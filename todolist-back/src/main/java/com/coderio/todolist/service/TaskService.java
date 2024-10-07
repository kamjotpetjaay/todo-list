package com.coderio.todolist.service;

import com.coderio.todolist.entity.Task;

import java.util.List;

public interface TaskService {
    //Crear, leer, actualizar y eliminar tareas.
    //Crear tarea
    Task saveTask(Task task);
    //Leer tareas
    List<Task> fetchTasks();
    //Actualizar tareas
    Task updateTask(Task task, Long taskId) throws Exception;
    //Eliminar tarea
    void deleteTaskById(Long taskId);
    //Filtrar tasks por estado o prioridad
    List<Task> filterTasksByStateOrPriority(Boolean state, Integer priority);
}
