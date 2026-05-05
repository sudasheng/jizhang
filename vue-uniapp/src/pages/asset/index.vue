<template>
  <view class="page page--tabbar bg-white min-h-screen pb-20">
    <!-- 顶部状态栏占位 -->
    <view :style="{ height: statusBarHeight + 'px' }" class="bg-white" />

    <view class="px-4 pt-4">
      <!-- 净资产汇总卡片 -->
      <view class="asset-summary-card rounded-[40rpx] p-6 mb-8 relative">
        <view class="flex justify-between items-start mb-4">
          <view>
            <text class="text-sm text-gray-500 block mb-2 font-medium">净资产</text>
            <view class="flex items-center">
              <text class="text-4xl font-bold text-[#333] mr-2">{{ isVisible ? '10.00' : '****' }}</text>
              <view class="p-2 active:opacity-60" @click="isVisible = !isVisible">
                <wd-icon :name="isVisible ? 'eye' : 'eye-close'" size="20px" color="#333" />
              </view>
            </view>
          </view>
        </view>
        
        <view class="border-t border-dashed border-gray-200 pt-4 flex">
          <view class="flex-1">
            <text class="text-xs text-gray-400 block mb-1">总资产</text>
            <text class="text-lg font-bold text-[#333]">{{ isVisible ? '10.00' : '****' }}</text>
          </view>
          <view class="flex-1 text-right pr-4">
            <text class="text-xs text-gray-400 block mb-1">总负债</text>
            <text class="text-lg font-bold text-[#333]">{{ isVisible ? '0.00' : '****' }}</text>
          </view>
        </view>
      </view>

      <!-- 资产账户列表头部 -->
      <view class="flex justify-between items-center mb-6">
        <text class="text-xl font-bold text-[#333]">资产账户</text>
        <view class="add-account-btn flex items-center px-4 py-1.5 active:scale-95 transition-transform" @click="handleAddAccount">
          <text class="text-sm font-bold text-[#333]">+ 添加账户</text>
        </view>
      </view>

      <!-- 账户列表 -->
      <view class="account-list">
        <view 
          v-for="(item, index) in accounts" 
          :key="index" 
          class="flex items-center p-4 mb-4 bg-white rounded-3xl shadow-sm border border-gray-50 active:bg-gray-50"
          @click="handleAccountClick(item)"
        >
          <view class="w-12 h-12 rounded-full bg-[#FFF9F0] flex items-center justify-center mr-4">
            <wd-icon :name="item.icon" size="26px" color="#D4A017" />
          </view>
          <view class="flex-1">
            <text class="text-base font-bold text-[#333] block mb-0.5">{{ item.name }}</text>
            <text class="text-xs text-gray-400">{{ item.type }}</text>
          </view>
          <view class="text-right">
            <text class="text-lg font-bold text-[#333]">{{ isVisible ? item.amount.toFixed(2) : '****' }}</text>
          </view>
        </view>
      </view>
    </view>
    
    <wd-toast />
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useNavbar } from '@/composables/useNavbar'
import { useToast } from 'wot-design-uni'

const { statusBarHeight } = useNavbar()
const toast = useToast()

const isVisible = ref(true)

const accounts = ref([
  { name: 'aa', type: '其他', amount: 10.00, icon: 'app' }
])

const handleAddAccount = () => {
  toast.show('添加资产账户')
}

const handleAccountClick = (item: any) => {
  toast.show(`账户详情: ${item.name}`)
}
</script>

<route lang="json">
{
  "name": "asset",
  "style": { "navigationStyle": "custom" },
  "layout": "tabbar"
}
</route>

<style lang="scss" scoped>
.page {
  padding-bottom: calc(160rpx + constant(safe-area-inset-bottom));
  padding-bottom: calc(160rpx + env(safe-area-inset-bottom));
}

.asset-summary-card {
  background: #FFFFFF;
  border: 4rpx solid #FFDCDC;
  box-shadow: 0 8rpx 30rpx rgba(255, 220, 220, 0.4);
}

.add-account-btn {
  background-color: #FFE082;
  border-radius: 100rpx;
}

.account-list {
  background-color: transparent;
}
</style>
