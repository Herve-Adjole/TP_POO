package org.adjole.services.implementations;

import org.adjole.entities.Client;
import org.adjole.entities.ClientParticulier;
import org.adjole.services.IClientParticulierService;
import org.adjole.utils.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientParticulierService implements IClientParticulierService {


    private Connexion cn;

    public ClientParticulierService() {
        cn = new Connexion();
    }

    @Override
    public void ajouter(ClientParticulier client) {
        if (cn != null) {
            String requeteSQL = "INSERT INTO clientparticulier (nom, prenom, telephone, dateNaiss, lieuNaiss) VALUES (?,?,?,?,?)";
            try {
                PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
                preparedRequeteSQL.setString(1, client.getNom());
                preparedRequeteSQL.setString(2, client.getPrenom());
                preparedRequeteSQL.setString(3, client.getTelephone());
                preparedRequeteSQL.setDate(4, new java.sql.Date(client.getDateNaiss().getTime())); // Convertir Date en java.sql.Date
                preparedRequeteSQL.setString(5, client.getLieuNaiss());
                preparedRequeteSQL.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("Erreur-l'objet Conexion est null.");
        }
    }

    @Override
    public void modifier(ClientParticulier client) {
        String requeteSQL = "UPDATE clientparticulier SET nom=?, prenom=?, telephone=?, dateNaiss=?, lieuNaiss=? WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setString(1, client.getNom());
            preparedRequeteSQL.setString(2, client.getPrenom());
            preparedRequeteSQL.setString(3, client.getTelephone());
            preparedRequeteSQL.setDate(4, new java.sql.Date(client.getDateNaiss().getTime()));
            preparedRequeteSQL.setString(5, client.getLieuNaiss());
            preparedRequeteSQL.setInt(6, client.getId());
            preparedRequeteSQL.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void supprimer(int id) {
        String requeteSQL = "DELETE FROM clientparticulier WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setInt(1, id);
            preparedRequeteSQL.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ClientParticulier selectionner(int id) {
        String requeteSQL = "SELECT * FROM clientparticulier WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setInt(1, id);
            ResultSet result = preparedRequeteSQL.executeQuery();
            if (result.next()) {
                return new ClientParticulier(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getDate("dateNaiss"), result.getString("lieuNaiss"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ClientParticulier> getAll() {
        List<ClientParticulier> clientParticuliers = new ArrayList<>();
        String requeteSQL = "SELECT * FROM clientparticulier";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            ResultSet result = preparedRequeteSQL.executeQuery();
            while (result.next()) {
                ClientParticulier client = new ClientParticulier(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getDate("dateNaiss"), result.getString("lieuNaiss"));
                clientParticuliers.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientParticuliers;
    }

    public ClientParticulier getClientParticulierByName(String name) {
        ClientParticulier clientParticulier = null;
        String query = "SELECT * FROM clientparticulier WHERE nom = ?";
        try (PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    clientParticulier = new ClientParticulier();
                    clientParticulier.setId(resultSet.getInt("id"));
                    clientParticulier.setNom(resultSet.getString("nom"));
                    clientParticulier.setPrenom(resultSet.getString("prenom"));
                    clientParticulier.setTelephone(resultSet.getString("telephone"));
                    clientParticulier.setDateNaiss(resultSet.getDate("dateNaiss"));
                    clientParticulier.setLieuNaiss(resultSet.getString("lieuNaiss"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientParticulier;
    }
}
