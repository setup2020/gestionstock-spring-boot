package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.LineOrderCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LineOrderCustomerDto {
    private Long id;
    private ArticleDto article;

    @JsonIgnore
    private OrderCustomerDto orderCustomer;

    private BigDecimal quantity;
    private BigDecimal priceUnity;
    private Long companyId;
    public static LineOrderCustomerDto fromEntity(LineOrderCustomer lineOrderCustomer){
        if(lineOrderCustomer==null){
            return null;
        }
        return LineOrderCustomerDto.builder()
                .id(lineOrderCustomer.getId())
                .article(ArticleDto.mapToDTO(lineOrderCustomer.getArticle()))
                .companyId(lineOrderCustomer.getCompanyId())
                .quantity(lineOrderCustomer.getQuantity())
                .priceUnity(lineOrderCustomer.getPriceUnity())
        .build();
    }

    public  static  LineOrderCustomer toEntity(LineOrderCustomerDto lineOrderCustomerDto){
        if(lineOrderCustomerDto==null){
            return null;
        }
        LineOrderCustomer lineOrderCustomer=new LineOrderCustomer();
        lineOrderCustomer.setId(lineOrderCustomerDto.getId());
        lineOrderCustomer.setArticle(ArticleDto.toEntity(lineOrderCustomerDto.getArticle()));
        lineOrderCustomer.setQuantity(lineOrderCustomerDto.getQuantity());
        lineOrderCustomer.setPriceUnity(lineOrderCustomerDto.getPriceUnity());
        lineOrderCustomer.setCompanyId(lineOrderCustomerDto.getCompanyId());
        return lineOrderCustomer;

    }
}
