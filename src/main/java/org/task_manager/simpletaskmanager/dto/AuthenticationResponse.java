package org.task_manager.simpletaskmanager.dto;

import lombok.Data;
import org.task_manager.simpletaskmanager.enums.UserRole;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
}
