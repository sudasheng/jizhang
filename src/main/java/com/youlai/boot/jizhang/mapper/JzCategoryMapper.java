package com.youlai.boot.jizhang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.jizhang.model.entity.JzCategory;
import com.youlai.boot.jizhang.model.query.CategoryQuery;
import com.youlai.boot.jizhang.model.vo.CategoryPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类 Mapper
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper
public interface JzCategoryMapper extends BaseMapper<JzCategory> {

    /**
     * 获取分类分页列表
     */
    IPage<CategoryPageVO> getCategoryPage(Page<CategoryPageVO> page, CategoryQuery queryParams);

    /**
     * 获取分类列表（不分页）
     */
    List<CategoryPageVO> getCategoryList(CategoryQuery queryParams);
}
