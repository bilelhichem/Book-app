package com.environment.myapplication.Model;

public class user {
    public String emailreg;
    public String imageprofil;
    public  String nameuser ;

    public user(String emailreg, String imageprofil, String nameuser) {
        this.emailreg = emailreg;
        this.imageprofil = imageprofil;
        this.nameuser = nameuser;
    }

    public user() {
    }

    public String getEmailreg() {
        return emailreg;
    }

    public void setEmailreg(String emailreg) {
        this.emailreg = emailreg;
    }

    public String getImageprofil() {
        return imageprofil;
    }

    public void setImageprofil(String imageprofil) {
        this.imageprofil = imageprofil;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }
}
