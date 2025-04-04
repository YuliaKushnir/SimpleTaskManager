package org.task_manager.simpletaskmanager.service.admin;

import org.task_manager.simpletaskmanager.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUsers();
}
