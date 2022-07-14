package test.resttaskapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.resttaskapplication.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
