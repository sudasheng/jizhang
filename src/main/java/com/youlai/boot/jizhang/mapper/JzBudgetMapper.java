package com.youlai.boot.jizhang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.jizhang.model.entity.JzBudget;
import com.youlai.boot.jizhang.model.query.BudgetQuery;
import com.youlai.boot.jizhang.model.vo.BudgetPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预算 Mapper
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper
public interface JzBudgetMapper extends BaseMapper<JzBudget> {

    /**
     * 获取预算分页列表
     */
    IPage<BudgetPageVO> getBudgetPage(Page<BudgetPageVO> page, BudgetQuery queryParams);
}
