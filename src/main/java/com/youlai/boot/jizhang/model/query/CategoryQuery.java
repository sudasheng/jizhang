package com.youlai.boot.jizhang.model.query;

import com.youlai.boot.common.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分类查询对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "分类查询对象")
public class CategoryQuery extends BaseQuery {

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "收支类型：expense支出，income收入")
    private String type;

    @Schema(description = "父分类ID")
    private Long parentId;

    @Schema(hidden = true)
    private Long userId;
}
