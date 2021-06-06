package com.example.catalogboardgame.firebaseauth;

public class Akunfirebase {
    public String toPrinta()
    {
        return this.namaa+ " "+emaila+" "+UIDa;
    }
    public String getNamaa() {
        return namaa;
    }

    public void setNamaa(String namaa) {
        this.namaa = namaa;
    }

    public String getEmaila() {
        return emaila;
    }

    public void setEmaila(String emaila) {
        this.emaila = emaila;
    }

    public String getPassworda() {
        return passworda;
    }

    public void setPassworda(String passworda) {
        this.passworda = passworda;
    }

    public String getManaga() {
        return managa;
    }

    public void setManaga(String managa) {
        this.managa = managa;
    }

    public String getUIDa() {
        return UIDa;
    }

    public void setUIDa(String UIDa) {
        this.UIDa = UIDa;
    }

    public String getIDa() {
        return IDa;
    }

    public void setIDa(String IDa) {
        this.IDa = IDa;
    }

    public Akunfirebase(String namaa, String emaila, String passworda, String managa, String UIDa, String IDa) {
        this.namaa = namaa;
        this.emaila = emaila;
        this.passworda = passworda;
        this.managa = managa;
        this.UIDa = UIDa;
        this.IDa = IDa;
    }
    public Akunfirebase(){

    }
    public  String sas(){
        return this.managa;
    }


    private String namaa;
    private String emaila;
    private String passworda;
    private String managa;
    private String UIDa;
    private String IDa;
}
