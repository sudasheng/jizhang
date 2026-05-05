package com.youlai.boot.jizhang.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 收支记录表单对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "收支记录表单对象")
public class TransactionForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "记录ID")
    private Long id;

    @Schema(description = "所属账本ID")
    @NotNull(message = "账本不能为空")
    private Long ledgerId;

    @Schema(description = "所属资产账户ID")
    @NotNull(message = "资产账户不能为空")
    private Long accountId;

    @Schema(description = "分类ID")
    @NotNull(message = "分类不能为空")
    private Long categoryId;

    @Schema(description = "金额")
    @NotNull(message = "金额不能为空")
    private BigDecimal amount;

    @Schema(description = "收支类型：expense支出，income收入")
    @NotBlank(message = "收支类型不能为空")
    private String type;

    @Schema(description = "记账日期")
    @NotNull(message = "记账日期不能为空")
    private LocalDate transactionDate;

    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过255个字符")
    private String note;

    @Schema(description = "是否不计入收支/结余 (0:计入,1:不计入)")
    private Integer isExcluded;
}
