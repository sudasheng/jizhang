package com.youlai.boot.jizhang.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 预算表单对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "预算表单对象")
public class BudgetForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "预算ID")
    private Long id;

    @Schema(description = "所属账本ID")
    @NotNull(message = "账本不能为空")
    private Long ledgerId;

    @Schema(description = "预算月份")
    @NotNull(message = "预算月份不能为空")
    private LocalDate month;

    @Schema(description = "分类ID，空表示整个账本的总预算")
    private Long categoryId;

    @Schema(description = "预算金额")
    @NotNull(message = "预算金额不能为空")
    private BigDecimal amount;
}
