package com.youlai.boot.jizhang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.jizhang.model.entity.JzLedger;
import com.youlai.boot.jizhang.model.form.LedgerForm;
import com.youlai.boot.jizhang.model.query.LedgerQuery;
import com.youlai.boot.jizhang.model.vo.LedgerPageVO;

/**
 * 账本服务接口
 *
 * @author youlaitech
 * @since 2026-05-06
 */
public interface IJzLedgerService extends IService<JzLedger> {

    /**
     * 获取账本分页列表
     */
    IPage<LedgerPageVO> getLedgerPage(LedgerQuery queryParams);

    /**
     * 获取账本表单数据
     */
    LedgerForm getLedgerFormData(Long id);

    /**
     * 新增账本
     */
    boolean saveLedger(LedgerForm formData);

    /**
     * 修改账本
     */
    boolean updateLedger(Long id, LedgerForm formData);

    /**
     * 删除账本
     */
    boolean deleteLedgers(String ids);
}
