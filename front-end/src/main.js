import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'; 
import router from "./router";
import '@/css/main.css'

createApp(App).use(router).mount('#app')
