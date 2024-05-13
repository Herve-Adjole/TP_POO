package org.adjole.services.implementations;

import org.adjole.entities.Client;
import org.adjole.services.IClientService;
import org.adjole.utils.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientService implements IClientService {

    private Connexion cn;
    public ClientService() {
        cn = new Connexion();
    }


    @Override
    public void ajouter(Client client) {
        if (cn != null) {
            String requeteSQL = "INSERT INTO client (nom, prenom, telephone) VALUES (?,?,?)";
            try {
                PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
                preparedRequeteSQL.setString(1, client.getNom());
                preparedRequeteSQL.setString(2, client.getPrenom());
                preparedRequeteSQL.setString(3, client.getTelephone());
                preparedRequeteSQL.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
            }
    } else {
        // Handle case where cn is null
        System.err.println("Erreur-l'objet Conexion est null.");
    }
}


    @Override
    public void modifier(Client client) {
        String requeteSQL = "UPDATE client SET nom=?, prenom=?, telephone=? WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setString(1, client.getNom());
            preparedRequeteSQL.setString(2, client.getPrenom());
            preparedRequeteSQL.setString(3, client.getTelephone());
            preparedRequeteSQL.setInt(4, client.getId());
            preparedRequeteSQL.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        String requeteSQL = "DELETE FROM client WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setInt(1, id);
            preparedRequeteSQL.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Client selectionner(int id) {
        String requeteSQL = "SELECT * FROM client WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setInt(1, id);
            ResultSet result = preparedRequeteSQL.executeQuery();
            if (result.next()) {
                return new Client(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String requeteSQL = "SELECT * FROM client";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            ResultSet result = preparedRequeteSQL.executeQuery();
            while (result.next()) {
                Client client = new Client(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"));
                clients.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }


    public Client getClientByName(String name) {
        Client client = null;
        String query = "SELECT * FROM Client WHERE nom = ?";
        try (PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    client = new Client();
                    client.setId(resultSet.getInt("id"));
                    client.setNom(resultSet.getString("nom"));
                    client.setPrenom(resultSet.getString("prenom"));
                    client.setTelephone(resultSet.getString("telephone"));
                    // You can set other attributes here
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }
}
