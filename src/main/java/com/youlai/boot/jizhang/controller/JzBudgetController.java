package com.youlai.boot.jizhang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.enums.ActionTypeEnum;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import com.youlai.boot.jizhang.model.form.BudgetForm;
import com.youlai.boot.jizhang.model.query.BudgetQuery;
import com.youlai.boot.jizhang.model.vo.BudgetPageVO;
import com.youlai.boot.jizhang.service.IJzBudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 预算控制器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Tag(name = "05.预算管理")
@RestController
@RequestMapping("/api/v1/jz-budgets")
@RequiredArgsConstructor
public class JzBudgetController {

    private final IJzBudgetService budgetService;

    @Operation(summary = "预算分页列表")
    @GetMapping
    public PageResult<BudgetPageVO> getBudgetPage(BudgetQuery queryParams) {
        IPage<BudgetPageVO> result = budgetService.getBudgetPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增预算")
    @PostMapping
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.INSERT)
    public Result<?> saveBudget(@RequestBody @Valid BudgetForm formData) {
        boolean result = budgetService.saveBudget(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取预算表单数据")
    @GetMapping("/{id}/form")
    public Result<BudgetForm> getBudgetForm(@Parameter(description = "预算ID") @PathVariable Long id) {
        BudgetForm formData = budgetService.getBudgetFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改预算")
    @PutMapping("/{id}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.UPDATE)
    public Result<Void> updateBudget(@Parameter(description = "预算ID") @PathVariable Long id,
                                     @RequestBody @Valid BudgetForm formData) {
        boolean result = budgetService.updateBudget(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除预算")
    @DeleteMapping("/{ids}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.DELETE)
    public Result<Void> deleteBudgets(@Parameter(description = "预算ID集合，逗号分隔") @PathVariable String ids) {
        boolean result = budgetService.deleteBudgets(ids);
        return Result.judge(result);
    }
}
