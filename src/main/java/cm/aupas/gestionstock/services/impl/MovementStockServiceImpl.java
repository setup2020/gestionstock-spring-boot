package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.TypeMvtStock;
import cm.aupas.gestionstock.dto.MovementStockDto;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.ArticleRepository;
import cm.aupas.gestionstock.repository.MovementStockRepository;
import cm.aupas.gestionstock.services.ArticleService;
import cm.aupas.gestionstock.services.MovementStockService;
import cm.aupas.gestionstock.validators.MovementStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MovementStockServiceImpl implements MovementStockService {
    private final MovementStockRepository movementStockRepository;
    private final ArticleService articleService;


    @Autowired
    public MovementStockServiceImpl(MovementStockRepository movementStockRepository, ArticleService articleService) {
        this.movementStockRepository = movementStockRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockRealArticle(Long articleId) {
        if(articleId==null){
            log.warn("ID article is NULL");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(articleId);
        return movementStockRepository.stockRealArticle(articleId);
    }

    @Override
    public List<MovementStockDto> mvtStockArticle(Long articleId) {
        return movementStockRepository.findAllByArticleId(articleId).stream()
                .map(MovementStockDto::formEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MovementStockDto enterStock(MovementStockDto dto) {
            return entreePositive(dto,TypeMvtStock.ENTREE);
    }

    @Override
    public MovementStockDto sortieStock(MovementStockDto dto) {
      return sortieNegative(dto,TypeMvtStock.SORTIE);
    }

    @Override
    public MovementStockDto correctionStockPositive(MovementStockDto dto) {
        return entreePositive(dto,TypeMvtStock.CORRECTION_POSITIVE);
    }

    @Override
    public MovementStockDto correctionStockNegative(MovementStockDto dto) {
       return  sortieNegative(dto,TypeMvtStock.CORRECTION_NEGATIVE);
    }

    private MovementStockDto entreePositive(MovementStockDto dto, TypeMvtStock typeMvtStock){

        List<String> errors= MovementStockValidator.validator(dto);
        if(!errors.isEmpty()){
            log.error("Movement stock is valid {}",dto);
            throw new InvalidEntityException("Le mouvement de stock n'est pas valide", ErrorCode.ERROR_422,errors);
        }

        dto.setQuantity(
                BigDecimal.valueOf(
                        Math.abs(dto.getQuantity().doubleValue())));
        dto.setTypeMvt(typeMvtStock);
        return MovementStockDto.formEntity(
                movementStockRepository.save(
                        MovementStockDto.toEntity(dto)
                )
        );
    }

    private MovementStockDto sortieNegative(MovementStockDto dto, TypeMvtStock typeMvtStock){
        List<String> errors= MovementStockValidator.validator(dto);
        if(!errors.isEmpty()){
            log.error("Movement stock is valid {}",dto);
            throw new InvalidEntityException("Le mouvement de stock n'est pas valide", ErrorCode.ERROR_422,errors);
        }

        dto.setQuantity(
                BigDecimal.valueOf(
                        Math.abs(dto.getQuantity().doubleValue())*-1));
        dto.setTypeMvt(typeMvtStock);
        return MovementStockDto.formEntity(
                movementStockRepository.save(
                        MovementStockDto.toEntity(dto)
                )
        );

    }
}
