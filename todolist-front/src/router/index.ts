import { createRouter, createWebHistory } from 'vue-router'
import Tasks from '@/views/Tasks.vue'
import CreateTask from '@/views/CreateTask.vue'
import UpdateTask from '@/views/UpdateTask.vue'
import About from '@/views/About.vue'

const routes = [
  { path: '/', component: Tasks },
  { path: '/create', component: CreateTask },
  { path: '/update/:id', component: UpdateTask },
  { path: '/about', component: About }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
