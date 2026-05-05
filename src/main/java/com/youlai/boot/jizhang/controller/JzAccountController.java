package com.youlai.boot.jizhang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.enums.ActionTypeEnum;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import com.youlai.boot.jizhang.model.form.AccountForm;
import com.youlai.boot.jizhang.model.query.AccountQuery;
import com.youlai.boot.jizhang.model.vo.AccountPageVO;
import com.youlai.boot.jizhang.service.IJzAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 资产账户控制器
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Tag(name = "02.资产账户管理")
@RestController
@RequestMapping("/api/v1/jz-accounts")
@RequiredArgsConstructor
public class JzAccountController {

    private final IJzAccountService accountService;

    @Operation(summary = "资产账户分页列表")
    @GetMapping
    public PageResult<AccountPageVO> getAccountPage(AccountQuery queryParams) {
        IPage<AccountPageVO> result = accountService.getAccountPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增资产账户")
    @PostMapping
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.INSERT)
    public Result<?> saveAccount(@RequestBody @Valid AccountForm formData) {
        boolean result = accountService.saveAccount(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取资产账户表单数据")
    @GetMapping("/{id}/form")
    public Result<AccountForm> getAccountForm(@Parameter(description = "账户ID") @PathVariable Long id) {
        AccountForm formData = accountService.getAccountFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改资产账户")
    @PutMapping("/{id}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.UPDATE)
    public Result<Void> updateAccount(@Parameter(description = "账户ID") @PathVariable Long id,
                                      @RequestBody @Valid AccountForm formData) {
        boolean result = accountService.updateAccount(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除资产账户")
    @DeleteMapping("/{ids}")
    @Log(module = LogModuleEnum.JIZHANG, value = ActionTypeEnum.DELETE)
    public Result<Void> deleteAccounts(@Parameter(description = "账户ID集合，逗号分隔") @PathVariable String ids) {
        boolean result = accountService.deleteAccounts(ids);
        return Result.judge(result);
    }
}
