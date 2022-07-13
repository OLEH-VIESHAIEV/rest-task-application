package test.resttaskapplication.service;

import java.util.List;
import test.resttaskapplication.model.TaskBoard;

public interface TaskBoardService {
    TaskBoard save(TaskBoard taskBoard);

    void delete(Long id);

    List<TaskBoard> getAll();
}
