<script lang="tsx" setup>
import { computed, reactive, watch } from 'vue'
import { useI18n } from '@/hooks/web/useI18n'
import { reverseColor } from '../util/util'
import { ArrowRight } from '@element-plus/icons-vue'
import { dvMainStoreWithOut } from '@/store/modules/data-visualization/dvMain'
const dvMainStore = dvMainStoreWithOut()

const { t } = useI18n()

const props = defineProps({
  drillFilters: {
    type: Array,
    default: () => []
  },
  themeStyle: {
    type: Object,
    required: false,
    default: null
  },
  disabled: {
    type: Boolean,
    required: false,
    default: false
  }
})

const emit = defineEmits(['onDrillJump'])

const state = reactive({
  textColor: '#bbbfc4'
})

const textColor = computed(
  () => dvMainStore.canvasStyleData.component.seniorStyleSetting.drillLayerColor
)

// watch(
//   [() => props.themeStyle?.backgroundColorSelect, () => props.themeStyle?.color],
//   () => {
//     loadThemeStyle()
//   },
//   { deep: true }
// )

const drillJump = index => {
  if (index < props.drillFilters.length) {
    emit('onDrillJump', index)
  }
}
const loadThemeStyle = () => {
  let themeStyle = null
  if (props.themeStyle) {
    themeStyle = JSON.parse(JSON.stringify(props.themeStyle))
    if (themeStyle && themeStyle.commonBackground) {
      const viewBGColor = themeStyle.commonBackground.color
      if (viewBGColor !== '#FFFFFF') {
        const reverseValue = reverseColor(viewBGColor)
        state.textColor = reverseValue
      } else {
        state.textColor = null
      }
    }
    if (themeStyle && themeStyle.backgroundColorSelect) {
      const panelColor = themeStyle.color
      if (panelColor !== '#FFFFFF') {
        const reverseValue = reverseColor(panelColor)
        state.textColor = reverseValue
      } else {
        state.textColor = null
      }
    }
  }
}
const drillPathVar = computed(() => [{ '--drill-color': textColor.value }])
</script>

<template>
  <div
    v-if="props.drillFilters && props.drillFilters.length > 0"
    class="drill"
    :style="drillPathVar"
    :class="{ noClick: disabled }"
  >
    <el-breadcrumb :separator-icon="ArrowRight" class="drill-style">
      <el-breadcrumb-item class="drill-item" @click="drillJump(0)">
        <span :style="{ color: textColor }">{{ t('commons.all') }}</span>
      </el-breadcrumb-item>
      <el-breadcrumb-item
        v-for="(filter, index) in props.drillFilters"
        :key="index"
        class="drill-item"
        @click="drillJump(index + 1)"
      >
        <span class="item-name" :style="{ color: textColor }" :title="filter.value[0]">{{
          filter.value[0]
        }}</span>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<style lang="less" scoped>
.drill-style {
  font-size: 12px;
}
.drill-style :deep(.el-breadcrumb__separator) {
  margin: 0 !important;
}
.drill-item {
  cursor: pointer;
  .item-name {
    max-width: 200px;
    display: inline-block;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
  }
}
.drill {
  z-index: 1;
  height: 20px;
  padding: 0 16px;
  ::v-deep(.ed-icon) {
    color: var(--drill-color) !important;
  }
}
.noClick {
  pointer-events: none; /* 禁止鼠标点击 */
}
</style>
