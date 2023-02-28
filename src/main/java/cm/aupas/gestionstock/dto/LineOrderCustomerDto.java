package cm.aupas.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LineOrderCustomerDto {
    private Long id;
    private ArticleDto article;


    private OrderCustomerDto orderCustomer;


    private BigDecimal quantity;


    private BigDecimal priceUnity;
}
