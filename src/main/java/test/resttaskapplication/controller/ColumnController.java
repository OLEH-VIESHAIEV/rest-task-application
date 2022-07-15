package test.resttaskapplication.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.resttaskapplication.dto.request.ColumnRequestDto;
import test.resttaskapplication.dto.response.ColumnResponseDto;
import test.resttaskapplication.mapper.ColumnDtoMapper;
import test.resttaskapplication.model.Column;
import test.resttaskapplication.service.ColumnService;
import test.resttaskapplication.service.SortService;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    private final ColumnService columnService;
    private final ColumnDtoMapper columnDtoMapper;
    private final SortService sortService;

    public ColumnController(ColumnService columnService,
                            ColumnDtoMapper columnDtoMapper,
                            SortService sortService) {
        this.columnService = columnService;
        this.columnDtoMapper = columnDtoMapper;
        this.sortService = sortService;
    }

    @PostMapping
    public ColumnResponseDto save(@RequestBody ColumnRequestDto requestDto) {
        return columnDtoMapper.mapToDto(columnService.save(columnDtoMapper.mapToModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        columnService.delete(id);
    }

    @PutMapping("/{id}")
    public ColumnResponseDto update(@PathVariable Long id,
                                    @RequestBody ColumnRequestDto requestDto) {
        Column column = columnDtoMapper.mapToModel(requestDto);
        column.setId(id);
        return columnDtoMapper.mapToDto(columnService.save(column));
    }

    @GetMapping("/{id}")
    public ColumnResponseDto getById(@PathVariable Long id) {
        return columnDtoMapper.mapToDto(columnService.getById(id));
    }

    @GetMapping
    public List<ColumnResponseDto> getAll(@RequestParam (defaultValue = "id")
                                          String sortBy) {
        Sort sort = Sort.by(sortService.parseSortedOrders(sortBy));
        PageRequest pageRequest = PageRequest.of(0, 20, sort);
        return columnService
                .getAll(pageRequest)
                .stream()
                .map(columnDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
