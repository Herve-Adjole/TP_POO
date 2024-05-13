package org.adjole.managedbeans;

import org.adjole.entities.Client;
import org.adjole.entities.Produit;
import org.adjole.services.implementations.ClientService;
import org.adjole.services.implementations.ProduitService;

public class ProduitController {



    public static void main(String[] args) {
        ProduitController produitController = new ProduitController();
        ProduitService produitService = new ProduitService();

        Produit epargne = new Produit();
        epargne.setLibelle("Epargne");
        epargne.setActif("T");
        produitService.ajouter(epargne);


        Produit courant = new Produit();
        courant.setLibelle("Courant");
        courant.setActif("T");
        produitService.ajouter(courant);

    }
}
