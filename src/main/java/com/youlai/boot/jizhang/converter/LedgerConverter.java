package com.youlai.boot.jizhang.converter;

import com.youlai.boot.jizhang.model.entity.JzLedger;
import com.youlai.boot.jizhang.model.form.LedgerForm;
import org.mapstruct.Mapper;

/**
 * 账本对象转换器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper(componentModel = "spring")
public interface LedgerConverter {

    LedgerForm toForm(JzLedger entity);

    JzLedger toEntity(LedgerForm formData);
}
