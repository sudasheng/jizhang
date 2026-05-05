package com.youlai.boot.jizhang.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 资产账户视图对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "资产账户视图对象")
public class AccountPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "账户ID")
    private Long id;

    @Schema(description = "账户名称")
    private String name;

    @Schema(description = "账户类型")
    private String type;

    @Schema(description = "净资产")
    private BigDecimal balance;

    @Schema(description = "是否计入总资产(0-否 1-是)")
    private Integer isIncludeTotalAssets;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
}
