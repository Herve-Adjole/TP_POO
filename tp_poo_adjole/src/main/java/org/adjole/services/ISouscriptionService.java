package org.adjole.services;

import org.adjole.entities.Souscription;

import java.util.List;

public interface ISouscriptionService {
    public Souscription ajouter(Souscription souscription);

    public Souscription selectionner(int id);

    public Souscription modifier(Souscription souscription);

    public void supprimer(int id);
    public List<Souscription> getAll();

}
