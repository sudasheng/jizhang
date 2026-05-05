package com.youlai.boot.jizhang.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 资产账户表单对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "资产账户表单对象")
public class AccountForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "账户ID")
    private Long id;

    @Schema(description = "账户名称")
    @NotBlank(message = "账户名称不能为空")
    @Size(max = 64, message = "账户名称长度不能超过64个字符")
    private String name;

    @Schema(description = "账户类型：cash/bank/credit/alipay/wechat/other")
    private String type;

    @Schema(description = "净资产")
    private BigDecimal balance;

    @Schema(description = "是否计入总资产(0-否 1-是)")
    private Integer isIncludeTotalAssets;

    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过255个字符")
    private String remark;
}
