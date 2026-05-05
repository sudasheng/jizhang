<template>
  <view class="page bg-[#FDF8F8] min-h-screen pb-32">
    <!-- 自定义导航栏 -->
    <custom-navbar title="创建账单" bg-color="#FDF8F8" placeholder />

    <view class="px-4 pt-4">
      <!-- 核心表单区域 -->
      <view class="bg-white rounded-[40rpx] p-6 shadow-sm">
        <!-- 分类图标展示 -->
        <view class="flex flex-col items-center mb-8">
          <view class="w-20 h-20 rounded-full bg-[#FDF8F8] flex items-center justify-center mb-2 border border-gray-100 shadow-inner">
            <wd-icon :name="query.icon || 'app'" size="40px" color="#333" />
          </view>
        </view>

        <!-- 表单项列表 -->
        <view class="form-list">
          <!-- 分类名称与金额输入 -->
          <view class="flex justify-between items-center py-5 border-b border-dashed border-gray-100 mb-4">
            <view class="flex items-center active:opacity-60" @click="handleReselectCategory">
              <text class="text-xl font-bold text-[#333] mr-1">{{ decodedCategory }}</text>
              <wd-icon name="edit-1" size="18px" color="#ccc" />
            </view>
            <view class="flex-1 text-right" @click="handleInputAmount">
              <text class="text-2xl font-bold" :class="amount ? 'text-[#333]' : 'text-gray-300'">
                {{ amount || '0.00' }}
              </text>
              <text v-if="!amount" class="text-xs text-gray-400 block mt-1">请输入金额</text>
            </view>
          </view>

          <!-- 账单日期 -->
          <view class="flex justify-between items-center py-4 active:bg-gray-50 px-2 rounded-xl transition-colors" @click="handleDatePicker">
            <text class="text-base text-gray-500">账单日期</text>
            <view class="flex items-center">
              <text class="text-base text-[#333] font-bold mr-1">{{ formattedDate }}</text>
              <wd-icon name="arrow-right" size="16px" color="#ccc" />
            </view>
          </view>

          <!-- 收支类型 -->
          <view class="flex justify-between items-center py-4 px-2">
            <text class="text-base text-gray-500">收支类型</text>
            <view class="flex bg-gray-100 rounded-full p-1">
              <view 
                class="px-5 py-1.5 rounded-full text-sm transition-all font-bold"
                :class="type === 'expense' ? 'bg-[#FF7070] text-white shadow-sm' : 'text-gray-500'"
                @click="type = 'expense'"
              >支出</view>
              <view 
                class="px-5 py-1.5 rounded-full text-sm transition-all font-bold"
                :class="type === 'income' ? 'bg-[#4DB6AC] text-white shadow-sm' : 'text-gray-500'"
                @click="type = 'income'"
              >收入</view>
            </view>
          </view>

          <!-- 账本选择 -->
          <view class="flex justify-between items-center py-4 active:bg-gray-50 px-2 rounded-xl" @click="handleLedgerPicker">
            <text class="text-base text-gray-500">账本</text>
            <view class="flex items-center">
              <text class="text-base text-[#333] font-bold mr-1">默认账本</text>
              <wd-icon name="arrow-right" size="16px" color="#ccc" />
            </view>
          </view>

          <!-- 所属资产 -->
          <view class="flex justify-between items-center py-4 active:bg-gray-50 px-2 rounded-xl" @click="handleAssetPicker">
            <text class="text-base text-gray-500">所属资产</text>
            <view class="flex items-center">
              <text class="text-base text-[#333] font-bold mr-1">默认资产</text>
              <wd-icon name="arrow-right" size="16px" color="#ccc" />
            </view>
          </view>

          <!-- 不计入收支 -->
          <view class="flex justify-between items-center py-4 px-2 border-b border-dashed border-gray-100 mb-4">
            <text class="text-base text-gray-500">不计入收支</text>
            <wd-switch v-model="excludeFromStats" active-color="#FF7070" />
          </view>

          <!-- 备注 -->
          <view class="py-2 px-2">
            <text class="text-base text-gray-500 block mb-3 font-medium">备注</text>
            <wd-textarea 
              v-model="note" 
              placeholder="请输入备注" 
              auto-height 
              custom-style="background-color: #F8F8F8; border-radius: 20rpx; padding: 24rpx;"
            />
          </view>
        </view>
      </view>
    </view>

    <!-- 底部保存按钮 - 适配安全区 -->
    <view class="fixed-bottom-btn">
      <view class="main-btn" @click="handleSave">
        <text class="main-btn__text">保存</text>
      </view>
    </view>

    <!-- 日期选择器 - 放在隐藏容器中防止其渲染默认单元格 -->
    <view style="display: none;">
      <wd-datetime-picker
        v-model="currentDate"
        type="date"
        title="选择日期"
        @confirm="handleDateConfirm"
        ref="datePicker"
      />
    </view>
    
    <wd-toast />
  </view>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'uni-mini-router'
import { useToast } from 'wot-design-uni'
import dayjs from 'dayjs'

const router = useRouter()
const route = useRoute()
const toast = useToast()

const query = computed(() => route.query || {})
// 修复中文乱码问题
const decodedCategory = computed(() => {
  const cat = query.value.category as string
  return cat ? decodeURIComponent(cat) : '未选择'
})

const amount = ref('')
const currentDate = ref(new Date().getTime())
const formattedDate = computed(() => dayjs(currentDate.value).format('YYYY.MM.DD'))
const type = ref('expense')
const excludeFromStats = ref(false)
const note = ref('')

const datePicker = ref()

const handleReselectCategory = () => router.back()

const handleInputAmount = () => {
  uni.showInput({
    title: '输入金额',
    placeholder: '0.00',
    success: (res) => {
      if (res.content) {
        // 简单验证数字
        if (!isNaN(Number(res.content))) {
           amount.value = res.content
        } else {
           toast.show('请输入有效的数字')
        }
      }
    }
  })
}

const handleDatePicker = () => datePicker.value.open()
const handleDateConfirm = ({ value }: any) => {
  currentDate.value = value
}

const handleLedgerPicker = () => toast.show('选择账本')
const handleAssetPicker = () => toast.show('选择资产')

const handleSave = () => {
  if (!amount.value || Number(amount.value) <= 0) {
    toast.show('请输入有效的金额')
    return
  }
  toast.show('保存成功')
  setTimeout(() => {
    uni.switchTab({ url: '/pages/index/index' })
  }, 1000)
}
</script>

<style lang="scss" scoped>
.fixed-bottom-btn {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 50;
  padding: 0 32rpx;
  padding-bottom: calc(32rpx + constant(safe-area-inset-bottom));
  padding-bottom: calc(32rpx + env(safe-area-inset-bottom));
  background: linear-gradient(to top, #FDF8F8 80%, transparent 100%);
}

.main-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 96rpx;
  background-color: #FF7070;
  border-radius: 48rpx;
  box-shadow: 0 8rpx 20rpx rgba(255, 112, 112, 0.4);
  transition: all 0.2s;

  &:active {
    transform: scale(0.96);
    opacity: 0.9;
  }

  &__text {
    font-size: 32rpx;
    font-weight: bold;
    color: #fff;
  }
}

.form-list {
  background-color: transparent;
}
</style>
