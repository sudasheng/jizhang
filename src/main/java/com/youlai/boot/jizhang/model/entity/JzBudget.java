package com.youlai.boot.jizhang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 预算实体对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@TableName("jz_budgets")
public class JzBudget extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 所属账本ID
     */
    private Long ledgerId;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 预算月份，存储当月第一天
     */
    private LocalDate month;

    /**
     * 分类ID，NULL表示整个账本的总预算
     */
    private Long categoryId;

    /**
     * 预算金额
     */
    private BigDecimal amount;

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
