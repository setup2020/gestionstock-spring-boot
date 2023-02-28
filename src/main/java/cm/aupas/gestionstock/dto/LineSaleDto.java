package cm.aupas.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LineSaleDto {
    private Long id;
    private SaleDto sale;


    private ArticleDto article;


    private BigDecimal quantity;


    private BigDecimal priceUnity;
}
