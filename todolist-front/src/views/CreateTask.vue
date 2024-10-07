<template>
  <div class="container mt-5">
    <div class="card">
      <div class="card-header">
        <h4>Agregar Tarea</h4>
      </div>
      <div class="card-body">
        <form @submit.prevent="submitTask">
          <div v-if="errors.length">
            <b>Se encontraron los siguientes errores:</b>
            <ul>
              <li v-for="(error, index) in errors" :key="index">{{ error }}</li>
            </ul>
          </div>
          <div class="mb-3">
            <label for="">Tarea: </label>
            <input type="text" v-model="newTask.title" class="form-control" />
          </div>
          <div class="mb-3">
            <label for="">Prioridad: </label>
            <select class="form-select" v-model="newTask.priority">
              <option value="alto">Alta</option>
              <option value="medio">Media</option>
              <option value="bajo">Baja</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="">Estado: </label>
            <select class="form-select" v-model="newTask.state">
              <option value="Incompleto">No completada</option>
              <option value="Completo">Completada</option>
            </select>
          </div>
          <div class="mb-3">
            <button class="btn btn-danger btn-task" type="button" @click="returnBack()">
              Cancelar
            </button>
            <button class="btn btn-primary" type="submit">Guardar</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  data() {
    return {
      newTask: {
        title: '',
        priority: 'medio',
        state: 'Incompleto'
      },
      errors: []
    }
  },
  methods: {
    ...mapActions(['createTask']),

    submitTask() {
      if (this.newTask.title) {
        this.createTask(this.newTask).then(() => {
          this.$router.push('/')
        })
      } else {
        alert('Titulo requerido')
      }
    },
    returnBack() {
      this.$router.push('/')
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
