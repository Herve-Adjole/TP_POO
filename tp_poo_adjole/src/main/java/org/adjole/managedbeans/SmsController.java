package org.adjole.managedbeans;

import org.adjole.entities.Client;
import org.adjole.entities.Sms;
import org.adjole.services.implementations.ClientService;
import org.adjole.services.implementations.ProduitService;
import org.adjole.services.implementations.SmsService;
import org.adjole.services.implementations.SouscriptionService;

import java.util.List;

public class SmsController {


    public static void main(String[] args) {
        SouscriptionController souscriptionController = new SouscriptionController();
        SmsService smsService = new SmsService();
        ClientService clientService = new ClientService();
        ProduitService produitService = new ProduitService();



        // Récupérer les SMS envoyés
        List<Sms> sentSmsList = smsService.getSentSms();
        System.out.println("Liste des SMS envoyés :");
        for (Sms sms : sentSmsList) {
            System.out.println("ID: " + sms.getId());
            System.out.println("ID du client: " + sms.getIdClient());
            System.out.println("Libellé: " + sms.getLibelle());
            System.out.println("Envoyé: " + sms.isEnvoye());
            System.out.println("------------------------------");
        }

        System.out.println("-@@@@@@@@@@@@@@@@@@@@@@@@@@@@-");
        System.out.println("-@@@@@@@@@@@@@@@@@@@@@@@@@@@@-");


        // Récupérer les SMS en attente
        List<Sms> notSentSmsList = smsService.getNotSentSms();
        System.out.println("Liste des SMS en attente :");
        for (Sms sms : notSentSmsList) {
            System.out.println("ID: " + sms.getId());
            System.out.println("ID du client: " + sms.getIdClient());
            System.out.println("Libellé: " + sms.getLibelle());
            System.out.println("Envoyé: " + sms.isEnvoye());
            System.out.println("------------------------------");
        }

        System.out.println("-@@@@@@@@@@@@@@@@@@@@@@@@@@@@-");
        System.out.println("-@@@@@@@@@@@@@@@@@@@@@@@@@@@@-");
        // Récupérer tous les SMS
        List<Sms> allSmsList = smsService.getAll();

        if (!allSmsList.isEmpty()) {
            System.out.println("Liste de tous les SMS :");
            for (Sms sms : allSmsList) {
                System.out.println("ID: " + sms.getId());
                System.out.println("ID du client: " + sms.getIdClient());
                System.out.println("Libellé: " + sms.getLibelle());
                System.out.println("Envoyé: " + sms.isEnvoye());
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("Aucun SMS trouvé.");
        }




    }
}

