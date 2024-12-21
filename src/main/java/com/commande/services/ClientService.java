package com.commande.services;

import com.commande.entities.Client;
import com.commande.repositories.ClientRepository;

public class ClientService extends BaseService<Client> {
    private final ClientRepository clientRepository;

    public ClientService() {
        super(clientRepository);
        this.clientRepository = clientRepository;
    }

    public Client findByTelephone(String telephone) {
        return clientRepository.findByTelephone(telephone);
    }
}
