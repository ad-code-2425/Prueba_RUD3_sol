package com.example.hibernate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="account_id")
public class TermDepositAccount extends Account{
    
    private float interes;
    private int plazo_meses;


    public float getInteres() {
        return interes;
    }
    public void setInteres(float interes) {
        this.interes = interes;
    }
    public int getPlazo_meses() {
        return plazo_meses;
    }
    public void setPlazo_meses(int plazo_meses) {
        this.plazo_meses = plazo_meses;
    }


}
