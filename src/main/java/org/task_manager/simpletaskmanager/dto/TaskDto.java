package org.task_manager.simpletaskmanager.dto;

import lombok.Data;
import org.task_manager.simpletaskmanager.enums.TaskStatus;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Date dueDate;
    private String priority;
    private TaskStatus taskStatus;
    private Long employeeId;
    private String employeeName;
}
