package com.youlai.boot.jizhang.converter;

import com.youlai.boot.jizhang.model.entity.JzAccount;
import com.youlai.boot.jizhang.model.form.AccountForm;
import org.mapstruct.Mapper;

/**
 * 资产账户对象转换器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper(componentModel = "spring")
public interface AccountConverter {

    AccountForm toForm(JzAccount entity);

    JzAccount toEntity(AccountForm formData);
}
