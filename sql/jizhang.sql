-- 2. 账本表（支持多账本）
CREATE TABLE `jz_ledgers` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '账本ID',
  `user_id` bigint unsigned NOT NULL COMMENT '所属用户ID',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '默认账本' COMMENT '账本名称',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为默认账本 (0:否,1:是)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='账本表';


-- 3. 资产账户表（如现金、银行卡、支付宝等）
CREATE TABLE `jz_accounts` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '账户ID',
  `user_id` bigint unsigned NOT NULL COMMENT '所属用户ID',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户名称，例如“aa”、“工资卡”',
  `type` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT 'other' COMMENT '账户类型：cash/bank/credit/alipay/wechat/other',
  `balance` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '净资产',
	`is_include_total_assets` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否计入总资产(0-否 1-是)' ,
	`remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资产账户表';

-- 4. 分类表（支持父子二级，收入和支出分类可混用，但通过type区分）
CREATE TABLE `jz_categories` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '所属用户ID，0表示系统预置分类，非0为用户自定义',
  `parent_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '父分类ID，0表示一级分类',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称，如“三餐”、“衣服”、“域名”',
  `type` enum('expense','income') COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收支类型：expense支出，income收入',
  `icon` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '图标名称或URL',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序序号，越小越靠前',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收支分类表（支持二级）';

-- 5. 收支记录表（核心流水）
CREATE TABLE `jz_transactions` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `ledger_id` bigint unsigned NOT NULL COMMENT '所属账本ID',
  `user_id` bigint unsigned NOT NULL COMMENT '所属用户ID（冗余，便于查询）',
  `account_id` bigint unsigned NOT NULL COMMENT '所属资产账户ID',
  `category_id` bigint unsigned NOT NULL COMMENT '分类ID（关联categories表）',
  `amount` decimal(12,2) NOT NULL COMMENT '金额（正数）',
  `type` enum('expense','income') COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收支类型：expense支出，income收入',
  `transaction_date` date NOT NULL COMMENT '记账日期（账单日期）',
  `note` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  `is_excluded` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否不计入收支/结余 (0:计入,1:不计入)',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收支记录表';

-- 6. 预算表（按月、按账本、可选按分类设置预算）
CREATE TABLE `jz_budgets` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '预算ID',
  `ledger_id` bigint unsigned NOT NULL COMMENT '所属账本ID',
  `user_id` bigint unsigned NOT NULL COMMENT '所属用户ID',
  `month` date NOT NULL COMMENT '预算月份，存储当月第一天，如2026-05-01',
  `category_id` bigint unsigned DEFAULT NULL COMMENT '分类ID，NULL表示整个账本的总预算',
  `amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '预算金额',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='预算表';