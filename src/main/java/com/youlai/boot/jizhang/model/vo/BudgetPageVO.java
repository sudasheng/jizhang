package com.youlai.boot.jizhang.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 预算视图对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "预算视图对象")
public class BudgetPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "预算ID")
    private Long id;

    @Schema(description = "所属账本ID")
    private Long ledgerId;

    @Schema(description = "预算月份")
    private LocalDate month;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "预算金额")
    private BigDecimal amount;
}
