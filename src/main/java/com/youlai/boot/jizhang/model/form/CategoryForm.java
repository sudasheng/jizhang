package com.youlai.boot.jizhang.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分类表单对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "分类表单对象")
public class CategoryForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "分类ID")
    private Long id;

    @Schema(description = "父分类ID，0表示一级分类")
    private Long parentId;

    @Schema(description = "分类名称")
    @NotBlank(message = "分类名称不能为空")
    @Size(max = 64, message = "分类名称长度不能超过64个字符")
    private String name;

    @Schema(description = "收支类型：expense支出，income收入")
    @NotBlank(message = "收支类型不能为空")
    private String type;

    @Schema(description = "图标名称或URL")
    @Size(max = 64, message = "图标长度不能超过64个字符")
    private String icon;

    @Schema(description = "排序序号，越小越靠前")
    private Integer sortOrder;
}
