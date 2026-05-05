<template>
  <view class="page page--tabbar bg-[#FDF8F8] min-h-screen pb-20">
    <!-- 顶部状态栏占位 -->
    <view :style="{ height: statusBarHeight + 'px' }" class="bg-[#FDF8F8]" />

    <!-- 用户信息头部 -->
    <view class="flex items-center px-6 py-6 mb-2">
      <view class="w-16 h-16 rounded-full bg-[#FFE082] flex items-center justify-center mr-4 border-2 border-white shadow-sm overflow-hidden">
        <image src="/static/logo.png" mode="aspectFit" class="w-12 h-12" />
      </view>
      <view class="flex-1">
        <view class="flex items-center mb-1">
          <text class="text-xl font-bold text-[#333] mr-2">甜心65570</text>
          <wd-icon name="edit-1" size="18px" color="#ccc" />
        </view>
        <text class="text-xs text-gray-400">你暂时还不是会员</text>
      </view>
    </view>

    <!-- 会员特权横幅 -->
    <view class="px-4 mb-6">
      <view class="vip-banner rounded-3xl p-5 flex justify-between items-center relative overflow-hidden">
        <view class="relative z-10">
          <text class="text-lg font-bold text-white block mb-1">开通会员,享更多特权</text>
          <text class="text-xs text-white opacity-80">成为会员无限制使用所有功能</text>
        </view>
        <view class="bg-white rounded-full px-5 py-2 relative z-10 active:scale-95 transition-transform" @click="handleGoVip">
          <text class="text-sm font-bold text-[#FF5D5D]">去开通</text>
        </view>
        <!-- 背景装饰效果 -->
        <view class="absolute right-[-20rpx] top-[-20rpx] w-20 h-20 bg-white opacity-10 rounded-full"></view>
      </view>
    </view>

    <!-- 功能菜单列表 -->
    <view class="px-4">
      <!-- 第一组 -->
      <view class="menu-group bg-white rounded-3xl overflow-hidden mb-4 shadow-sm">
        <wd-cell title="个性商城" is-link @click="handleMenuClick('个性商城')">
          <template #icon>
            <view class="mr-3 flex items-center"><wd-icon name="home" size="20px" color="#D4A017" /></view>
          </template>
          <template #value>
             <view class="bg-[#FF7070] text-white text-[10px] px-2 py-0.5 rounded-full flex items-center">
               <wd-icon name="chart-bar" size="10px" class="mr-0.5" />
               <text class="font-bold">HOT</text>
             </view>
          </template>
        </wd-cell>
      </view>

      <!-- 第二组 -->
      <view class="menu-group bg-white rounded-3xl overflow-hidden mb-4 shadow-sm">
        <wd-cell title="自动记账" is-link @click="handleMenuClick('自动记账')">
          <template #icon>
            <view class="mr-3 flex items-center"><wd-icon name="computer" size="20px" color="#1E88E5" /></view>
          </template>
        </wd-cell>
      </view>

      <!-- 第三组 -->
      <view class="menu-group bg-white rounded-3xl overflow-hidden mb-4 shadow-sm">
        <wd-cell title="记账日历" is-link @click="handleMenuClick('记账日历')">
          <template #icon>
            <view class="mr-3 flex items-center"><wd-icon name="calendar" size="20px" color="#D4A017" /></view>
          </template>
          <template #value>
             <view class="bg-[#444] text-white text-[10px] px-2 py-0.5 rounded-md font-bold">VIP</view>
          </template>
        </wd-cell>
        <wd-cell title="每月开始于" is-link @click="handleMenuClick('每月开始于')">
          <template #icon>
            <view class="mr-3 flex items-center"><wd-icon name="app" size="20px" color="#FF7070" /></view>
          </template>
          <template #value>
             <view class="flex items-center">
               <text class="text-sm text-gray-400 mr-2">1日</text>
               <view class="bg-[#444] text-white text-[10px] px-2 py-0.5 rounded-md font-bold">VIP</view>
             </view>
          </template>
        </wd-cell>
        <wd-cell title="账本管理" is-link @click="handleMenuClick('账本管理')">
          <template #icon>
            <view class="mr-3 flex items-center"><wd-icon name="order" size="20px" color="#A78BFA" /></view>
          </template>
        </wd-cell>
        <wd-cell title="搜索账单" is-link @click="handleMenuClick('搜索账单')">
          <template #icon>
            <view class="mr-3 flex items-center"><wd-icon name="search" size="20px" color="#D4A017" /></view>
          </template>
          <template #value>
             <view class="bg-[#444] text-white text-[10px] px-2 py-0.5 rounded-md font-bold">VIP</view>
          </template>
        </wd-cell>
        <wd-cell title="创建资产" is-link @click="handleMenuClick('创建资产')">
          <template #icon>
            <view class="mr-3 flex items-center"><wd-icon name="money-circle" size="20px" color="#4DB6AC" /></view>
          </template>
        </wd-cell>
      </view>

      <!-- 第四组 -->
      <view class="menu-group bg-white rounded-3xl overflow-hidden mb-4 shadow-sm">
        <wd-cell title="数据备份" is-link @click="handleMenuClick('数据备份')">
          <template #icon>
            <view class="mr-3 flex items-center"><wd-icon name="chart-bar" size="20px" color="#1E88E5" /></view>
          </template>
          <template #value>
             <view class="flex items-center">
               <text class="text-sm text-gray-400 mr-2">数据未备份</text>
               <view class="bg-[#444] text-white text-[10px] px-2 py-0.5 rounded-md font-bold">VIP</view>
             </view>
          </template>
        </wd-cell>
      </view>
    </view>
    
    <wd-toast />
  </view>
</template>

<script setup lang="ts">
import { useNavbar } from '@/composables/useNavbar'
import { useToast } from 'wot-design-uni'

const { statusBarHeight } = useNavbar()
const toast = useToast()

const handleGoVip = () => {
  toast.show('跳转会员开通页面')
}

const handleMenuClick = (name: string) => {
  toast.show(`点击了: ${name}`)
}
</script>

<route lang="json">
{
  "name": "mine",
  "style": { "navigationStyle": "custom" },
  "layout": "tabbar"
}
</route>

<style lang="scss" scoped>
.page {
  padding-bottom: calc(140rpx + constant(safe-area-inset-bottom));
  padding-bottom: calc(140rpx + env(safe-area-inset-bottom));
}

.vip-banner {
  background: linear-gradient(135deg, #FF7070 0%, #FF9E6A 100%);
  box-shadow: 0 10rpx 30rpx rgba(255, 112, 112, 0.3);
}

.menu-group {
  :deep(.wd-cell) {
    padding: 32rpx 24rpx;
    &::after {
      left: 100rpx !important;
    }
  }
}
</style>
