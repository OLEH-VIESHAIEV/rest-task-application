package test.resttaskapplication.service;

import java.util.List;
import org.springframework.data.domain.Sort;

public interface SortService {
    List<Sort.Order> parseSortedOrders(String sortBy);
}
