package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.dto.MovementStockDto;

import java.math.BigDecimal;
import java.util.List;

public interface MovementStockService {

    BigDecimal stockRealArticle(Long articleId);

    List<MovementStockDto> mvtStockArticle(Long articleId);
    MovementStockDto enterStock(MovementStockDto dto);
    MovementStockDto sortieStock(MovementStockDto dto);

    MovementStockDto correctionStockPositive(MovementStockDto dto);
    MovementStockDto correctionStockNegative(MovementStockDto dto);
}
