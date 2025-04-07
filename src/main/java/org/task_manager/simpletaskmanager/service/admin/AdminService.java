package org.task_manager.simpletaskmanager.service.admin;

import org.task_manager.simpletaskmanager.dto.TaskDto;
import org.task_manager.simpletaskmanager.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUsers();

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    void deleteTask(Long id);

    TaskDto getTaskById(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);

    List<TaskDto> searchTaskByTitle(String title);

}
