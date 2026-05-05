package com.youlai.boot.jizhang.model.query;

import com.youlai.boot.common.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账本查询对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "账本查询对象")
public class LedgerQuery extends BaseQuery {

    @Schema(description = "账本名称")
    private String name;

    @Schema(hidden = true)
    private Long userId;
}
