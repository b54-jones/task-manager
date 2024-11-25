package com.benjones.training.service;

import com.benjones.training.entity.Task;
import com.benjones.training.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }

    public List<Task> findIncompleteTasks() {
        return taskRepository.findByCompleted(false);
    }

    public Task createTask(String taskName) {
        Task newTask = new Task(taskName, false);
        taskRepository.save(newTask);
        return newTask;
    }

    public void completeTask(long id) {
    Optional<Task> taskInDb = taskRepository.findById(id);
        if (taskInDb.isPresent()) {
            Task current = taskInDb.get();
            current.setCompleted(true);
            taskRepository.save(current);
        }
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }
}
