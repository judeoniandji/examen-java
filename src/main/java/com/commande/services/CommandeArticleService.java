package com.commande.services;

import com.commande.entities.CommandeArticle;
import com.commande.repositories.CommandeArticleRepository;

public class CommandeArticleService extends BaseService<CommandeArticle> {
    public CommandeArticleService() {
        super(commandeArticleRepository);
    }
}
