package com.youlai.boot.jizhang.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.framework.security.util.SecurityUtils;
import com.youlai.boot.jizhang.converter.TransactionConverter;
import com.youlai.boot.jizhang.mapper.JzAccountMapper;
import com.youlai.boot.jizhang.mapper.JzTransactionMapper;
import com.youlai.boot.jizhang.model.entity.JzAccount;
import com.youlai.boot.jizhang.model.entity.JzTransaction;
import com.youlai.boot.jizhang.model.form.TransactionForm;
import com.youlai.boot.jizhang.model.query.TransactionQuery;
import com.youlai.boot.jizhang.model.vo.TransactionPageVO;
import com.youlai.boot.jizhang.service.IJzTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 收支记录服务实现
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Service
@RequiredArgsConstructor
public class JzTransactionServiceImpl extends ServiceImpl<JzTransactionMapper, JzTransaction> implements IJzTransactionService {

    private final TransactionConverter transactionConverter;
    private final JzAccountMapper accountMapper;

    @Override
    public IPage<TransactionPageVO> getTransactionPage(TransactionQuery queryParams) {
        queryParams.setUserId(SecurityUtils.getUserId());
        return this.baseMapper.getTransactionPage(new Page<>(queryParams.getPageNum(), queryParams.getPageSize()), queryParams);
    }

    @Override
    public TransactionForm getTransactionFormData(Long id) {
        JzTransaction entity = this.getById(id);
        return transactionConverter.toForm(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveTransaction(TransactionForm formData) {
        JzTransaction entity = transactionConverter.toEntity(formData);
        entity.setUserId(SecurityUtils.getUserId());
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());
        boolean saved = this.save(entity);
        if (saved) {
            updateAccountBalance(entity.getAccountId(), entity.getAmount(), entity.getType());
        }
        return saved;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTransaction(Long id, TransactionForm formData) {
        JzTransaction oldEntity = this.getById(id);
        Assert.notNull(oldEntity, "收支记录不存在");

        // 还原旧记录的账户余额
        reverseAccountBalance(oldEntity.getAccountId(), oldEntity.getAmount(), oldEntity.getType());

        JzTransaction entity = transactionConverter.toEntity(formData);
        entity.setId(id);
        entity.setUserId(oldEntity.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());
        boolean updated = this.updateById(entity);
        if (updated) {
            updateAccountBalance(entity.getAccountId(), entity.getAmount(), entity.getType());
        } else {
            updateAccountBalance(oldEntity.getAccountId(), oldEntity.getAmount(), oldEntity.getType());
        }
        return updated;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteTransactions(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除数据为空");
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        List<JzTransaction> transactions = this.listByIds(idList);
        boolean removed = this.removeByIds(idList);
        if (removed) {
            for (JzTransaction transaction : transactions) {
                reverseAccountBalance(transaction.getAccountId(), transaction.getAmount(), transaction.getType());
            }
        }
        return removed;
    }

    /**
     * 更新账户余额
     */
    private void updateAccountBalance(Long accountId, BigDecimal amount, String type) {
        JzAccount account = accountMapper.selectById(accountId);
        if (account != null) {
            BigDecimal newBalance = "income".equals(type)
                    ? account.getBalance().add(amount)
                    : account.getBalance().subtract(amount);
            account.setBalance(newBalance);
            accountMapper.updateById(account);
        }
    }

    /**
     * 还原账户余额（删除或修改时使用）
     */
    private void reverseAccountBalance(Long accountId, BigDecimal amount, String type) {
        JzAccount account = accountMapper.selectById(accountId);
        if (account != null) {
            BigDecimal newBalance = "expense".equals(type)
                    ? account.getBalance().add(amount)
                    : account.getBalance().subtract(amount);
            account.setBalance(newBalance);
            accountMapper.updateById(account);
        }
    }
}
