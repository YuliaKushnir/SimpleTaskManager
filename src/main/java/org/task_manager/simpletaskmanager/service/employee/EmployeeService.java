package org.task_manager.simpletaskmanager.service.employee;

import org.task_manager.simpletaskmanager.dto.CommentDto;
import org.task_manager.simpletaskmanager.dto.TaskDto;

import java.util.List;

public interface EmployeeService {
    List<TaskDto> getTasksByUserId();

    TaskDto updateTaskStatus(Long id, String status);

    TaskDto getTaskById(Long id);

    CommentDto createComment(Long taskId, String content);

    List<CommentDto> getCommentByTaskId(Long taskId);

}
