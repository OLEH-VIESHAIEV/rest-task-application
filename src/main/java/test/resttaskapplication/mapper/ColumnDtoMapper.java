package test.resttaskapplication.mapper;

import org.springframework.stereotype.Component;
import test.resttaskapplication.dto.request.ColumnRequestDto;
import test.resttaskapplication.dto.response.ColumnResponseDto;
import test.resttaskapplication.model.Column;

@Component
public class ColumnDtoMapper {
    public Column mapToModel(ColumnRequestDto requestDto) {
        Column column = new Column();
        column.setName(requestDto.getName());
        return column;
    }

    public ColumnResponseDto mapToDto(Column column) {
        ColumnResponseDto columnResponseDto = new ColumnResponseDto();
        columnResponseDto.setId(column.getId());
        columnResponseDto.setName(column.getName());
        return columnResponseDto;
    }
}
