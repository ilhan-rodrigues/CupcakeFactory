package org.example.model;

public class Topping implements Ingredient {
    private final String nom;
    private final double prix;

    public Topping(String nom, double prix) {
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
        return "Topping [nom=" + nom + ", prix=" + prix + "]";
    }
}
