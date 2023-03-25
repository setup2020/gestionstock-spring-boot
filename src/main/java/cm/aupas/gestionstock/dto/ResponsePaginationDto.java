package cm.aupas.gestionstock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

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


  public static Sort sort( List<String> sort){

       sort.stream().map(el->el.split(",")).forEach(ar->{
           log.error("======================================{}",ar);
       });
      return  Sort.by(
              sort
                      .stream()
                      .map(el->el.split(","))
                      .map(ar->new Sort.Order( ar[1]=="asc"? Sort.Direction.ASC:Sort.Direction.DESC,"name"))
                      .collect(Collectors.toList()));
  }


}
