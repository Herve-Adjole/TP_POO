package org.adjole.services.implementations;

import org.adjole.entities.Souscription;
import org.adjole.services.ISouscriptionService;
import org.adjole.utils.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SouscriptionService implements ISouscriptionService {

    private Connexion cn;


    public SouscriptionService() {
        cn = new Connexion();
    }

    public SouscriptionService souscriptionService;
    public SmsService smsService;


    @Override
    public Souscription ajouter(Souscription souscription) {
        String requeteSQL = "INSERT INTO Souscription (dateHeureSous, actif, idClient, idProduit) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, new java.sql.Date(souscription.getDateHeureSous().getTime()));
            preparedStatement.setString(2, souscription.getActif());
            preparedStatement.setInt(3, souscription.getIdClient());
            preparedStatement.setInt(4, souscription.getIdProduit());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                souscription.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SouscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return souscription;
    }

    @Override
    public Souscription selectionner(int id) {
        Souscription souscription = null;
        String requeteSQL = "SELECT * FROM Souscription WHERE id=?";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                souscription = new Souscription();
                souscription.setId(resultSet.getInt("id"));
                souscription.setDateHeureSous(resultSet.getDate("dateHeureSous"));
                souscription.setActif(resultSet.getString("actif"));
                souscription.setIdClient(resultSet.getInt("idClient"));
                souscription.setIdProduit(resultSet.getInt("idProduit"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SouscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return souscription;
    }

    @Override
    public Souscription modifier(Souscription souscription) {
        String requeteSQL = "UPDATE Souscription SET dateHeureSous=?, actif=?, idClient=?, idProduit=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setDate(1, new java.sql.Date(souscription.getDateHeureSous().getTime()));
            preparedStatement.setString(2, souscription.getActif());
            preparedStatement.setInt(3, souscription.getIdClient());
            preparedStatement.setInt(4, souscription.getIdProduit());
            preparedStatement.setInt(5, souscription.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SouscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return souscription;
    }

    @Override
    public void supprimer(int id) {
        String requeteSQL = "DELETE FROM Souscription WHERE id=?";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SouscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Souscription> getAll() {
        List<Souscription> souscriptions = new ArrayList<>();
        String requeteSQL = "SELECT * FROM Souscription";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Souscription souscription = new Souscription();
                souscription.setId(resultSet.getInt("id"));
                souscription.setDateHeureSous(resultSet.getDate("dateHeureSous"));
                souscription.setActif(resultSet.getString("actif"));
                souscription.setIdClient(resultSet.getInt("idClient"));
                souscription.setIdProduit(resultSet.getInt("idProduit"));
                souscriptions.add(souscription);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SouscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return souscriptions;




    }
}
