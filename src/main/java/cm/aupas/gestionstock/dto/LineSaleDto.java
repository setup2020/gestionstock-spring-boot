package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.LineSale;
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

    private Long companyId;
    private static  LineSaleDto fromEntity(LineSale lineSale){
        if(lineSale==null){
            return  null;
        }

        return LineSaleDto.builder()
                .id(lineSale.getId())
                .sale(SaleDto.fromEntity(lineSale.getSale()))
                .article(ArticleDto.fromEntity(lineSale.getArticle()))
                .quantity(lineSale.getQuantity())
                .priceUnity(lineSale.getPriceUnity())
                .build();
    }

    private static LineSale toEntity(LineSaleDto lineSaleDto){
        LineSale lineSale=new LineSale();
        lineSale.setId(lineSaleDto.getId());
        lineSale.setSale(SaleDto.toEntity(lineSaleDto.getSale()));
        lineSale.setArticle(ArticleDto.toEntity(lineSaleDto.getArticle()));
        lineSale.setQuantity(lineSaleDto.getQuantity());
        lineSale.setPriceUnity(lineSaleDto.getPriceUnity());
        lineSale.setCompanyId(lineSaleDto.getCompanyId());
        return lineSale;
    }
}
