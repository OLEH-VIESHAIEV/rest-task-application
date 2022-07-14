package test.resttaskapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.resttaskapplication.model.TaskBoard;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, Long> {
}
