package com.commande.repositories;

import com.commande.entities.Article;

public class ArticleRepository extends BaseRepository<Article> {

    public ArticleRepository() {
        super(Article.class);
    }
}
