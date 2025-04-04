package org.task_manager.simpletaskmanager.service.auth;

import org.task_manager.simpletaskmanager.dto.SignupRequest;
import org.task_manager.simpletaskmanager.dto.UserDto;

public interface AuthService {

    UserDto signupUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}
