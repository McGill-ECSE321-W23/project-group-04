import {createApp} from 'vue'
import App from './App.vue'
import router from './router'

import './assets/main.css'

// main.ts
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080',
    withCredentials: true,
    headers: {
        "Access-Control-Allow-Origin": 'localhost:5173'
    }
});

const app = createApp(App)
app.use(ElementPlus)
app.use(ElementPlus, { size: 'small', zIndex: 3000 })

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(router)

app.provide('axios', api)

app.mount('#app')
