<script lang="ts">
import { h } from 'vue'
import { ElMenuItem, ElSubMenu, ElIcon } from 'element-plus-secondary'
import auth from '@/assets/svg/auth.svg'
import association from '@/assets/svg/association.svg'
import threshold from '@/assets/svg/threshold.svg'
import org from '@/assets/svg/org.svg'
import peoples from '@/assets/svg/peoples.svg'
import report from '@/assets/svg/report.svg'
import sync from '@/assets/svg/sync.svg'
import appearance from '@/assets/svg/appearance.svg'
import authentication from '@/assets/svg/authentication.svg'
import embedded from '@/assets/svg/embedded.svg'
import platform from '@/assets/svg/platform.svg'
import plugin from '@/assets/svg/plugin.svg'
import sysParameter from '@/assets/svg/sys-parameter.svg'
import variable from '@/assets/svg/variable.svg'
import watermark from '@/assets/svg/watermark.svg'
import icon_font from '@/assets/svg/icon_font.svg'
import icon_msg_fill from '@/assets/svg/icon_msg_fill.svg'
import icon_free from '@/assets/svg/icon_free.svg'
import icon_security from '@/assets/svg/icon_security.svg'
import icon_webhook from '@/assets/svg/icon_webhook.svg'
import icon_template from '@/assets/svg/icon_template.svg'
import log from '@/assets/svg/log.svg'

const iconMap = {
  appearance: appearance,
  authentication: authentication,
  embedded: embedded,
  platform: platform,
  plugin: plugin,
  'sys-parameter': sysParameter,
  variable: variable,
  watermark: watermark,
  icon_font: icon_font,
  icon_msg_fill: icon_msg_fill,
  icon_free: icon_free,
  icon_security,
  icon_webhook,
  auth: auth,
  association: association,
  threshold: threshold,
  org: org,
  peoples: peoples,
  report: report,
  sync: sync,
  icon_template,
  log
}

const titleWithIcon = props => {
  const { title, icon } = props.menu?.meta || {}
  return [
    h(ElIcon, null, { default: () => h(iconMap[icon], { className: 'svg-icon logo' }) }),
    h('span', null, { default: () => title })
  ]
}

const MenuItem = props => {
  const { children, hidden, path } = props.menu
  if (hidden) {
    return null
  }
  if (children?.length) {
    return h(
      ElSubMenu,
      { index: path },
      {
        title: () => titleWithIcon(props),
        default: () => children.map(ele => h(MenuItem, { menu: ele }))
      }
    )
  }
  const { title, icon } = props.menu?.meta || {}
  return h(
    ElMenuItem,
    { index: path },
    {
      title: h('span', null, { default: () => title }),
      default: h(iconMap[icon] ? ElIcon : null, null, {
        default: () => h(iconMap[icon], { className: 'svg-icon logo' })
      })
    }
  )
}
export default MenuItem
</script>
