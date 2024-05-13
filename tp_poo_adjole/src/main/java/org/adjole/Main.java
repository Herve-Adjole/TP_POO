package org.adjole;

import org.adjole.managedbeans.ClientController;
import org.adjole.managedbeans.ProduitController;
import org.adjole.managedbeans.SmsController;
import org.adjole.managedbeans.SouscriptionController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Code Executé! ");
        ClientController.main(args);
        ProduitController.main(args);
        SouscriptionController.main(args);
        SmsController.main(args);
        System.out.println("-@Veuillez consulter la base de donnée@-");

    }
}