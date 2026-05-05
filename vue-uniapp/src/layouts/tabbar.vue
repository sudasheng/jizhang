<template>
  <wd-config-provider :theme-vars="themeVars" :custom-class="theme" :theme="theme">
    <slot />
    <view class="tabbar-container">
      <wd-tabbar
        :model-value="activeTabbar.name"
        :bordered="false"
        safe-area-inset-bottom
        fixed
        @change="handleTabbarChange"
        custom-class="custom-tabbar-style"
      >
        <block v-for="(item, index) in tabbarList" :key="index">
          <wd-tabbar-item
            v-if="item.name !== 'ai'"
            :name="item.name"
            :value="getTabbarItemValue(item.name)"
            :title="item.title"
            :icon="item.icon"
          />
          <view v-else class="tabbar-center-item" @click="handleTabbarChange({ value: 'ai' })">
            <view class="center-button">
              <wd-icon name="add" size="28px" color="#fff" />
            </view>
            <text class="center-title">{{ item.title }}</text>
          </view>
        </block>
      </wd-tabbar>
    </view>
    <wd-notify />
    <wd-toast />
    <wd-message-box />
  </wd-config-provider>
</template>

<script setup lang="ts">
import { useThemeStore } from "@/store";
import { useTabbar } from "@/composables/useTabbar";
import { useRouter, useRoute } from "uni-mini-router";
import { storeToRefs } from "pinia";
import { watch, onMounted } from "vue";

const router = useRouter();
const route = useRoute();
const themeStore = useThemeStore();
const { themeVars, theme } = storeToRefs(themeStore);
const { activeTabbar, getTabbarItemValue, setTabbarItemActive, tabbarList } = useTabbar();

function handleTabbarChange({ value }: { value: string }) {
  setTabbarItemActive(value);
  router.pushTab({ name: value });
}

// 监听路由变化更新 tabbar 激活状态
watch(
  () => route.name,
  (name) => {
    if (name && name !== activeTabbar.value.name) {
      setTabbarItemActive(name);
    }
  },
  { immediate: true }
);

onMounted(() => {
  // #ifdef APP-PLUS
  uni.hideTabBar();
  // #endif
});

defineOptions({
  addGlobalClass: true,
  virtualHost: true,
  styleIsolation: "shared",
});
</script>

<style lang="scss" scoped>
.tabbar-container {
  :deep(.wd-tabbar) {
    display: flex;
    align-items: center;
    justify-content: space-around;
    box-shadow: 0 -2rpx 10rpx rgba(0, 0, 0, 0.05);
  }
}

.tabbar-center-item {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 20%;
  height: 100%;
  margin-top: -30rpx;
}

.center-button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 96rpx;
  height: 96rpx;
  background-color: #FF7070;
  border: 8rpx solid #fff;
  border-radius: 50%;
  box-shadow: 0 4rpx 12rpx rgba(255, 112, 112, 0.4);
}

.center-title {
  margin-top: 4rpx;
  font-size: 20rpx;
  color: #333;
}
</style>
