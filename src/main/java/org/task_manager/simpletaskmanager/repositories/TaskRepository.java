package org.task_manager.simpletaskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.task_manager.simpletaskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
