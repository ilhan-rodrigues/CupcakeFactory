package org.example.model;

public class Base implements Ingredient{
    private final String nom;
    private final double prix;

    public Base(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public String toString() {
        return "Base [nom=" + nom + ", prix=" + prix + "]";
    }

}
