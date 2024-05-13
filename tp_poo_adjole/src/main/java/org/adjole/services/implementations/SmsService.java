package org.adjole.services.implementations;

import org.adjole.entities.Sms;
import org.adjole.services.ISmsService;
import org.adjole.utils.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmsService implements ISmsService {
    private Connexion cn;
    public SmsService() {
        cn = new Connexion();
    }


    @Override
    public void ajouter(Sms sms) {
        String requeteSQL = "INSERT INTO Sms (idClient, libelle, envoye) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setInt(1, sms.getIdClient());
            preparedStatement.setString(2, sms.getLibelle());
            preparedStatement.setBoolean(3, sms.isEnvoye());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SmsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public Sms selectionner(int id) {
        Sms sms = null;
        String requeteSQL = "SELECT * FROM Sms WHERE id=?";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sms = new Sms();
                sms.setId(resultSet.getInt("id"));
                sms.setIdClient(resultSet.getInt("idClient"));
                sms.setLibelle(resultSet.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SmsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sms;
    }

    @Override
    public Sms modifier(Sms sms) {
        String requeteSQL = "UPDATE Sms SET idClient=?, libelle=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setInt(1, sms.getIdClient());
            preparedStatement.setString(2, sms.getLibelle());
            preparedStatement.setInt(3, sms.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SmsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sms;
    }

    @Override
    public void supprimer(int id) {
        String requeteSQL = "DELETE FROM Sms WHERE id=?";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SmsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Sms> getAll() {
        List<Sms> smsList = new ArrayList<>();
        String requeteSQL = "SELECT * FROM Sms";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sms sms = new Sms();
                sms.setId(resultSet.getInt("id"));
                sms.setIdClient(resultSet.getInt("idClient"));
                sms.setLibelle(resultSet.getString("libelle"));
                smsList.add(sms);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SmsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return smsList;
    }@Override
    public List<Sms> getSentSms() {
        List<Sms> sentSmsList = new ArrayList<>();
        String requeteSQL = "SELECT * FROM Sms WHERE envoye = ?";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sms sms = new Sms();
                sms.setId(resultSet.getInt("id"));
                sms.setIdClient(resultSet.getInt("idClient"));
                sms.setLibelle(resultSet.getString("libelle"));
                sms.setEnvoye(resultSet.getBoolean("envoye"));
                sentSmsList.add(sms);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SmsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sentSmsList;
    }

    @Override
    public List<Sms> getNotSentSms() {
        List<Sms> notSentSmsList = new ArrayList<>();
        String requeteSQL = "SELECT * FROM Sms WHERE envoye = ?";
        try {
            PreparedStatement preparedStatement = cn.makeConnection().prepareStatement(requeteSQL);
            preparedStatement.setBoolean(1, false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sms sms = new Sms();
                sms.setId(resultSet.getInt("id"));
                sms.setIdClient(resultSet.getInt("idClient"));
                sms.setLibelle(resultSet.getString("libelle"));
                sms.setEnvoye(resultSet.getBoolean("envoye"));
                notSentSmsList.add(sms);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SmsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notSentSmsList;
    }

}
