package com.youlai.boot.jizhang.model.query;

import com.youlai.boot.common.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资产账户查询对象
 *
 * @author youlaitech
 * @since 2026-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "资产账户查询对象")
public class AccountQuery extends BaseQuery {

    @Schema(description = "账户名称")
    private String name;

    @Schema(description = "账户类型：cash/bank/credit/alipay/wechat/other")
    private String type;

    @Schema(hidden = true)
    private Long userId;
}
