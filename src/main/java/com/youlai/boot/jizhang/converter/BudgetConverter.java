package com.youlai.boot.jizhang.converter;

import com.youlai.boot.jizhang.model.entity.JzBudget;
import com.youlai.boot.jizhang.model.form.BudgetForm;
import org.mapstruct.Mapper;

/**
 * 预算对象转换器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper(componentModel = "spring")
public interface BudgetConverter {

    BudgetForm toForm(JzBudget entity);

    JzBudget toEntity(BudgetForm formData);
}
