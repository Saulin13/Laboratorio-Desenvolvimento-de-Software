package com.example.roteiro01.controller;
import com.example.roteiro01.entity.Task;
import com.example.roteiro01.repository.TaskRepository;
import com.example.roteiro01.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;
    private TaskService taskService;

    // Injeta o serviço de tarefas no construtor
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        tasks.forEach(task -> {
            // Verifica se completed é null antes de acessá-lo
            if (task.getCompleted() == null) {
                task.setCompleted(false); // Ou qualquer outro valor padrão que você queira atribuir
            }
            // Verifica se taskType é null antes de acessá-lo
            if (task.isTaskTypeNull()) {
                task.setTaskType(0); // Ou qualquer outro valor padrão que você queira atribuir
            }
        });
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/gerenciar-tarefas")
    @Operation(summary = "Gerencie as tarefas da lista")
    public ResponseEntity<List<Task>> gerenciarTarefas() {
        List<Task> taskList = taskService.gerenciarTarefas();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }
    @GetMapping("/concluir-tarefas")
    @Operation(summary = "Concluir tarefas da lista")
    public ResponseEntity<List<Task>> concluirTarefas() {
        List<Task> taskList = taskService.concluirTarefas();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/priorizar-tarefas")
    @Operation(summary = "Priorizar tarefas da lista")
    public ResponseEntity<List<Task>> priorizarTarefas() {
        List<Task> taskList = taskService.priorizarTarefas();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/categorizar-tarefas")
    @Operation(summary = "Categorizar tarefas da lista")
    public ResponseEntity<List<Task>> categorizarTarefas() {
       List<Task> taskList = taskService.categorizarTarefas();
        if (taskList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskList);
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }


}
