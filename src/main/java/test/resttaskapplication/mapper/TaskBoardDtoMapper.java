package test.resttaskapplication.mapper;

import org.springframework.stereotype.Component;
import test.resttaskapplication.dto.request.TaskBoardRequestDto;
import test.resttaskapplication.dto.response.TaskBoardResponseDto;
import test.resttaskapplication.model.TaskBoard;

@Component
public class TaskBoardDtoMapper {
    public TaskBoard mapToModel(TaskBoardRequestDto requestDto) {
        TaskBoard taskBoard = new TaskBoard();
        taskBoard.setName(requestDto.getName());
        return taskBoard;
    }

    public TaskBoardResponseDto mapToDto(TaskBoard taskBoard) {
        TaskBoardResponseDto taskBoardResponseDto = new TaskBoardResponseDto();
        taskBoardResponseDto.setId(taskBoard.getId());
        taskBoardResponseDto.setName(taskBoard.getName());
        return taskBoardResponseDto;
    }
}
