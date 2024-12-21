package com.commande.repositories;

import com.commande.entities.Commande;

public class CommandeRepository extends BaseRepository<Commande> {

    public CommandeRepository() {
        super(Commande.class);
    }
}
