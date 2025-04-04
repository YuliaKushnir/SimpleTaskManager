package org.task_manager.simpletaskmanager.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task_manager.simpletaskmanager.dto.UserDto;
import org.task_manager.simpletaskmanager.enums.UserRole;
import org.task_manager.simpletaskmanager.model.User;
import org.task_manager.simpletaskmanager.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;


    @Override
    public List<UserDto> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> user.getRole() == UserRole.EMPLOYEE)
                .map(User::getUserDto)
                .collect(Collectors.toList());
    }
}
