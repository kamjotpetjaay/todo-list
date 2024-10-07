import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
  state: {
    tasks: []
  },
  mutations: {
    setTasks(state, tasks) {
      const formattedTasks = tasks.map((task) => ({
        ...task,
        priority: task.priority === 3 ? 'alto' : task.priority === 2 ? 'medio' : 'bajo',
        state: task.state ? 'Completo' : 'Incompleto'
      }))
      state.tasks = formattedTasks
    },
    addTask(state, task) {
      state.tasks.push({
        ...task,
        priority: task.priority === 3 ? 'alto' : task.priority === 2 ? 'medio' : 'bajo',
        state: task.state ? 'Completo' : 'Incompleto'
      })
    },
    updateTask(state, updatedTask) {
      const index = state.tasks.findIndex((task) => task.id === updatedTask.id)
      if (index !== -1) {
        state.tasks.splice(index, 1, {
          ...updatedTask,
          priority:
            updatedTask.priority === 3 ? 'alto' : updatedTask.priority === 2 ? 'medio' : 'bajo',
          state: updatedTask.state ? 'Completo' : 'Incompleto'
        })
      }
    },
    deleteTask(state, taskId) {
      state.tasks = state.tasks.filter((task) => task.taskId !== taskId)
    }
  },
  actions: {
    async fetchTasks({ commit }) {
      const response = await axios.get('http://localhost:8080/tasks')
      commit('setTasks', response.data)
    },
    async createTask({ commit }, task) {
      if (!task.title || !task.priority || !task.state) {
        throw new Error('faltan campos')
      }
      const payload = {
        title: task.title,
        priority: task.priority === 'alto' ? 3 : task.priority === 'medio' ? 2 : 1,
        state: task.state === 'Completo'
      }
      const response = await axios.post('http://localhost:8080/tasks', payload)
      console.log(response)
      commit('addTask', response.data)
    },
    async updateTask({ commit }, task) {
      const payload = {
        ...task,
        priority: task.priority === 'alto' ? 3 : task.priority === 'medio' ? 2 : 1,
        state: task.state === 'Completo'
      }
      const response = await axios.put(`http://localhost:8080/tasks/${task.taskId}`, payload)
      commit('updateTask', response.data)
    },
    async deleteTask({ commit }, taskId) {
      console.log(taskId)
      await axios.delete(`http://localhost:8080/tasks/${taskId}`)
      commit('deleteTask', taskId)
    }
  }
})
