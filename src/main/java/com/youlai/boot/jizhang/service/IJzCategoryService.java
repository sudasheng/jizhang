package com.youlai.boot.jizhang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.jizhang.model.entity.JzCategory;
import com.youlai.boot.jizhang.model.form.CategoryForm;
import com.youlai.boot.jizhang.model.query.CategoryQuery;
import com.youlai.boot.jizhang.model.vo.CategoryPageVO;

import java.util.List;

/**
 * 分类服务接口
 *
 * @author youlaitech
 * @since 2026-05-06
 */
public interface IJzCategoryService extends IService<JzCategory> {

    /**
     * 获取分类分页列表
     */
    IPage<CategoryPageVO> getCategoryPage(CategoryQuery queryParams);

    /**
     * 获取分类列表（不分页）
     */
    List<CategoryPageVO> getCategoryList(CategoryQuery queryParams);

    /**
     * 获取分类表单数据
     */
    CategoryForm getCategoryFormData(Long id);

    /**
     * 新增分类
     */
    boolean saveCategory(CategoryForm formData);

    /**
     * 修改分类
     */
    boolean updateCategory(Long id, CategoryForm formData);

    /**
     * 删除分类
     */
    boolean deleteCategories(String ids);
}
