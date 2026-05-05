package com.youlai.boot.jizhang.converter;

import com.youlai.boot.jizhang.model.entity.JzTransaction;
import com.youlai.boot.jizhang.model.form.TransactionForm;
import org.mapstruct.Mapper;

/**
 * 收支记录对象转换器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper(componentModel = "spring")
public interface TransactionConverter {

    TransactionForm toForm(JzTransaction entity);

    JzTransaction toEntity(TransactionForm formData);
}
