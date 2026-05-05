package com.youlai.boot.jizhang.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 收支记录视图对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "收支记录视图对象")
public class TransactionPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "记录ID")
    private Long id;

    @Schema(description = "所属账本ID")
    private Long ledgerId;

    @Schema(description = "所属资产账户ID")
    private Long accountId;

    @Schema(description = "资产账户名称")
    private String accountName;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "金额")
    private BigDecimal amount;

    @Schema(description = "收支类型：expense支出，income收入")
    private String type;

    @Schema(description = "记账日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

    @Schema(description = "备注")
    private String note;

    @Schema(description = "是否不计入收支/结余 (0:计入,1:不计入)")
    private Integer isExcluded;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
}
