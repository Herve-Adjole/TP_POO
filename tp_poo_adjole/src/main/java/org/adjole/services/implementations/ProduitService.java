package org.adjole.services.implementations;

import org.adjole.entities.Produit;
import org.adjole.services.IProduitService;
import org.adjole.utils.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProduitService implements IProduitService {
    private Connexion cn;
    public ProduitService() {
        cn = new Connexion();
    }

    @Override
    public void ajouter(Produit produit) {
        String requeteSQL = "INSERT INTO produit (libelle, actif) VALUES (?, ?)";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setString(1, produit.getLibelle());
            preparedRequeteSQL.setString(2, produit.getActif());
            preparedRequeteSQL.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Produit produit) {
        String requeteSQL = "UPDATE produit SET libelle=?, actif=? WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setString(1, produit.getLibelle());
            preparedRequeteSQL.setString(2, produit.getActif());
            preparedRequeteSQL.setInt(3, produit.getId());
            preparedRequeteSQL.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(Produit produit) {
        String requeteSQL = "DELETE FROM produit WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setInt(1, produit.getId());
            preparedRequeteSQL.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Produit selectionner(int id) {
        String requeteSQL = "SELECT * FROM produit WHERE id=?";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            preparedRequeteSQL.setInt(1, id);
            ResultSet result = preparedRequeteSQL.executeQuery();
            if (result.next()) {
                return new Produit(result.getInt("id"), result.getString("libelle"), result.getString("actif"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> produits = new ArrayList<>();
        String requeteSQL = "SELECT * FROM produit";
        try {
            PreparedStatement preparedRequeteSQL = cn.makeConnection().prepareStatement(requeteSQL);
            ResultSet result = preparedRequeteSQL.executeQuery();
            while (result.next()) {
                Produit produit = new Produit(result.getInt("id"), result.getString("libelle"), result.getString("actif"));
                produits.add(produit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }

    public Produit getProductByName(String productName) {
        List<Produit> produits = getAll();
        for (Produit produit : produits) {
            if (produit.getLibelle().equalsIgnoreCase(productName)) {
                return produit;
            }
        }
        return null;
    }

}
