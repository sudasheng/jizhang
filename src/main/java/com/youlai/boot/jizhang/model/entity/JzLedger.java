package com.youlai.boot.jizhang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 账本实体对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@TableName("jz_ledgers")
public class JzLedger extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 账本名称
     */
    private String name;

    /**
     * 是否为默认账本 (0:否,1:是)
     */
    private Integer isDefault;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 更新人ID
     */
    private Long updateBy;

    /**
     * 逻辑删除标识(0-未删除 1-已删除)
     */
    private Integer isDeleted;
}
