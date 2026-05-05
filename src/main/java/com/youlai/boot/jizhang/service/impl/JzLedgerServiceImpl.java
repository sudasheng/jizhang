package com.youlai.boot.jizhang.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.framework.security.util.SecurityUtils;
import com.youlai.boot.jizhang.converter.LedgerConverter;
import com.youlai.boot.jizhang.mapper.JzLedgerMapper;
import com.youlai.boot.jizhang.model.entity.JzLedger;
import com.youlai.boot.jizhang.model.form.LedgerForm;
import com.youlai.boot.jizhang.model.query.LedgerQuery;
import com.youlai.boot.jizhang.model.vo.LedgerPageVO;
import com.youlai.boot.jizhang.service.IJzLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 账本服务实现
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Service
@RequiredArgsConstructor
public class JzLedgerServiceImpl extends ServiceImpl<JzLedgerMapper, JzLedger> implements IJzLedgerService {

    private final LedgerConverter ledgerConverter;

    @Override
    public IPage<LedgerPageVO> getLedgerPage(LedgerQuery queryParams) {
        queryParams.setUserId(SecurityUtils.getUserId());
        return this.baseMapper.getLedgerPage(new Page<>(queryParams.getPageNum(), queryParams.getPageSize()), queryParams);
    }

    @Override
    public LedgerForm getLedgerFormData(Long id) {
        JzLedger entity = this.getById(id);
        return ledgerConverter.toForm(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveLedger(LedgerForm formData) {
        JzLedger entity = ledgerConverter.toEntity(formData);
        entity.setUserId(SecurityUtils.getUserId());
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());
        if (Integer.valueOf(1).equals(entity.getIsDefault())) {
            this.update(new LambdaUpdateWrapper<JzLedger>().set(JzLedger::getIsDefault, 0).eq(JzLedger::getIsDefault, 1));
        }
        return this.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateLedger(Long id, LedgerForm formData) {
        JzLedger entity = ledgerConverter.toEntity(formData);
        entity.setId(id);
        entity.setUpdateBy(SecurityUtils.getUserId());
        if (Integer.valueOf(1).equals(entity.getIsDefault())) {
            this.update(new LambdaUpdateWrapper<JzLedger>().set(JzLedger::getIsDefault, 0).eq(JzLedger::getIsDefault, 1).ne(JzLedger::getId, id));
        }
        return this.updateById(entity);
    }

    @Override
    public boolean deleteLedgers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除数据为空");
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        return this.removeByIds(idList);
    }
}
