package com.youlai.boot.jizhang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 收支记录实体对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@TableName("jz_transactions")
public class JzTransaction extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 所属账本ID
     */
    private Long ledgerId;

    /**
     * 所属用户ID（冗余，便于查询）
     */
    private Long userId;

    /**
     * 所属资产账户ID
     */
    private Long accountId;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 金额（正数）
     */
    private BigDecimal amount;

    /**
     * 收支类型：expense支出，income收入
     */
    private String type;

    /**
     * 记账日期（账单日期）
     */
    private LocalDate transactionDate;

    /**
     * 备注
     */
    private String note;

    /**
     * 是否不计入收支/结余 (0:计入,1:不计入)
     */
    private Integer isExcluded;

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
