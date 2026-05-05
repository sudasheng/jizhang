<template>
  <view class="page bg-[#FDF8F8] min-h-screen pb-24">
    <!-- 自定义导航栏 -->
    <custom-navbar title="分类管理" bg-color="#FDF8F8" placeholder />

    <view class="px-4 pt-4">
      <view v-for="(group, gIndex) in categoryGroups" :key="gIndex" class="mb-4">
        <!-- 分类组标题 -->
        <view class="bg-white rounded-3xl p-4 flex justify-between items-center shadow-sm active:bg-gray-50 transition-colors" @click="toggleGroup(gIndex)">
          <view class="flex items-center">
            <view class="w-10 h-10 rounded-full bg-[#FFF9F0] flex items-center justify-center mr-3">
              <wd-icon :name="group.icon" size="24px" color="#D4A017" />
            </view>
            <text class="text-base font-bold text-[#333]">{{ group.name }}</text>
          </view>
          <wd-icon :name="group.expanded ? 'arrow-up' : 'arrow-down'" size="16px" color="#ccc" />
        </view>

        <!-- 子分类网格 -->
        <view v-if="group.expanded" class="bg-white rounded-3xl mt-2 p-5 shadow-sm animate-fade-in">
          <view class="grid grid-cols-4 gap-y-8">
            <view 
              v-for="(item, iIndex) in group.children" 
              :key="iIndex" 
              class="flex flex-col items-center active:scale-90 transition-transform"
              @click="handleCategorySelect(item)"
            >
              <view class="w-14 h-14 rounded-full bg-[#FDF8F8] flex items-center justify-center mb-2 overflow-hidden border border-gray-50 shadow-sm">
                <wd-icon :name="item.icon" size="28px" color="#333" />
              </view>
              <text class="text-xs text-[#333] font-medium">{{ item.name }}</text>
            </view>
            <!-- 新建按钮 -->
            <view class="flex flex-col items-center active:scale-90 transition-transform" @click="handleCreate">
              <view class="w-14 h-14 rounded-full bg-white flex items-center justify-center mb-2 border border-dashed border-gray-300">
                <wd-icon name="add" size="24px" color="#ccc" />
              </view>
              <text class="text-xs text-gray-400">新建</text>
            </view>
          </view>
        </view>
      </view>
    </view>

    <!-- 底部按钮 - 适配安全区 -->
    <view class="fixed-bottom-btn">
      <view class="main-btn" @click="handleCreateMain">
        <text class="main-btn__text">添加一级分类</text>
      </view>
    </view>
    
    <wd-toast />
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'uni-mini-router'
import { useToast } from 'wot-design-uni'

const router = useRouter()
const toast = useToast()

const categoryGroups = ref([
  {
    name: '食品餐饮',
    icon: 'app',
    expanded: true,
    children: [
      { name: '三餐', icon: 'app' },
      { name: '零食', icon: 'app' },
      { name: '饮品', icon: 'app' },
      { name: '蔬菜', icon: 'app' },
      { name: '水果', icon: 'app' },
      { name: '夜宵', icon: 'app' },
      { name: '肉类', icon: 'app' },
    ]
  },
  { name: '购物消费', icon: 'order', expanded: false, children: [] },
  { name: '出行交通', icon: 'computer', expanded: false, children: [] },
  { name: '休闲娱乐', icon: 'chart-bar', expanded: false, children: [] },
  { name: '居家生活', icon: 'home', expanded: false, children: [] },
])

const toggleGroup = (index: number) => {
  categoryGroups.value[index].expanded = !categoryGroups.value[index].expanded
}

const handleCategorySelect = (item: any) => {
  router.push({
    path: '/pages/add-transaction/index',
    query: { category: item.name, icon: item.icon }
  })
}

const handleCreate = () => toast.show('新建子分类')
const handleCreateMain = () => toast.show('添加一级分类')
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

.animate-fade-in {
  animation: fadeIn 0.3s ease-out;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(-10rpx); }
  to { opacity: 1; transform: translateY(0); }
}
</style>
