package org.adjole.services;

import org.adjole.entities.Produit;

import java.util.List;

public interface IProduitService {

    void ajouter(Produit produit);

    void modifier(Produit produit);

    void supprimer(Produit produit);

    Produit selectionner(int id);

    List<Produit> getAll() ;
}
