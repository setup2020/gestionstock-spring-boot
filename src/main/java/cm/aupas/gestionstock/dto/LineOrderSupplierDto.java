package cm.aupas.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LineOrderSupplierDto {
    private Long id;
    private ArticleDto article;


    private OrderSupplierDto orderCustomer;


    private BigDecimal quantity;


    private BigDecimal priceUnity;
}
