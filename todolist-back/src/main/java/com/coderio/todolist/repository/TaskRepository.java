package com.coderio.todolist.repository;

import com.coderio.todolist.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    public List<Task> findByState(Boolean state);
    public List<Task> findByPriority(Integer priority);
}
