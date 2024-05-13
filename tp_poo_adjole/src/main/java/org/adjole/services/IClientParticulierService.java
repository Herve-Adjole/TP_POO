package org.adjole.services;

import org.adjole.entities.Client;
import org.adjole.entities.ClientParticulier;

import java.util.List;

public interface IClientParticulierService {

    void ajouter(ClientParticulier clientParticulier);

    void modifier(ClientParticulier clientParticulier);

    void supprimer(int id);

    ClientParticulier selectionner(int id);

    List<ClientParticulier> getAll() ;

}
