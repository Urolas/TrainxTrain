package com.sn.trainxtrain.model;

public class Fields
{
    private String code_equip;
    private String date;
    private String origine_iata;
    private String destination_iata;
    private String heure_depart;
    private String train_no;
    private String destination;
    private String origine;
    private String heure_arrivee;
    private String entity;
    private String od_happy_card;
    private String axe;

    public String getCode_equip() {
        return code_equip;
    }

    public String getDate() {
        return date;
    }

    public String getOrigine_iata() {
        return origine_iata;
    }

    public String getDestination_iata() {
        return destination_iata;
    }

    public String getHeure_depart() {
        return heure_depart;
    }

    public String getTrain_no() {
        return train_no;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigine() {
        return origine;
    }

    public String getHeure_arrivee() {
        return heure_arrivee;
    }

    public String getEntity() {
        return entity;
    }

    public String getOd_happy_card() {
        return od_happy_card;
    }

    public String getAxe() {
        return axe;
    }

    @Override
    public String toString() {
        return "ClassPojo [code_equip = "+code_equip+", date = "+date+", origine_iata = "+origine_iata+", destination_iata = "+destination_iata+", heure_depart = "+heure_depart+", train_no = "+train_no+", destination = "+destination+", origine = "+origine+", heure_arrivee = "+heure_arrivee+", entity = "+entity+", od_happy_card = "+od_happy_card+", axe = "+axe+"]";
    }
}