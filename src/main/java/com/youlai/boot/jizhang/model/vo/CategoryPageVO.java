package com.youlai.boot.jizhang.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分类视图对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "分类视图对象")
public class CategoryPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "分类ID")
    private Long id;

    @Schema(description = "父分类ID")
    private Long parentId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "收支类型：expense支出，income收入")
    private String type;

    @Schema(description = "图标名称或URL")
    private String icon;

    @Schema(description = "排序序号")
    private Integer sortOrder;
}
