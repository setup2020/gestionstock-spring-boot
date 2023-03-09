package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.MovementStock;
import cm.aupas.gestionstock.domain.TypeMvtStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MovementStockDto {
    private Long id;
    private Instant dateMvt;


    private BigDecimal quantity;


    private TypeMvtStock typeMvt;


    private ArticleDto article;
    private Long companyId;

    private static MovementStockDto formEntity(MovementStock movementStock){
        if(movementStock==null){
            return null;
        }

        return MovementStockDto.builder()
                .id(movementStock.getId())
                .dateMvt(movementStock.getDateMvt())
                .quantity(movementStock.getQuantity())
                .typeMvt(movementStock.getTypeMvt())
                .article(ArticleDto.fromEntity(movementStock.getArticle()))
                .companyId(movementStock.getCompanyId())
                .build();
    }

    public static MovementStock toEntity(MovementStockDto movementStockDto){
        MovementStock movementStock=new MovementStock();
        movementStock.setId(movementStockDto.getId());
        movementStock.setTypeMvt(movementStock.getTypeMvt());
        movementStock.setArticle(ArticleDto.toEntity(movementStockDto.getArticle()));
        movementStock.setCompanyId(movementStockDto.getCompanyId());
        return  movementStock;

    }
}
