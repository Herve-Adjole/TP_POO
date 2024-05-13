package org.adjole.entities;


import java.util.Date;

public class ClientParticulier extends Client{
    private Date dateNaiss;
    private String lieuNaiss;

    public ClientParticulier() {
        this.dateNaiss = dateNaiss;
        this.lieuNaiss = lieuNaiss;
    }

    public ClientParticulier(int id, String nom, String prenom, String telephone, Date dateNaiss, String lieuNaiss) {
        super(id, nom, prenom, telephone);
        this.dateNaiss = dateNaiss;
        this.lieuNaiss = lieuNaiss;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getLieuNaiss() {
        return lieuNaiss;
    }

    public void setLieuNaiss(String lieuNaiss) {
        this.lieuNaiss = lieuNaiss;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public String getPrenom() {
        return super.getPrenom();
    }

    @Override
    public String getTelephone() {
        return super.getTelephone();
    }
}