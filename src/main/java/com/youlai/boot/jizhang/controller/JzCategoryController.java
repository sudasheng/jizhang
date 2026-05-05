package com.youlai.boot.jizhang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.enums.ActionTypeEnum;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import com.youlai.boot.jizhang.model.form.CategoryForm;
import com.youlai.boot.jizhang.model.query.CategoryQuery;
import com.youlai.boot.jizhang.model.vo.CategoryPageVO;
import com.youlai.boot.jizhang.service.IJzCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 收支分类控制器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Tag(name = "03.收支分类管理")
@RestController
@RequestMapping("/api/v1/jz-categories")
@RequiredArgsConstructor
public class JzCategoryController {

    private final IJzCategoryService categoryService;

    @Operation(summary = "分类分页列表")
    @GetMapping
    public PageResult<CategoryPageVO> getCategoryPage(CategoryQuery queryParams) {
        IPage<CategoryPageVO> result = categoryService.getCategoryPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "分类列表（不分页）")
    @GetMapping("/list")
    public Result<List<CategoryPageVO>> getCategoryList(CategoryQuery queryParams) {
        List<CategoryPageVO> list = categoryService.getCategoryList(queryParams);
        return Result.success(list);
    }

    @Operation(summary = "分类树形选项")
    @GetMapping("/tree")
    public Result<List<Option>> getCategoryTree(CategoryQuery queryParams) {
        List<CategoryPageVO> list = categoryService.getCategoryList(queryParams);
        // 按 parentId 分组
        Map<Long, List<CategoryPageVO>> parentIdMap = list.stream()
                .collect(Collectors.groupingBy(CategoryPageVO::getParentId));
        // 构建一级节点
        List<Option> tree = new ArrayList<>();
        List<CategoryPageVO> roots = parentIdMap.getOrDefault(0L, List.of());
        for (CategoryPageVO root : roots) {
            tree.add(buildOption(root, parentIdMap));
        }
        return Result.success(tree);
    }

    @Operation(summary = "新增分类")
    @PostMapping
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.INSERT)
    public Result<?> saveCategory(@RequestBody @Valid CategoryForm formData) {
        boolean result = categoryService.saveCategory(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取分类表单数据")
    @GetMapping("/{id}/form")
    public Result<CategoryForm> getCategoryForm(@Parameter(description = "分类ID") @PathVariable Long id) {
        CategoryForm formData = categoryService.getCategoryFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改分类")
    @PutMapping("/{id}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.UPDATE)
    public Result<Void> updateCategory(@Parameter(description = "分类ID") @PathVariable Long id,
                                       @RequestBody @Valid CategoryForm formData) {
        boolean result = categoryService.updateCategory(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除分类")
    @DeleteMapping("/{ids}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.DELETE)
    public Result<Void> deleteCategories(@Parameter(description = "分类ID集合，逗号分隔") @PathVariable String ids) {
        boolean result = categoryService.deleteCategories(ids);
        return Result.judge(result);
    }

    /**
     * 递归构建树节点
     */
    private Option buildOption(CategoryPageVO node, Map<Long, List<CategoryPageVO>> parentIdMap) {
        Option option = new Option(node.getId(), node.getName());
        List<CategoryPageVO> children = parentIdMap.getOrDefault(node.getId(), List.of());
        if (!children.isEmpty()) {
            List<Option> childOptions = children.stream()
                    .map(child -> buildOption(child, parentIdMap))
                    .toList();
            option.setChildren(childOptions);
        }
        return option;
    }
}
