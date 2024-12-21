package com.commande.repositories;

import com.commande.entities.Client;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientRepository extends BaseRepository<Client> {

    public ClientRepository() {
        super(Client.class);
    }

    public Client findByTelephone(String telephone) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client WHERE telephone = :telephone", Client.class)
                    .setParameter("telephone", telephone)
                    .uniqueResult();
        }
    }
}
