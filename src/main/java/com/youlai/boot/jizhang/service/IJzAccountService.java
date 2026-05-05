package com.youlai.boot.jizhang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.jizhang.model.entity.JzAccount;
import com.youlai.boot.jizhang.model.form.AccountForm;
import com.youlai.boot.jizhang.model.query.AccountQuery;
import com.youlai.boot.jizhang.model.vo.AccountPageVO;

/**
 * 资产账户服务接口
 *
 * @author youlaitech
 * @since 2026-05-06
 */
public interface IJzAccountService extends IService<JzAccount> {

    /**
     * 获取资产账户分页列表
     */
    IPage<AccountPageVO> getAccountPage(AccountQuery queryParams);

    /**
     * 获取资产账户表单数据
     */
    AccountForm getAccountFormData(Long id);

    /**
     * 新增资产账户
     */
    boolean saveAccount(AccountForm formData);

    /**
     * 修改资产账户
     */
    boolean updateAccount(Long id, AccountForm formData);

    /**
     * 删除资产账户
     */
    boolean deleteAccounts(String ids);
}
