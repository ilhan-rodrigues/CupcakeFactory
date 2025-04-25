package org.example.model;

public class Creme implements Ingredient {
    private final String nom;
    private final double prix;

    public Creme(String nom, double prix) {
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
        return "Creme [nom=" + nom + ", prix=" + prix + "]";
    }
}
