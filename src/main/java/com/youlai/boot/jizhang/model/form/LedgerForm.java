package com.youlai.boot.jizhang.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 账本表单对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "账本表单对象")
public class LedgerForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "账本ID")
    private Long id;

    @Schema(description = "账本名称")
    @NotBlank(message = "账本名称不能为空")
    @Size(max = 64, message = "账本名称长度不能超过64个字符")
    private String name;

    @Schema(description = "是否为默认账本 (0:否,1:是)")
    private Integer isDefault;
}
