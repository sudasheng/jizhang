<template>
  <view class="page page--tabbar bg-white min-h-screen">
    <!-- 自定义导航栏 -->
    <custom-navbar :show-back="false" bg-color="#FFFFFF" placeholder>
      <template #left>
        <view class="ml-4 active:opacity-60 transition-opacity" @click="handleCalendarClick">
          <wd-icon name="calendar" size="24px" color="#333" />
        </view>
      </template>
      <template #center>
        <view class="flex items-center active:opacity-60 transition-opacity" @click="handleLedgerClick">
          <text class="text-lg font-bold text-[#333]">{{ currentLedgerLabel }}</text>
          <wd-icon name="caret-down-small" size="18px" color="#333" class="ml-1" />
        </view>
      </template>
      <template #right>
        <view class="mr-4 active:opacity-60 transition-opacity" @click="handleSearchClick">
          <wd-icon name="search" size="24px" color="#333" />
        </view>
      </template>
    </custom-navbar>

    <view class="px-4 pt-2">
      <!-- 月度收支卡片 -->
      <view class="summary-card rounded-[40rpx] p-6 mb-5 relative overflow-hidden">
        <view class="flex justify-center items-center mb-6 text-[#333]">
          <view class="p-2 active:opacity-50" @click="handlePrevMonth">
            <wd-icon name="arrow-left" size="14px" />
          </view>
          <view class="mx-6 flex items-center active:opacity-60" @click="handleMonthPickerOpen">
            <text class="text-base font-bold">{{ formattedMonth }}</text>
          </view>
          <view class="p-2 active:opacity-50" @click="handleNextMonth">
            <wd-icon name="arrow-right" size="14px" />
          </view>
        </view>
        
        <view class="flex justify-between items-start relative z-10">
          <view class="flex-1">
            <view class="mb-5 flex justify-between items-end">
              <view>
                <text class="text-xs text-gray-500 block mb-1">本月支出</text>
                <text class="text-4xl font-bold text-[#333] leading-none">540.40</text>
              </view>
              <view class="text-right mr-4 mb-1">
                 <view class="mb-2">
                  <text class="text-xs text-gray-400 mr-2">本月收入</text>
                  <text class="text-lg font-bold text-[#333]">850.00</text>
                </view>
                <view>
                  <text class="text-xs text-gray-400 mr-2">本月结余</text>
                  <text class="text-lg font-bold text-[#333]">309.60</text>
                </view>
              </view>
            </view>
          </view>
        </view>
        
        <!-- 背景装饰 -->
        <view class="absolute right-0 bottom-0 w-36 h-36 opacity-80 pointer-events-none">
           <image src="https://img.js.design/assets/img/646db4a5b4b1a41f6e6e2f14.png" mode="aspectFit" class="w-full h-full translate-x-4 translate-y-4" />
        </view>
      </view>

      <!-- 预算卡片 -->
      <view class="budget-card rounded-3xl p-5 mb-6 shadow-sm active:scale-[0.98] transition-transform" @click="handleBudgetClick">
        <view class="flex justify-between items-center mb-4">
          <text class="text-sm font-bold text-[#333]">本月预算</text>
          <view class="bg-[#FFF2CC] text-[#D4A017] text-xs px-2.5 py-0.5 rounded-full font-bold">0%</view>
        </view>
        
        <!-- 进度条 -->
        <view class="h-2 bg-gray-100 rounded-full mb-4 relative">
          <view class="absolute left-0 top-0 h-full bg-[#FFBABA] w-[5%] rounded-full" />
          <view class="absolute left-[5%] top-[-14rpx] transition-all duration-300 transform translate-x-[-50%]">
            <view class="w-8 h-8 flex items-center justify-center">
               <text class="text-lg">🍑</text>
            </view>
          </view>
        </view>

        <view class="flex justify-between text-xs text-gray-400">
          <text>剩余预算 未设置</text>
          <text>日均可用 0.00</text>
        </view>
      </view>

      <!-- 收支记录标题 -->
      <view class="mb-4 mt-8 px-1">
        <text class="text-lg font-bold text-[#333]">{{ formattedMonth }}收支记录</text>
      </view>

      <!-- 按日期分组 -->
      <view class="transaction-group mb-8">
        <view class="date-header flex justify-between items-center py-2 px-4 mb-2">
          <view class="flex items-center">
            <text class="mr-2 text-[#333] font-bold text-sm">昨天</text>
            <text class="text-gray-400 text-xs font-medium">周一</text>
          </view>
          <view class="text-gray-400 text-[10px]">
            <text>支出 -540.40 | 收入 +850.00</text>
          </view>
        </view>

        <!-- 记录项 -->
        <view v-for="(item, index) in transactions" :key="index" class="flex items-center py-4 px-2 hover:bg-gray-50 active:bg-gray-100 transition-colors duration-200" @click="handleTransactionClick(item)">
          <view class="w-12 h-12 rounded-full bg-[#E3F2FD] flex items-center justify-center mr-4">
            <wd-icon :name="item.icon" size="26px" color="#1E88E5" />
          </view>
          <view class="flex-1 flex justify-between items-center border-b border-gray-50 pb-4" :class="{'border-none': index === transactions.length - 1}">
            <view>
              <text class="text-base font-bold text-[#333] block mb-0.5">{{ item.category }}</text>
              <text class="text-xs text-gray-400">{{ item.note }}</text>
            </view>
            <view class="text-right">
              <text class="text-lg font-bold text-[#333]">
                {{ item.amount > 0 ? '+' : '' }}{{ item.amount.toFixed(2) }}元
              </text>
            </view>
          </view>
        </view>
      </view>
    </view>

    <!-- 悬浮按钮 -->
    <view class="fixed right-6 bottom-[300rpx] flex flex-col items-center z-50 animate-bounce-subtle">
      <view class="manual-btn shadow-lg flex flex-col items-center justify-center border-[6rpx] border-white active:scale-90 transition-transform duration-200" @click="handleManualAdd">
        <wd-icon name="edit-1" size="24px" color="#333" />
        <text class="text-[10px] font-bold mt-[-2px]">手动记</text>
      </view>
    </view>
    
    <!-- 功能组件 -->
    
    <!-- 月份选择器 -->
    <wd-datetime-picker
      v-model="currentDate"
      type="year-month"
      title="选择月份"
      @confirm="handleMonthConfirm"
      ref="monthPicker"
    />

    <!-- 日历选择器 (用于左上角日历按钮) -->
    <wd-calendar
      v-model="calendarValue"
      @confirm="handleCalendarConfirm"
      ref="calendar"
    />

    <!-- 账本选择器 -->
    <wd-select-picker
      v-model="ledgerValue"
      :columns="ledgers"
      title="切换账本"
      @confirm="handleLedgerConfirm"
      ref="ledgerPicker"
    />

    <!-- 搜索框弹出层 -->
    <wd-popup v-model="showSearch" position="top" custom-style="height: 120rpx; padding-top: 100rpx;">
      <view class="px-4 pb-2 bg-white flex items-center">
        <wd-search v-model="searchKeyword" placeholder="搜索账单" hide-cancel @search="onSearch" class="flex-1" />
        <text class="ml-3 text-sm text-blue-500" @click="showSearch = false">取消</text>
      </view>
    </wd-popup>
    
    <wd-toast />
  </view>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'uni-mini-router'
import { useToast } from 'wot-design-uni'
import dayjs from 'dayjs'

const router = useRouter()
const toast = useToast()

// 数据状态
const transactions = ref([
  { category: '衣服', note: '衣服', amount: -90.00, icon: 'app' },
  { category: '其他', note: '域名', amount: -80.00, icon: 'help-circle' },
  { category: '其他', note: '域名', amount: -60.00, icon: 'help-circle' },
  { category: '其他', note: '域名', amount: -50.00, icon: 'help-circle' },
])

// 日期管理
const currentDate = ref(new Date('2026-05-01').getTime())
const formattedMonth = computed(() => dayjs(currentDate.value).format('YYYY年MM月'))

// 账本管理
const ledgerValue = ref('1')
const ledgers = ref([
  { value: '1', label: '默认账本' },
  { value: '2', label: '日常开支' },
  { value: '3', label: '旅行账本' },
  { value: '4', label: '人情往来' }
])
const currentLedgerLabel = computed(() => {
  const ledger = ledgers.value.find(l => l.value === ledgerValue.value)
  return ledger ? ledger.label : '默认账本'
})

// 搜索管理
const showSearch = ref(false)
const searchKeyword = ref('')

// 日历管理
const calendarValue = ref<number | number[]>(new Date('2026-05-05').getTime())

// 引用
const monthPicker = ref()
const calendar = ref()
const ledgerPicker = ref()

// 交互处理

// 1. 日历功能
const handleCalendarClick = () => {
  calendar.value.open()
}
const handleCalendarConfirm = ({ value }: any) => {
  const dateStr = dayjs(value).format('YYYY-MM-DD')
  toast.show(`跳转到日期: ${dateStr}`)
}

// 2. 账本切换
const handleLedgerClick = () => {
  ledgerPicker.value.open()
}
const handleLedgerConfirm = ({ value, selectedItem }: any) => {
  ledgerValue.value = value
  toast.show(`已切换至: ${selectedItem.label}`)
}

// 3. 搜索功能
const handleSearchClick = () => {
  showSearch.value = true
}
const onSearch = () => {
  toast.show(`搜索: ${searchKeyword.value}`)
  showSearch.value = false
}

// 4. 月份切换与选择
const handleMonthPickerOpen = () => {
  monthPicker.value.open()
}
const handleMonthConfirm = ({ value }: any) => {
  currentDate.value = value
  toast.show(`已加载 ${dayjs(value).format('YYYY年MM月')} 数据`)
}

const handlePrevMonth = () => {
  currentDate.value = dayjs(currentDate.value).subtract(1, 'month').valueOf()
  toast.show(`已切换至 ${dayjs(currentDate.value).format('YYYY年MM月')}`)
}

const handleNextMonth = () => {
  currentDate.value = dayjs(currentDate.value).add(1, 'month').valueOf()
  toast.show(`已切换至 ${dayjs(currentDate.value).format('YYYY年MM月')}`)
}

// 其他交互
const handleBudgetClick = () => {
  toast.show('跳转预算设置页面')
}

const handleTransactionClick = (item: any) => {
  toast.show(`账单详情: ${item.category} ${item.amount}元`)
}

const handleManualAdd = () => {
  router.push({ path: '/pages/category/index' })
}
</script>

<route lang="json">
{
  "name": "index",
  "style": { "navigationStyle": "custom" },
  "layout": "tabbar"
}
</route>

<style lang="scss" scoped>
.page {
  padding-bottom: calc(180rpx + constant(safe-area-inset-bottom));
  padding-bottom: calc(180rpx + env(safe-area-inset-bottom));
}

.summary-card {
  background: #FFFFFF;
  border: 4rpx solid #FFDCDC;
  box-shadow: 0 8rpx 30rpx rgba(255, 220, 220, 0.4);
}

.budget-card {
  background: #FFF9F9;
  border: 2rpx solid #FFEBEB;
}

.date-header {
  background-color: #F8F8F8;
  border-radius: 100rpx;
}

.manual-btn {
  width: 110rpx;
  height: 110rpx;
  background-color: #FDD835;
  border-radius: 50%;
}

@keyframes bounce-subtle {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-10rpx); }
}

.animate-bounce-subtle {
  animation: bounce-subtle 3s infinite ease-in-out;
}
</style>
