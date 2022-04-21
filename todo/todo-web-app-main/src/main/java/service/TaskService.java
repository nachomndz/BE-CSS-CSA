package service;


import entity.task.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.TaskRepository;

import java.awt.print.Pageable;
import java.util.Optional;


@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository ;

    public TaskService(){
        this.taskRepository= null;
    }

    public Iterable<Task> getWidgets(Pageable pageable) {
        return taskRepository.findAll();
    }


    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) throws Exception {
        Optional<Task> maybeTask = Optional.of(task);
        Optional<Long> maybeTaskId = maybeTask.map(Task::getTaskId);

        if (maybeTaskId.isEmpty()) {
            return taskRepository.save(task);
        }
        return maybeTaskId
                .flatMap(taskRepository::findById)
                .map(dbTask -> dbTask.copyFields(task))
                .map(taskRepository::save)
                .orElseThrow(() -> new Exception("There is no task with id=" + task.getTaskId()));

    }

    public void deleteTask(long id) throws Exception {
        Task taskToDelete = taskRepository.findById(id)
                .orElseThrow(() -> new Exception("There is no task with id=" + id));
        taskRepository.delete(taskToDelete);

    }




}
