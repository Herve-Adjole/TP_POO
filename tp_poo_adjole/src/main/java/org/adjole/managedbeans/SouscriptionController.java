package org.adjole.managedbeans;

import org.adjole.entities.*;
import org.adjole.services.implementations.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class SouscriptionController {

    private boolean estEligibleAuProduit(ClientParticulier clientParticulier, Produit produit) {
        // Vérifier si le produit est de type "Epargne"
        if (produit.getLibelle().equalsIgnoreCase("Epargne")) {
            Date dateNaiss = clientParticulier.getDateNaiss();
            LocalDate dateNaissance = Instant.ofEpochMilli(dateNaiss.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate now = LocalDate.now();
            Period period = Period.between(dateNaissance, now);
            int age = period.getYears();

            if (age < 18) {
                return true;
            }
        }
        return false;
    }



    //Main
    public static void main(String[] args) {
        SouscriptionController souscriptionController = new SouscriptionController();
        SouscriptionService souscriptionService = new SouscriptionService();
        SmsService smsService = new SmsService();
        ClientService clientService = new ClientService();
        ProduitService produitService = new ProduitService();
        ClientParticulierService clientParticulierService = new ClientParticulierService();


        //Souscription d'un client(ADJOLE) pour un produit epargne
        Client client = clientService.getClientByName("ADJOLE");
        Produit produit = produitService.getProductByName("Epargne");
        if (client != null && produit != null) {

            Souscription souscription = new Souscription();
            souscription.setIdClient(client.getId());
            souscription.setIdProduit(produit.getId());
            souscriptionService.ajouter(souscription);
            //SMS de souscription
            Sms sms = new Sms();
            sms.setIdClient(souscription.getIdClient());
            sms.setLibelle("Bonjour " + client.getNom() + " Félicitations pour votre souscription à " + produit.getLibelle() + ".");
            sms.setEnvoye(true);
            //sms_saving
            smsService.ajouter(sms);

        } else {
            System.out.println("Le produit n'existe pas.");
        }

        //Souscription d'un client(ADJOLE) pour un produit Courant
        Client client1 = clientService.getClientByName("ADJOLE");
        Produit produit1 = produitService.getProductByName("Courant");
        if (client != null && produit != null) {

            Souscription souscription = new Souscription();
            souscription.setIdClient(client1.getId());
            souscription.setIdProduit(produit1.getId());
            souscriptionService.ajouter(souscription);
            //SMS de souscription
            Sms sms = new Sms();
            sms.setIdClient(souscription.getIdClient());
            sms.setLibelle("Bonjour " + client.getNom() + " Félicitations pour votre souscription à " + produit.getLibelle() + ".");
            sms.setEnvoye(true);
            //sms_saving
            smsService.ajouter(sms);

        } else {
            System.out.println("Le produit n'existe pas.");
        }

        //Souscription d'un client(SADANA) pour un produit Courant
        Client client2 = clientService.getClientByName("SADANA");
        Produit produit2 = produitService.getProductByName("Courant");
        if (client != null && produit != null) {

            Souscription souscription = new Souscription();
            souscription.setIdClient(client2.getId());
            souscription.setIdProduit(produit2.getId());
            souscriptionService.ajouter(souscription);
            //SMS de souscription
            Sms sms = new Sms();
            sms.setIdClient(souscription.getIdClient());
            sms.setLibelle("Bonjour " + client.getNom() + " Félicitations pour votre souscription à " + produit.getLibelle() + ".");
            sms.setEnvoye(true);
            //sms_saving
            smsService.ajouter(sms);

        } else {
            System.out.println("Le produit n'existe pas.");
        }


        // Manipulation de client particulier
        // Souscription d'un Particulier (YUMAKA)

        ClientParticulier clientYumaka = clientParticulierService.getClientParticulierByName("YUMAKA");
        Produit produitYumaka = produitService.getProductByName("Epargne");

        if (clientYumaka != null && produitYumaka != null) {
            if (souscriptionController.estEligibleAuProduit(clientYumaka, produitYumaka)) {
                Souscription souscriptionYumaka = new Souscription();
                souscriptionYumaka.setIdClient(clientYumaka.getId());
                souscriptionYumaka.setIdProduit(produitYumaka.getId());
                souscriptionService.ajouter(souscriptionYumaka);

                // SMS de souscription
                Sms smsYumaka = new Sms();
                smsYumaka.setIdClient(souscriptionYumaka.getIdClient());
                smsYumaka.setLibelle("Bonjour " + clientYumaka.getNom() + " Félicitations pour votre souscription à " + produitYumaka.getLibelle() + ".");
                smsYumaka.setEnvoye(true);
                smsService.ajouter(smsYumaka);
            } else {
                System.out.println("Le client particulier YUMAKA n'est pas éligible au produit " + produitYumaka.getLibelle() + ".");
            }
        } else {
            System.out.println("Le client particulier YUMAKA ou le produit n'existe pas.");
        }

//Souscription client particulier "DOVIC"
        ClientParticulier clientParticulier = clientParticulierService.getClientParticulierByName("DOVIC");
        Produit produitP = produitService.getProductByName("Epargne");
        if (souscriptionController.estEligibleAuProduit(clientParticulier, produitP)) {
            Souscription souscription = new Souscription();
            souscription.setIdClient(clientParticulier.getId());
            souscription.setIdProduit(produitP.getId());
            souscriptionService.ajouter(souscription);


            Sms sms = new Sms();
            sms.setIdClient(clientParticulier.getId());
            sms.setLibelle("Bonjour " + clientParticulier.getNom() + " 'Client Particulier'. Félicitations pour votre souscription à " + produit.getLibelle() + ".");
            smsService.ajouter(sms);
            smsService.getSentSms();
            sms.toString();
        } else {
            System.out.println("Le client n'est pas éligible à ce produit.");
        }


        //Echantillons de SMS de souscription avec un status en attente

        Client clientS = clientService.getClientByName("SADANA");
        Produit produitS = produitService.getProductByName("Courant");
        if (client != null && produit != null) {

            Souscription souscription = new Souscription();
            souscription.setIdClient(clientS.getId());
            souscription.setIdProduit(produitS.getId());
            souscriptionService.ajouter(souscription);
            //SMS en attente
            Sms sms = new Sms();
            sms.setIdClient(souscription.getIdClient());
            sms.setLibelle("Premier msg en attente");
            sms.setEnvoye(false);

            //sms_saving
            smsService.ajouter(sms);

            // Afficher la liste des SMS envoyés



        }
    }
}
