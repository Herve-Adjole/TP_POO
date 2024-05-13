package org.adjole.services;


import org.adjole.entities.Sms;

import java.util.List;

public interface ISmsService {

    public void ajouter(Sms sms);

    public Sms selectionner(int id);

    public Sms modifier(Sms sms);

    public void supprimer(int id);
    public List<Sms>  getAll();

    public List<Sms> getSentSms();
    public List<Sms> getNotSentSms();
}
