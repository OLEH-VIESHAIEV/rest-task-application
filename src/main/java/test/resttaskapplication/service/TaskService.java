package test.resttaskapplication.service;

import java.util.List;
import test.resttaskapplication.model.Task;

public interface TaskService {
    Task save(Task task);

    Task get(Long id);

    void delete(Long id);

    List<Task> getAll();
}
