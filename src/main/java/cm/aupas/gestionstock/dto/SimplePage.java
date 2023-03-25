package cm.aupas.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;


@JsonIgnoreProperties({
        "pageable",
        "number",
        "first",
        "last",
        "empty",
        "numberOfElements",
        "sort"
})
public class SimplePage<T> extends PageImpl<T> {
    public SimplePage(
            @JsonProperty("content") final List<T> content,
            @JsonProperty("total") final long total,
                      @JsonProperty("page") final int page,
            @JsonProperty("size") final int size,
            @JsonProperty("sort") final List<String> sort
    ) {
        super(content, PageRequest.of(page,size,
                Sort.by(sort.stream()
                        .map(el->el.split(",")).map(ar->new Sort.Order(Sort.Direction.fromString(ar[1]),ar[0])).collect(Collectors.toList()))

                ),
                total);
    }

    public SimplePage(List<T> content,final long total,final Pageable pageable) {
        super(content,pageable,total);
    }

    public int getPage(){
        return getNumber();
    }
    public List<String> getSortList(){
        return getSort().stream().map(order -> order.getProperty()+","+order.getDirection().name()).collect(Collectors.toList());
    }
}
