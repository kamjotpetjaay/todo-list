package com.coderio.todolist.controller;

import com.coderio.todolist.entity.Task;
import com.coderio.todolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class TaskController {
    @Autowired
    private TaskService taskService;
    // Obtener todas las tareas.
    @GetMapping("/tasks")
    public List<Task> getTaskList(){
        return taskService.fetchTasks();
    }
    // Crear una nueva tarea.
    @PostMapping("/tasks")
    public Task saveTask( @Valid @RequestBody Task task){
        return  taskService.saveTask(task);
    }
    //Actualizar una tarea existente.
    @PutMapping("/tasks/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable("id") Long taskId) throws Exception {
        return taskService.updateTask( task, taskId);
    }
    //Eliminar una tarea.
    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable("id") Long taskId) {
        taskService.deleteTaskById( taskId);
        return "Se he eliminado la tarea con id: "+taskId;
    }
    //Filtrar tareas por estado y/o prioridad.
    @GetMapping("/tasks/filter")
    public List<Task> filterTasksByStateOrPriority(boolean state, Integer priority) {
        return taskService.filterTasksByStateOrPriority(state, priority);
    }
}
