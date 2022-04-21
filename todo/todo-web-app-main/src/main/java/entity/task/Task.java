package entity.task;

import entity.category.Category;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.*;
import java.time.Instant;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TASKS")
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskName;
    private String taskDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryId;
    private Instant deadline;


    public String getTaskDescription() {
        return taskDescription;
    }

    public long getTaskId(){
        return taskId;
    } public String getTaskName(){
        return taskName;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Instant getDeadline() {
        return deadline;
    }

    public void setDeadline(Instant deadline) {
        this.deadline = deadline;
    }

    public Task copyFields(Task from) {
        this.setTaskName(from.getTaskName());
        this.setTaskDescription(from.getTaskDescription());
        this.setCategoryId(from.getCategoryId());
        this.setDeadline(from.getDeadline());
        return this;
    }





}
