package org.task_manager.simpletaskmanager.dto;

import lombok.Data;
import org.task_manager.simpletaskmanager.enums.UserRole;

@Data
public class UserDto {
    private Long id;

    private String name;
    private String email;
    private String password;
    private UserRole role;

}
