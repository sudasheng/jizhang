package com.youlai.boot.jizhang.model.query;

import com.youlai.boot.common.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 收支记录查询对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "收支记录查询对象")
public class TransactionQuery extends BaseQuery {

    @Schema(description = "账本ID")
    private Long ledgerId;

    @Schema(description = "资产账户ID")
    private Long accountId;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "收支类型：expense支出，income收入")
    private String type;

    @Schema(description = "记账日期范围(起止)")
    private List<String> transactionDate;

    @Schema(description = "备注")
    private String note;

    @Schema(hidden = true)
    private Long userId;
}
