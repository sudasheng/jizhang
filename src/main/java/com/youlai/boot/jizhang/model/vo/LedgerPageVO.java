package com.youlai.boot.jizhang.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账本视图对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@Schema(description = "账本视图对象")
public class LedgerPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "账本ID")
    private Long id;

    @Schema(description = "账本名称")
    private String name;

    @Schema(description = "是否为默认账本 (0:否,1:是)")
    private Integer isDefault;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
}
