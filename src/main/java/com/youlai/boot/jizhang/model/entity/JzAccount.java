package com.youlai.boot.jizhang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * 资产账户实体对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@TableName("jz_accounts")
public class JzAccount extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 账户名称
     */
    private String name;

    /**
     * 账户类型：cash/bank/credit/alipay/wechat/other
     */
    private String type;

    /**
     * 净资产
     */
    private BigDecimal balance;

    /**
     * 是否计入总资产(0-否 1-是)
     */
    private Integer isIncludeTotalAssets;

    /**
     * 备注
     */
    private String remark;

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
