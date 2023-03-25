package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Address;
import cm.aupas.gestionstock.domain.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {
    private Long id;

    private String reference;

    private String designation;

    private BigDecimal priceUnitHt;

    private BigDecimal tva;

    private BigDecimal priceUnitTtc;

    private String photo;


    private CategoryDto category;

 public  static    Article toEntity(ArticleDto articleDto){
        if(articleDto==null){
            return null;
        }

        Article article=new Article();
        article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
        article.setReference(articleDto.getReference());
        article.setDesignation(articleDto.getDesignation());
        article.setPhoto(articleDto.getPhoto());
        article.setPriceUnitHt(articleDto.getPriceUnitHt());
        article.setTva(articleDto.getTva());
        article.setPriceUnitTtc(articleDto.getPriceUnitTtc());
        article.setId(articleDto.getId());

        return  article;
    }

   public static ArticleDto mapToDTO(Article article){
        if(article==null){
            return  null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .designation(article.getDesignation())
                .tva(article.getTva())
                .photo(article.getPhoto())
                .priceUnitHt(article.getPriceUnitHt())
                .priceUnitTtc(article.getPriceUnitTtc())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .reference(article.getReference())
                .build();
    }
}
