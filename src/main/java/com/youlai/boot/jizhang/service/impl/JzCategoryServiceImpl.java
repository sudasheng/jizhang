package com.youlai.boot.jizhang.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.framework.security.util.SecurityUtils;
import com.youlai.boot.jizhang.converter.CategoryConverter;
import com.youlai.boot.jizhang.mapper.JzCategoryMapper;
import com.youlai.boot.jizhang.model.entity.JzCategory;
import com.youlai.boot.jizhang.model.form.CategoryForm;
import com.youlai.boot.jizhang.model.query.CategoryQuery;
import com.youlai.boot.jizhang.model.vo.CategoryPageVO;
import com.youlai.boot.jizhang.service.IJzCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 分类服务实现
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Service
@RequiredArgsConstructor
public class JzCategoryServiceImpl extends ServiceImpl<JzCategoryMapper, JzCategory> implements IJzCategoryService {

    private final CategoryConverter categoryConverter;

    @Override
    public IPage<CategoryPageVO> getCategoryPage(CategoryQuery queryParams) {
        queryParams.setUserId(SecurityUtils.getUserId());
        return this.baseMapper.getCategoryPage(new Page<>(queryParams.getPageNum(), queryParams.getPageSize()), queryParams);
    }

    @Override
    public List<CategoryPageVO> getCategoryList(CategoryQuery queryParams) {
        queryParams.setUserId(SecurityUtils.getUserId());
        return this.baseMapper.getCategoryList(queryParams);
    }

    @Override
    public CategoryForm getCategoryFormData(Long id) {
        JzCategory entity = this.getById(id);
        return categoryConverter.toForm(entity);
    }

    @Override
    public boolean saveCategory(CategoryForm formData) {
        JzCategory entity = categoryConverter.toEntity(formData);
        entity.setUserId(SecurityUtils.getUserId());
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());
        if (entity.getParentId() == null) {
            entity.setParentId(0L);
        }
        if (entity.getSortOrder() == null) {
            entity.setSortOrder(0);
        }
        return this.save(entity);
    }

    @Override
    public boolean updateCategory(Long id, CategoryForm formData) {
        JzCategory entity = categoryConverter.toEntity(formData);
        entity.setId(id);
        entity.setUpdateBy(SecurityUtils.getUserId());
        return this.updateById(entity);
    }

    @Override
    public boolean deleteCategories(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除数据为空");
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        for (Long id : idList) {
            long count = this.lambdaQuery().eq(JzCategory::getParentId, id).count();
            Assert.isTrue(count == 0, "分类ID=" + id + " 存在子分类，无法删除");
        }
        return this.removeByIds(idList);
    }
}
