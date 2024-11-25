package com.benjones.training.controller;

import com.benjones.training.entity.Task;
import com.benjones.training.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    private String taskList(Model model) {
        List<Task> tasks = taskService.findIncompleteTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @GetMapping("/completed")
    private String findCompletedTasks(Model model) {
        List<Task> tasks = taskService.getCompletedTasks();
        model.addAttribute("tasks", tasks);
        return "completed-tasks";
    }

    @GetMapping("/incomplete")
    private String findIncompleteTasks(Model model) {
        List<Task> tasks = taskService.findIncompleteTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/add")
    private String createTask(@ModelAttribute(name="newTask") Task newTask) {
        taskService.createTask(newTask.getTaskName());
        return "redirect:/tasks";
    }

    @PostMapping("/finish")
    private String finishTask(@RequestParam(name="id") long id) {
        taskService.completeTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/delete")
    private String deleteTask(@RequestParam(name="id") long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks/completed";
    }
}
