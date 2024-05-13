package org.adjole.managedbeans;

import org.adjole.entities.Client;
import org.adjole.entities.ClientParticulier;
import org.adjole.services.implementations.ClientParticulierService;
import org.adjole.services.implementations.ClientService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ClientController {

    private static List<Client> clients = new ArrayList<>();
    private static List<ClientParticulier> clientParticuliers = new ArrayList<>();

    //Main
    public static void main(String[] args) {
        ClientController clientController = new ClientController();
        ClientService clientService = new ClientService();
        ClientParticulierService clientParticulierService = new ClientParticulierService();


        //enregistrement clients
        Client client1 = new Client();
        client1.setNom("ADJOLE");
        client1.setPrenom("Mènéli");
        client1.setTelephone("0123456781");
        clientService.ajouter(client1);
        clients.add(client1);

        Client client2 = new Client();
        client2.setNom("YUME");
        client2.setPrenom("Sade");
        client2.setTelephone("0123456782");
        clientService.ajouter(client2);
        clients.add(client2);

        Client client3 = new Client();
        client3.setNom("SADANA");
        client3.setPrenom("Patrick");
        client3.setTelephone("0123456783");
        clientService.ajouter(client3);
        clients.add(client3);



        // enreistrement client particulier
        ClientParticulier clientParticulier1 = new ClientParticulier();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        clientParticulier1.setNom("YUMAKA");
        clientParticulier1.setPrenom("Davinci");
        clientParticulier1.setTelephone("0123456784");
        try {
            Date dateNaissance = dateFormat.parse("2015-03-03");
            clientParticulier1.setDateNaiss(dateNaissance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientParticulier1.setLieuNaiss("Lome");
        clientParticulierService.ajouter(clientParticulier1);
        clientParticuliers.add(clientParticulier1);

        // enreistrement client particulier
        ClientParticulier clientParticulier2 = new ClientParticulier();
        clientParticulier2.setNom("DOVIC");
        clientParticulier2.setPrenom("Vacom");
        clientParticulier2.setTelephone("0123456784");
        try {
            Date dateNaissance = dateFormat.parse("2015-01-01");
            clientParticulier2.setDateNaiss(dateNaissance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientParticulier2.setLieuNaiss("Lome");
        clientParticulierService.ajouter(clientParticulier2);
        clients.add(clientParticulier2);


    }
}