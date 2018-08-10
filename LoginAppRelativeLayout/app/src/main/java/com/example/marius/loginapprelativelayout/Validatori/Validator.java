package com.example.marius.loginapprelativelayout.Validatori;

public class Validator {


    public boolean validateLogin(String email, String password)
    {
        if (email.isEmpty() || password.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean validateRegister(String email, String password, String password2)
    {
        if (email.isEmpty() || password.isEmpty() || password2.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public boolean validateAdaugare(String nume, String prenume, String adresa)
    {
        if (nume.isEmpty() || prenume.isEmpty() || adresa.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean ValidareDimensiuneNume(String nume)
    {
        if (nume.length()<=10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean ValidareDimeniunePrenume(String prenume)
    {
        if (prenume.length()<=10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean ValidareDimeniuneAdresa(String adresa)
    {
        if ((10<=adresa.length())&&(adresa.length()<=30))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkPassword(String pass1, String pass2)
    {

        if (pass1.equals(pass2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
