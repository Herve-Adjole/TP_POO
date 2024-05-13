package org.adjole.services;

import org.adjole.entities.Client;

import java.util.List;

public interface IClientService {

    void ajouter(Client client);

    void modifier(Client client);

    void supprimer(int id);

    Client selectionner(int id);

    List<Client> getAll() ;


}
