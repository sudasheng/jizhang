package com.youlai.boot.jizhang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 收支分类实体对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Getter
@Setter
@TableName("jz_categories")
public class JzCategory extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 所属用户ID，0表示系统预置分类
     */
    private Long userId;

    /**
     * 父分类ID，0表示一级分类
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 收支类型：expense支出，income收入
     */
    private String type;

    /**
     * 图标名称或URL
     */
    private String icon;

    /**
     * 排序序号，越小越靠前
     */
    private Integer sortOrder;

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
