package com.youlai.boot.jizhang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.jizhang.model.entity.JzBudget;
import com.youlai.boot.jizhang.model.form.BudgetForm;
import com.youlai.boot.jizhang.model.query.BudgetQuery;
import com.youlai.boot.jizhang.model.vo.BudgetPageVO;

/**
 * 预算服务接口
 *
 * @author youlaitech
 * @since 2026-05-06
 */
public interface IJzBudgetService extends IService<JzBudget> {

    /**
     * 获取预算分页列表
     */
    IPage<BudgetPageVO> getBudgetPage(BudgetQuery queryParams);

    /**
     * 获取预算表单数据
     */
    BudgetForm getBudgetFormData(Long id);

    /**
     * 新增预算
     */
    boolean saveBudget(BudgetForm formData);

    /**
     * 修改预算
     */
    boolean updateBudget(Long id, BudgetForm formData);

    /**
     * 删除预算
     */
    boolean deleteBudgets(String ids);
}
