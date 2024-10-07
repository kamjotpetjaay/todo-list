<template>
  <div class="container">
    <div class="card card-spaced">
      <div class="card-header">
        <h4>
          Tareas
          <router-link to="/create">
            <button class="btn btn-primary float-end">Crear nueva tarea</button>
          </router-link>
        </h4>
      </div>
      <div class="card-body">
        <v-row>
          <v-col md="5">
            <v-select
              v-model="selectedState"
              :items="stateOptions"
              label="Filtrar por estado"
              @change="applyFilters"
            ></v-select>
          </v-col>

          <v-col md="5">
            <v-select
              v-model="selectedPriority"
              :items="priorityOptions"
              label="Filtrar por prioridad"
              @change="applyFilters"
            ></v-select>
          </v-col>
          <v-col md="2"
            ><v-btn
              color="#2c0800"
              class="btn btn-warning btn-actions btn-remove"
              @click="removeFilters()"
              >Borrar filtros</v-btn
            >
          </v-col>
        </v-row>
        <v-data-table
          :headers="headers"
          :items="filteredTasks"
          :items-per-page="5"
          class="table"
          item-value="id"
        >
          <template v-slot:item.priority="{ item }">
            <span>
              {{ item.priority === 'alto' ? 'Alto' : item.priority === 'medio' ? 'Medio' : 'Bajo' }}
            </span>
          </template>
          <template v-slot:item.state="{ item }">
            <span>{{ item.state === 'Completo' ? 'Completo' : 'Incompleto' }}</span>
          </template>

          <template v-slot:item.actions="{ item }">
            <router-link :to="`/update/${item.taskId}`">
              <v-btn color="blue" class="btn btn-success btn-actions">Editar</v-btn>
            </router-link>
            <v-btn color="red" class="btn btn-danger btn-actions" @click="deleteTask(task.taskId)"
              >Eliminar</v-btn
            >
          </template>
        </v-data-table>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState(['tasks']),
    filteredTasks() {
      console.log('filtered tasks????')
      return this.tasks.filter((task) => {
        let matchState = true
        //Si se seleccionó un filtro de estado
        if (this.selectedState !== null) {
          matchState = task.state === this.selectedState
        } else {
          matchState = true
        }
        let matchPriority = true
        //Si se seleccionó un filtro de prioridad
        if (this.selectedPriority !== null) {
          matchPriority = task.priority === this.selectedPriority
        } else {
          matchPriority = true
        }
        return matchState && matchPriority
      })
    }
  },
  methods: {
    ...mapActions(['fetchTasks', 'deleteTask']),
    applyFilters() {},
    removeFilters() {
      this.selectedState = null
      this.selectedPriority = null
    }
  },
  created() {
    this.fetchTasks()
  },
  data() {
    return {
      headers: [
        { title: 'Tarea', key: 'title', color: 'blue-lighten-4' },
        { title: 'Prioridad', key: 'priority' },
        { title: 'Estado', key: 'state' },
        { title: 'Acciones', key: 'actions', sortable: false }
      ],
      selectedState: null,
      selectedPriority: null,
      stateOptions: ['Incompleto', 'Completo'],
      priorityOptions: ['bajo', 'medio', 'alto']
    }
  }
}
</script>
<style scoped>
.btn-actions {
  margin-right: 10px;
  margin-left: 5px;
}
.btn-remove {
  height: 70% !important;
}
.card-spaced {
  margin-top: 30px;
}
</style>
