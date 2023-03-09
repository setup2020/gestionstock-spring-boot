package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Sale;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.Instant;

@Data
@Builder
public class SaleDto {


    private Long id;
    private  String reference;

    private Instant dateSale;

    private String comment;

    public static SaleDto fromEntity(Sale sale){
        if(sale==null){
            return  null;
        }
        return SaleDto.builder()
                .id(sale.getId())
                .reference(sale.getReference())
                .dateSale(sale.getDateSale())
                .comment(sale.getComment())
                .build();
    }

    public static Sale toEntity(SaleDto saleDto){
        Sale sale=new Sale();
        sale.setId(saleDto.getId());
        sale.setReference(saleDto.getReference());
        sale.setDateSale(saleDto.getDateSale());
        sale.setComment(saleDto.getComment());
        return sale;
    }
}
