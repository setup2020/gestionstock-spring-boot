package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.domain.LineSale;
import cm.aupas.gestionstock.domain.Sale;
import cm.aupas.gestionstock.dto.LineSaleDto;
import cm.aupas.gestionstock.dto.SaleDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.ArticleRepository;
import cm.aupas.gestionstock.repository.LineSaleRepository;
import cm.aupas.gestionstock.repository.SaleRepository;
import cm.aupas.gestionstock.services.SaleService;
import cm.aupas.gestionstock.validators.SaleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SaleServiceImpl implements SaleService {

    private final ArticleRepository articleRepository;
    private final LineSaleRepository lineSaleRepository;
    private final SaleRepository saleRepository;

    public SaleServiceImpl(ArticleRepository articleRepository, LineSaleRepository lineSaleRepository, SaleRepository saleRepository) {
        this.articleRepository = articleRepository;
        this.lineSaleRepository = lineSaleRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public SaleDto save(SaleDto saleDto) {
        List<String> errors= SaleValidator.validate(saleDto);
        if(!errors.isEmpty()){
            log.error("Ventes n'est pas valide");
            throw new InvalidEntityException(" l'oject Ventes n'est pas valide", ErrorCode.ERROR_422,errors);
        }
        List<String> articleErrors=new ArrayList<>();
        saleDto.getLineSales().forEach(lineSale -> {
            Optional<Article> article=articleRepository.findById(lineSale.getArticle().getId());
            if(article.isEmpty()){
                articleErrors.add("Aucun article avec l'Id "+lineSale.getArticle().getId()+" n'a ete trouvé dans le BD");
            }
        });

        if(!articleErrors.isEmpty()){
            log.error("One or more articles not found in th Db , {}",saleRepository);
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans le BD", ErrorCode.ERROR_422,articleErrors);
        }
        Sale saleSaved=saleRepository.save(SaleDto.toEntity(saleDto));
        saleDto.getLineSales().forEach(lineSaleDto -> {
            LineSale lineSale1= LineSaleDto.toEntity(lineSaleDto);
            lineSale1.setSale(saleSaved);
            lineSaleRepository.save(lineSale1);


        });


        return SaleDto.fromEntity(saleSaved);
    }

    @Override
    public SaleDto findById(Long id) {
        if(id==null){
            log.error("Vente  ID is NULL");
            return null;
        }
        return saleRepository.findById(id).map(SaleDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucune vente n'a ete trouvé dans la DB",ErrorCode.ERROR_401));
    }

    @Override
    public List<SaleDto> findAll() {
        return saleRepository.findAll().stream().map(SaleDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Vente ID is NULL");
            return;
        }
        saleRepository.deleteById(id);
    }

    @Override
    public SaleDto update(SaleDto saleDto) {
        return null;
    }

    @Override
    public SaleDto findByReference(String reference) {
        if(reference==null){
            log.error("Vente  Reference is NULL");
            return null;
        }
        return saleRepository.findByReference(reference).map(SaleDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucune vente n'a ete trouvé dans la DB",ErrorCode.ERROR_401));
    }
}
