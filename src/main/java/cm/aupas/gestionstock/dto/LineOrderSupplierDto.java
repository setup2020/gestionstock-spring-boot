package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.LineOrderCustomer;
import cm.aupas.gestionstock.domain.LineOrderSupplier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LineOrderSupplierDto {
    private Long id;
    private ArticleDto article;


    @JsonIgnore
    private OrderSupplierDto orderCustomer;


    private BigDecimal quantity;


    private BigDecimal priceUnity;
    private Long companyId;

    public static LineOrderSupplierDto fromEntity(LineOrderSupplier lineOrderSupplier){
        if(lineOrderSupplier==null){
            return null;
        }
        return LineOrderSupplierDto.builder()
                .id(lineOrderSupplier.getId())
                .article(ArticleDto.mapToDTO(lineOrderSupplier.getArticle()))

                .quantity(lineOrderSupplier.getQuantity())
                .priceUnity(lineOrderSupplier.getPriceUnity())
                .companyId(lineOrderSupplier.getCompanyId())
                .build();
    }

    public  static  LineOrderSupplier toEntity(LineOrderSupplierDto lineOrderSupplierDto){
        if(lineOrderSupplierDto==null){
            return null;
        }
        LineOrderSupplier lineOrderSupplier=new LineOrderSupplier();
        lineOrderSupplierDto.setId(lineOrderSupplierDto.getId());
        lineOrderSupplier.setArticle(ArticleDto.toEntity(lineOrderSupplierDto.getArticle()));
        lineOrderSupplier.setQuantity(lineOrderSupplierDto.getQuantity());
        lineOrderSupplier.setPriceUnity(lineOrderSupplierDto.getPriceUnity());
        lineOrderSupplier.setCompanyId(lineOrderSupplierDto.getCompanyId());
        return lineOrderSupplier;

    }
}
