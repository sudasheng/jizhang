package com.youlai.boot.jizhang.model.query;

import com.youlai.boot.common.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 预算查询对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "预算查询对象")
public class BudgetQuery extends BaseQuery {

    @Schema(description = "账本ID")
    private Long ledgerId;

    @Schema(description = "预算月份范围(起止)")
    private List<String> month;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(hidden = true)
    private Long userId;
}
