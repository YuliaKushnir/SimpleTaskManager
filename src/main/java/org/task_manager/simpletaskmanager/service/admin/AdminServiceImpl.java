package org.task_manager.simpletaskmanager.service.admin;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task_manager.simpletaskmanager.dto.CommentDto;
import org.task_manager.simpletaskmanager.dto.TaskDto;
import org.task_manager.simpletaskmanager.dto.UserDto;
import org.task_manager.simpletaskmanager.enums.TaskStatus;
import org.task_manager.simpletaskmanager.enums.UserRole;
import org.task_manager.simpletaskmanager.model.Comment;
import org.task_manager.simpletaskmanager.model.Task;
import org.task_manager.simpletaskmanager.model.User;
import org.task_manager.simpletaskmanager.repositories.CommentRepository;
import org.task_manager.simpletaskmanager.repositories.TaskRepository;
import org.task_manager.simpletaskmanager.repositories.UserRepository;
import org.task_manager.simpletaskmanager.utils.JwtUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final JwtUtil jwtUtil;

    private final CommentRepository commentRepository;


    @Override
    public List<UserDto> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> user.getRole() == UserRole.EMPLOYEE)
                .map(User::getUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Optional<User> optionalUser = userRepository.findById(taskDto.getEmployeeId());
        if (optionalUser.isPresent()) {
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            task.setDueDate(taskDto.getDueDate());
            task.setTaskStatus(TaskStatus.IN_PROGRESS);
            task.setUser(optionalUser.get());

            return taskRepository.save(task).getTaskDto();
        }

        return null;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate)
                .reversed())
                .map(Task::getTaskDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(Task::getTaskDto).orElse(null);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(taskDto.getEmployeeId());
        if (optionalTask.isPresent() && optionalUser.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setDueDate(taskDto.getDueDate());
            task.setPriority(taskDto.getPriority());
            task.setTaskStatus(mapStringToTaskStatus(String.valueOf(taskDto.getTaskStatus())));
            task.setUser(optionalUser.get());

            return taskRepository.save(task).getTaskDto();
        }

        return null;
    }

    @Override
    public List<TaskDto> searchTaskByTitle(String title) {
        return taskRepository.findAllByTitleContaining(title)
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto createComment(Long taskId, String content) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        User user = jwtUtil.getLoggedInUser();
        if((optionalTask.isPresent()) && user != null) {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setCreatedAt(Date.valueOf(LocalDate.now()));
            comment.setTask(optionalTask.get());
            comment.setUser(user);
            return commentRepository.save(comment).getCommentDto();
        }
        throw new EntityNotFoundException("User or task not found");
    }

    private TaskStatus mapStringToTaskStatus(String status) {
        return switch (status) {
            case "PENDING" -> TaskStatus.PENDING;
            case "IN_PROGRESS" -> TaskStatus.IN_PROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELLED;
        };
    }


}
