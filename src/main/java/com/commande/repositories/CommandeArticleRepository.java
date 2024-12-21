package com.commande.repositories;

import com.commande.entities.CommandeArticle;

public class CommandeArticleRepository extends BaseRepository<CommandeArticle> {

    public CommandeArticleRepository() {
        super(CommandeArticle.class);
    }
}
