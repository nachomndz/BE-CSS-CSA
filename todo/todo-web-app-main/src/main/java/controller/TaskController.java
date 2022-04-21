package controller;
import entity.task.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TaskService;

import java.awt.print.Pageable;


@RestController
//@RequestMapping("/task")
//@RequiredArgsConstructor
@Log4j2
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    ResponseEntity getTasks(@PageableDefault Pageable pageable) {
        Iterable<Task> tasks = taskService.getWidgets(pageable);
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/task/{id}")
    ResponseEntity getTaskById(@PathVariable(value = "id") Long id) {
        return taskService.getTaskById(id).map(ResponseEntity::<Object>ok)
                .orElse(ResponseEntity.badRequest().body("Task with id=" + id + " does not exist"));
    }

    @PostMapping("/task/save")
    ResponseEntity saveWidget(@RequestBody Task task) throws Exception {
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable(value = "id") long id) throws Exception {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }


}
