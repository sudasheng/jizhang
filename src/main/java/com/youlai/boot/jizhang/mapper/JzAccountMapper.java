package com.youlai.boot.jizhang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.jizhang.model.entity.JzAccount;
import com.youlai.boot.jizhang.model.query.AccountQuery;
import com.youlai.boot.jizhang.model.vo.AccountPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产账户 Mapper
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Mapper
public interface JzAccountMapper extends BaseMapper<JzAccount> {

    /**
     * 获取资产账户分页列表
     */
    IPage<AccountPageVO> getAccountPage(Page<AccountPageVO> page, AccountQuery queryParams);
}
