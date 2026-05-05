package com.youlai.boot.jizhang.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.framework.security.util.SecurityUtils;
import com.youlai.boot.jizhang.converter.BudgetConverter;
import com.youlai.boot.jizhang.mapper.JzBudgetMapper;
import com.youlai.boot.jizhang.model.entity.JzBudget;
import com.youlai.boot.jizhang.model.form.BudgetForm;
import com.youlai.boot.jizhang.model.query.BudgetQuery;
import com.youlai.boot.jizhang.model.vo.BudgetPageVO;
import com.youlai.boot.jizhang.service.IJzBudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 预算服务实现
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Service
@RequiredArgsConstructor
public class JzBudgetServiceImpl extends ServiceImpl<JzBudgetMapper, JzBudget> implements IJzBudgetService {

    private final BudgetConverter budgetConverter;

    @Override
    public IPage<BudgetPageVO> getBudgetPage(BudgetQuery queryParams) {
        queryParams.setUserId(SecurityUtils.getUserId());
        return this.baseMapper.getBudgetPage(new Page<>(queryParams.getPageNum(), queryParams.getPageSize()), queryParams);
    }

    @Override
    public BudgetForm getBudgetFormData(Long id) {
        JzBudget entity = this.getById(id);
        return budgetConverter.toForm(entity);
    }

    @Override
    public boolean saveBudget(BudgetForm formData) {
        JzBudget entity = budgetConverter.toEntity(formData);
        entity.setUserId(SecurityUtils.getUserId());
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());
        return this.save(entity);
    }

    @Override
    public boolean updateBudget(Long id, BudgetForm formData) {
        JzBudget entity = budgetConverter.toEntity(formData);
        entity.setId(id);
        entity.setUpdateBy(SecurityUtils.getUserId());
        return this.updateById(entity);
    }

    @Override
    public boolean deleteBudgets(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除数据为空");
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        return this.removeByIds(idList);
    }
}
