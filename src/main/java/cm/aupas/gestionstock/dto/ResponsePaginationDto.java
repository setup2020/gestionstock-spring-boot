package cm.aupas.gestionstock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ResponsePaginationDto<T> {

    private List<T> content;
    private int page;
    private int size;
    private long total;
    private int totalPage;


  public static Sort sort( List<String> sortBy){

      List<String[]> p = sortBy.stream().map(sort -> sort.split(",", 2)).collect(Collectors.toList());
      log.warn("dddddddddddddddddddd0 {}",p);
      return Sort.by(sortBy.stream().map(sort -> sort.split(",", 2)).map(array -> new Sort.Order(replaceOrderStringThroughDirection(array[0]),array[0]).ignoreCase()).collect(Collectors.toList())         );

  }

    private static Sort.Direction replaceOrderStringThroughDirection(String sortDirection) {
      log.warn("dddddddddddddddddddddddddddddddddddddddddd {}");
        if (sortDirection.equalsIgnoreCase("DESC")){
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }


}
