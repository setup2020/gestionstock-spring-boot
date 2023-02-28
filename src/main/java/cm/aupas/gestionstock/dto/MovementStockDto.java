package cm.aupas.gestionstock.dto;

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
}
