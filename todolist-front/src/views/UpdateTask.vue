<template>
  <div class="container mt-5">
    <div class="card" v-if="task">
      <div class="card-header">
        <h4>Editar Tarea</h4>
      </div>
      <div class="card-body">
        <form @submit.prevent="submitTask">
          <div class="mb-3">
            <label for="">Tarea: </label>
            <input type="text" v-model="task.title" class="form-control" />
          </div>
          <div class="mb-3">
            <label for="">Prioridad: </label>
            <select v-model="task.priority">
              <option value="alto">Alta</option>
              <option value="medio">Media</option>
              <option value="bajo">Baja</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="">Estado: </label>
            <select v-model="task.state">
              <option value="Incompleto">No completada</option>
              <option value="Completo">Completada</option>
            </select>
          </div>
          <div class="mb-3">
            <button class="btn btn-danger btn-task" type="button" @click="returnBack()">
              Cancelar
            </button>
            <button class="btn btn-primary" type="submit">Actualizar Tarea</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
  data() {
    return {
      task: null
    }
  },
  computed: {
    ...mapState(['tasks'])
  },
  methods: {
    ...mapActions(['updateTask', 'fetchTasks']),
    loadTask() {
      const taskId = this.$route.params.id
      const foundTask = this.tasks.find((task) => task.taskId == taskId)
      if (foundTask) {
        this.task = { ...foundTask }
      }
    },
    submitTask() {
      if (this.task.title) {
        this.updateTask(this.task).then(() => {
          this.$router.push('/')
        })
      } else {
        alert('Task title is required')
      }
    },
    returnBack() {
      this.$router.push('/')
    }
  },
  created() {
    if (!this.tasks.length) {
      this.fetchTasks().then(this.loadTask)
    } else {
      this.loadTask()
    }
  }
}
</script>
<style scoped>
.btn-task {
  margin-right: 10px;
  margin-left: 5px;
}
</style>
