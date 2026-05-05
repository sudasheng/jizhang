package com.youlai.boot.jizhang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.enums.ActionTypeEnum;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import com.youlai.boot.jizhang.model.form.LedgerForm;
import com.youlai.boot.jizhang.model.query.LedgerQuery;
import com.youlai.boot.jizhang.model.vo.LedgerPageVO;
import com.youlai.boot.jizhang.service.IJzLedgerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 账本控制器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Tag(name = "01.账本管理")
@RestController
@RequestMapping("/api/v1/jz-ledgers")
@RequiredArgsConstructor
public class JzLedgerController {

    private final IJzLedgerService ledgerService;

    @Operation(summary = "账本分页列表")
    @GetMapping
    public PageResult<LedgerPageVO> getLedgerPage(LedgerQuery queryParams) {
        IPage<LedgerPageVO> result = ledgerService.getLedgerPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增账本")
    @PostMapping
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.INSERT)
    public Result<?> saveLedger(@RequestBody @Valid LedgerForm formData) {
        boolean result = ledgerService.saveLedger(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取账本表单数据")
    @GetMapping("/{id}/form")
    public Result<LedgerForm> getLedgerForm(@Parameter(description = "账本ID") @PathVariable Long id) {
        LedgerForm formData = ledgerService.getLedgerFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改账本")
    @PutMapping("/{id}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.UPDATE)
    public Result<Void> updateLedger(@Parameter(description = "账本ID") @PathVariable Long id,
                                     @RequestBody @Valid LedgerForm formData) {
        boolean result = ledgerService.updateLedger(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除账本")
    @DeleteMapping("/{ids}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.DELETE)
    public Result<Void> deleteLedgers(@Parameter(description = "账本ID集合，逗号分隔") @PathVariable String ids) {
        boolean result = ledgerService.deleteLedgers(ids);
        return Result.judge(result);
    }
}
