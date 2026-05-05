package com.youlai.boot.jizhang.converter;

import com.youlai.boot.jizhang.model.entity.JzCategory;
import com.youlai.boot.jizhang.model.form.CategoryForm;
import org.mapstruct.Mapper;

/**
 * 分类对象转换器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper(componentModel = "spring")
public interface CategoryConverter {

    CategoryForm toForm(JzCategory entity);

    JzCategory toEntity(CategoryForm formData);
}
