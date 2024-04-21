package com.example.roteiro01.service;

import com.example.roteiro01.entity.Task;
import com.example.roteiro01.entity.TaskType;
import com.example.roteiro01.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        // Verifica se o tipo de tarefa está definido
        if (task.getTaskType() == null) {
            task.setTaskType(TaskType.DATA.ordinal());
        }
        return taskRepository.save(task);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public List<Task> gerenciarTarefas() {
        return taskRepository.findAll();
    }

    public List<Task> concluirTarefas() {
        return taskRepository.findByCompleted(true);
    }

    public List<Task> priorizarTarefas() {
        return taskRepository.findAll();
    }

    public List<Task> categorizarTarefas() {
      return taskRepository.findAll();
    }
}