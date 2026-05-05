package com.youlai.boot.jizhang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.jizhang.model.entity.JzTransaction;
import com.youlai.boot.jizhang.model.form.TransactionForm;
import com.youlai.boot.jizhang.model.query.TransactionQuery;
import com.youlai.boot.jizhang.model.vo.TransactionPageVO;

/**
 * 收支记录服务接口
 *
 * @author youlaitech
 * @since 2026-05-06
 */
public interface IJzTransactionService extends IService<JzTransaction> {

    /**
     * 获取收支记录分页列表
     */
    IPage<TransactionPageVO> getTransactionPage(TransactionQuery queryParams);

    /**
     * 获取收支记录表单数据
     */
    TransactionForm getTransactionFormData(Long id);

    /**
     * 新增收支记录
     */
    boolean saveTransaction(TransactionForm formData);

    /**
     * 修改收支记录
     */
    boolean updateTransaction(Long id, TransactionForm formData);

    /**
     * 删除收支记录
     */
    boolean deleteTransactions(String ids);
}
