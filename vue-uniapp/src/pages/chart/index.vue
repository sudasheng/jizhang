<template>
  <view class="page page--tabbar bg-[#FDF8F8] min-h-screen pb-20">
    <!-- 顶部状态栏占位 -->
    <view :style="{ height: statusBarHeight + 'px' }" class="bg-[#FDF8F8]" />

    <!-- 顶部分段器与类型切换 -->
    <view class="flex justify-between items-center px-4 py-2">
      <view class="w-10"></view>
      <view class="flex-1 flex justify-center">
        <view class="flex bg-gray-100 rounded-lg p-1 scale-90">
          <view 
            v-for="item in tabs" 
            :key="item.value"
            @click="activeTab = item.value"
            class="px-6 py-1 rounded-md text-sm transition-all"
            :class="activeTab === item.value ? 'bg-white shadow-sm font-bold text-[#333]' : 'text-gray-500'"
          >
            {{ item.label }}
          </view>
        </view>
      </view>
      <view class="flex items-center text-sm font-bold text-[#333]" @click="handleTypeClick">
        <text>{{ activeType }}</text>
        <wd-icon name="caret-down-small" size="16px" />
      </view>
    </view>

    <!-- 日期范围选择 -->
    <view class="flex justify-center items-center py-4 text-[#333]">
      <wd-icon name="arrow-left" size="14px" class="p-2" @click="handlePrevRange" />
      <text class="mx-4 text-sm font-bold" @click="handleRangePicker">5月1日 - 5月31日</text>
      <wd-icon name="arrow-right" size="14px" class="p-2" @click="handleNextRange" />
    </view>

    <view class="px-4" v-if="isDataReady">
      <!-- 汇总卡片 -->
      <view class="bg-white rounded-3xl p-6 mb-4 shadow-sm border border-gray-50">
        <view class="flex justify-between items-center mb-6">
          <view>
            <text class="text-xs text-gray-400 block mb-1">总支出</text>
            <text class="text-3xl font-bold text-[#333]">540.40</text>
          </view>
        </view>
        <view class="flex border-t border-gray-50 pt-4">
          <view class="flex-1">
            <text class="text-xs text-gray-400 block mb-1">总结余</text>
            <text class="text-lg font-bold text-[#333]">309.60</text>
          </view>
          <view class="flex-1">
            <text class="text-xs text-gray-400 block mb-1">日均支出</text>
            <text class="text-lg font-bold text-[#333]">540.40</text>
          </view>
        </view>
      </view>

      <!-- 柱状图卡片 -->
      <view class="bg-white rounded-3xl p-5 mb-4 shadow-sm">
        <view class="mb-4">
          <text class="text-base font-bold text-[#333]">每日开销柱状图</text>
        </view>
        <view class="w-full h-400rpx">
           <qiun-data-charts 
            v-if="columnData.series && columnData.series.length > 0"
            type="column"
            :opts="columnOpts"
            :chartData="columnData"
            :canvas2d="true"
            canvasId="chart_column"
          />
        </view>
      </view>

      <!-- 环形图卡片 -->
      <view class="bg-white rounded-3xl p-5 mb-4 shadow-sm">
        <view class="mb-2">
          <text class="text-base font-bold text-[#333]">支出分类详情</text>
        </view>
        <view class="w-full h-500rpx relative">
           <qiun-data-charts 
            v-if="ringData.series && ringData.series.length > 0"
            type="ring"
            :opts="ringOpts"
            :chartData="ringData"
            :canvas2d="true"
            canvasId="chart_ring"
          />
        </view>
        
        <!-- 分类列表 -->
        <view class="mt-4">
          <view v-for="(item, index) in categoryList" :key="index" class="mb-6">
            <view class="flex items-center mb-2">
              <view class="w-10 h-10 rounded-full flex items-center justify-center mr-3" :style="{ backgroundColor: item.bgColor }">
                <wd-icon :name="item.icon" size="20px" :color="item.color" />
              </view>
              <view class="flex-1">
                <view class="flex justify-between items-center mb-1">
                  <view class="flex items-center">
                    <text class="text-sm font-bold text-[#333] mr-2">{{ item.name }}</text>
                    <text class="text-xs text-gray-400">{{ item.percent }}%</text>
                  </view>
                  <text class="text-sm font-bold text-[#333]">-¥{{ item.amount.toFixed(2) }}</text>
                </view>
                <!-- 进度条 -->
                <view class="h-1.5 bg-gray-100 rounded-full overflow-hidden">
                  <view 
                    class="h-full rounded-full transition-all duration-1000" 
                    :style="{ width: item.percent + '%', backgroundColor: item.color }"
                  />
                </view>
              </view>
            </view>
          </view>
        </view>
      </view>
    </view>
    
    <wd-toast />
  </view>
</template>

<script setup lang="ts">
import { ref, onMounted, nextTick } from 'vue'
import { useNavbar } from '@/composables/useNavbar'
import { useToast } from 'wot-design-uni'

const { statusBarHeight } = useNavbar()
const toast = useToast()
const isDataReady = ref(false)

const tabs = [
  { label: '周', value: 'week' },
  { label: '月', value: 'month' },
  { label: '年', value: 'year' }
]
const activeTab = ref('month')
const activeType = ref('支出')

// 柱状图数据
const columnData = ref<any>({ categories: [], series: [] })
const columnOpts = ref({
  padding: [15, 15, 0, 5],
  legend: { show: false },
  xAxis: { disableGrid: true, fontSize: 10 },
  yAxis: { disabled: true, disableGrid: true },
  extra: {
    column: {
      type: "group",
      width: 10,
      linearType: "vertical",
      customColor: ["#FF7070"]
    }
  }
})

// 环形图数据
const ringData = ref<any>({ series: [] })
const ringOpts = ref({
  legend: { show: true, position: "right", lineHeight: 25 },
  title: { name: "540.40", fontSize: 20, color: "#333" },
  subtitle: { name: "总支出", fontSize: 12, color: "#999" },
  extra: {
    ring: {
      ringWidth: 30,
      labelWidth: 15,
      border: false,
    }
  }
})

const categoryList = ref([
  { name: '其他', percent: 35.16, amount: 190.00, icon: 'help-circle', color: '#A78BFA', bgColor: '#F5F3FF' },
  { name: '数码', percent: 24.98, amount: 135.00, icon: 'laptop', color: '#F87171', bgColor: '#FEF2F2' },
  { name: '水果', percent: 18.50, amount: 100.00, icon: 'app', color: '#FBBF24', bgColor: '#FFFBEB' },
  { name: '衣服', percent: 16.65, amount: 90.00, icon: 'app', color: '#FB923C', bgColor: '#FFF7ED' },
])

onMounted(async () => {
  await nextTick()
  // 模拟异步加载数据，解决初次进入页面不渲染图表的问题
  setTimeout(() => {
    columnData.value = {
      categories: ["1", "3", "5", "7", "9", "11", "13", "15", "17", "19", "21", "23", "25", "27", "29", "31"],
      series: [{ name: "支出", data: [0, 0, 540.40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], color: "#FF7070" }]
    }
    ringData.value = {
      series: [{
        data: [
          { name: "其他", value: 35.16, color: "#A78BFA" },
          { name: "数码", value: 24.98, color: "#F87171" },
          { name: "水果", value: 18.5, color: "#FBBF24" },
          { name: "衣服", value: 16.65, color: "#FB923C" },
        ]
      }]
    }
    isDataReady.value = true
  }, 500)
})

const handlePrevRange = () => toast.show('上一周期')
const handleNextRange = () => toast.show('下一周期')
const handleRangePicker = () => toast.show('选择日期范围')
const handleTypeClick = () => toast.show('切换收支类型')
</script>

<route lang="json">
{
  "name": "chart",
  "style": { "navigationStyle": "custom" },
  "layout": "tabbar"
}
</route>

<style lang="scss" scoped>
.page {
  padding-bottom: calc(140rpx + constant(safe-area-inset-bottom));
  padding-bottom: calc(140rpx + env(safe-area-inset-bottom));
}
</style>
