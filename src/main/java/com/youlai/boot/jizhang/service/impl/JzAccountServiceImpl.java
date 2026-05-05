package com.youlai.boot.jizhang.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.framework.security.util.SecurityUtils;
import com.youlai.boot.jizhang.converter.AccountConverter;
import com.youlai.boot.jizhang.mapper.JzAccountMapper;
import com.youlai.boot.jizhang.model.entity.JzAccount;
import com.youlai.boot.jizhang.model.form.AccountForm;
import com.youlai.boot.jizhang.model.query.AccountQuery;
import com.youlai.boot.jizhang.model.vo.AccountPageVO;
import com.youlai.boot.jizhang.service.IJzAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 资产账户服务实现
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Service
@RequiredArgsConstructor
public class JzAccountServiceImpl extends ServiceImpl<JzAccountMapper, JzAccount> implements IJzAccountService {

    private final AccountConverter accountConverter;

    @Override
    public IPage<AccountPageVO> getAccountPage(AccountQuery queryParams) {
        queryParams.setUserId(SecurityUtils.getUserId());
        return this.baseMapper.getAccountPage(new Page<>(queryParams.getPageNum(), queryParams.getPageSize()), queryParams);
    }

    @Override
    public AccountForm getAccountFormData(Long id) {
        JzAccount entity = this.getById(id);
        return accountConverter.toForm(entity);
    }

    @Override
    public boolean saveAccount(AccountForm formData) {
        JzAccount entity = accountConverter.toEntity(formData);
        entity.setUserId(SecurityUtils.getUserId());
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());
        if (entity.getBalance() == null) {
            entity.setBalance(BigDecimal.ZERO);
        }
        if (entity.getIsIncludeTotalAssets() == null) {
            entity.setIsIncludeTotalAssets(1);
        }
        return this.save(entity);
    }

    @Override
    public boolean updateAccount(Long id, AccountForm formData) {
        JzAccount entity = accountConverter.toEntity(formData);
        entity.setId(id);
        entity.setUpdateBy(SecurityUtils.getUserId());
        return this.updateById(entity);
    }

    @Override
    public boolean deleteAccounts(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除数据为空");
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        return this.removeByIds(idList);
    }
}
