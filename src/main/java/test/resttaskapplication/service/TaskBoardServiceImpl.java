package test.resttaskapplication.service;

import java.util.List;
import org.springframework.stereotype.Service;
import test.resttaskapplication.model.TaskBoard;
import test.resttaskapplication.repository.TaskBoardRepository;

@Service
public class TaskBoardServiceImpl implements TaskBoardService {
    private final TaskBoardRepository taskBoardRepository;

    public TaskBoardServiceImpl(TaskBoardRepository taskBoardRepository) {
        this.taskBoardRepository = taskBoardRepository;
    }

    @Override
    public TaskBoard save(TaskBoard taskBoard) {
        return taskBoardRepository.save(taskBoard);
    }

    @Override
    public void delete(Long id) {
        taskBoardRepository.deleteById(id);
    }

    @Override
    public List<TaskBoard> getAll() {
        return taskBoardRepository.findAll();
    }
}
