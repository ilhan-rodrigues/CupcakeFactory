package org.example.service;

public class HistoriqueVentes {
    private double total;

    public HistoriqueVentes() {
        this.total = 0;
    }

    public void enregistrerVente(double montant) {
        total += montant;
    }

    public double getTotal() {
        return total;
    }

    public void reset() {
        total = 0;
    }
}