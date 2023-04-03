import {createApp} from 'vue'
import App from './App.vue'
import router from './router'

import './assets/main.css'

// main.ts
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App)
app.use(ElementPlus)
app.use(ElementPlus, { size: 'small', zIndex: 3000 })

app.use(router)

app.mount('#app')
