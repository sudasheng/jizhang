package com.youlai.boot.jizhang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.jizhang.model.entity.JzLedger;
import com.youlai.boot.jizhang.model.query.LedgerQuery;
import com.youlai.boot.jizhang.model.vo.LedgerPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账本 Mapper
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper
public interface JzLedgerMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<JzLedger> {

    /**
     * 获取账本分页列表
     */
    IPage<LedgerPageVO> getLedgerPage(Page<LedgerPageVO> page, LedgerQuery queryParams);
}
