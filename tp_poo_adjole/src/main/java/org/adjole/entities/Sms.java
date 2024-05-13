package org.adjole.entities;

public class Sms {
    private int id;
    private int idClient;
    private String libelle;
    private boolean envoye;

    public Sms(int id, int idClient, String libelle, boolean envoye) {
        this.id = id;
        this.idClient = idClient;
        this.libelle = libelle;
        this.envoye = envoye;
    }

    public Sms(int id) {
        this.id = id;
    }

    public Sms() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean isEnvoye() {
        return envoye;
    }

    public void setEnvoye(boolean envoye) {
        this.envoye = envoye;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", libelle='" + libelle + '\'' +
                ", envoye=" + envoye +
                '}';
    }
}
