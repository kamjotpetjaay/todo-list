package com.coderio.todolist.service.impl;

import com.coderio.todolist.entity.Task;
import com.coderio.todolist.repository.TaskRepository;
import com.coderio.todolist.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> fetchTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task, Long taskId) throws Exception {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if(taskOptional.isEmpty())
            throw new Exception("No se encontró el task con id "+taskId);
        else{
            Task task1 = taskOptional.get();
            task1.setTitle(task.getTitle());
            task1.setPriority(task.getPriority());
            task1.setState(task.getState());
            return taskRepository.save(task1);
        }
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> filterTasksByStateOrPriority(Boolean state, Integer priority) {
        List<Task> taskList = new ArrayList<>();
        if(state != null)
            taskList = taskRepository.findByState(state);
        else if(priority != null)
            taskList = taskRepository.findByPriority(priority);
        return taskList;
    }
}
