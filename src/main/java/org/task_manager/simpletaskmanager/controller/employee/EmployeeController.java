package org.task_manager.simpletaskmanager.controller.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task_manager.simpletaskmanager.dto.TaskDto;
import org.task_manager.simpletaskmanager.service.employee.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUserId() {
        return ResponseEntity.ok(employeeService.getTasksByUserId());
    }
}
