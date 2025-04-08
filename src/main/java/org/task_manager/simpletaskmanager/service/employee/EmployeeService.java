package org.task_manager.simpletaskmanager.service.employee;

import org.task_manager.simpletaskmanager.dto.TaskDto;

import java.util.List;

public interface EmployeeService {
    List<TaskDto> getTasksByUserId();
}
