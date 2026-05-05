package com.youlai.boot.jizhang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.enums.ActionTypeEnum;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import com.youlai.boot.jizhang.model.form.TransactionForm;
import com.youlai.boot.jizhang.model.query.TransactionQuery;
import com.youlai.boot.jizhang.model.vo.TransactionPageVO;
import com.youlai.boot.jizhang.service.IJzTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 收支记录控制器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Tag(name = "04.收支记录管理")
@RestController
@RequestMapping("/api/v1/jz-transactions")
@RequiredArgsConstructor
public class JzTransactionController {

    private final IJzTransactionService transactionService;

    @Operation(summary = "收支记录分页列表")
    @GetMapping
    public PageResult<TransactionPageVO> getTransactionPage(TransactionQuery queryParams) {
        IPage<TransactionPageVO> result = transactionService.getTransactionPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增收支记录")
    @PostMapping
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.INSERT)
    public Result<?> saveTransaction(@RequestBody @Valid TransactionForm formData) {
        boolean result = transactionService.saveTransaction(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取收支记录表单数据")
    @GetMapping("/{id}/form")
    public Result<TransactionForm> getTransactionForm(@Parameter(description = "记录ID") @PathVariable Long id) {
        TransactionForm formData = transactionService.getTransactionFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改收支记录")
    @PutMapping("/{id}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.UPDATE)
    public Result<Void> updateTransaction(@Parameter(description = "记录ID") @PathVariable Long id,
                                          @RequestBody @Valid TransactionForm formData) {
        boolean result = transactionService.updateTransaction(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除收支记录")
    @DeleteMapping("/{ids}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.DELETE)
    public Result<Void> deleteTransactions(@Parameter(description = "记录ID集合，逗号分隔") @PathVariable String ids) {
        boolean result = transactionService.deleteTransactions(ids);
        return Result.judge(result);
    }
}
