package com.commande.services;


import com.commande.entities.Commande;
import com.commande.repositories.CommandeRepository;

public class CommandeService extends BaseService<Commande> {
    public CommandeService() {
        super(commandeRepository);
    }
}
