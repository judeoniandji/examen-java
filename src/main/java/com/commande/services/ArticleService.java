package com.commande.services;

import com.commande.entities.Article;
import com.commande.repositories.ArticleRepository;

public class ArticleService extends BaseService<Article> {
    public ArticleService() {
        super(articleRepository);
    }
}
