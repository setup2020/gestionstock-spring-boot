package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.dto.ArticleDto;

public interface ArticleService extends DefaultService<ArticleDto, Long> {

    ArticleDto findByReferenceArticle(String reference);
}
