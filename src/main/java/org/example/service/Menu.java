package org.example.service;

import org.example.model.Cupcake;
import org.example.model.Ingredient;
import org.example.stock.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final List<Cupcake> cupcakesDuJour = new ArrayList<>();
    private final Map<Cupcake, Integer> stockCupcakesDuJour = new HashMap<>();
    private final Stock stock;

    public Menu(Stock stock) {
        this.stock = stock;
    }

    public void ajouterCupcakeDuJour(Cupcake cupcake, int quantite) {
        cupcakesDuJour.add(cupcake);
        stockCupcakesDuJour.put(cupcake, quantite);
    }

    public boolean estDisponible(Cupcake cupcake) {
        return stockCupcakesDuJour.getOrDefault(cupcake, 0) > 0;
    }

    public List<Cupcake> getCupcakesDuJourDiposponibles() {
        List<Cupcake> dispo = new ArrayList<>();
        for (Cupcake cupcake : cupcakesDuJour) {
            if (estDisponible(cupcake)) {
                dispo.add(cupcake);
            }
        }
        return dispo;
    }

    public List<Ingredient> getIngredientsDisponibles() {
        List<Ingredient> disponibles = new ArrayList<>();
        for (Ingredient ing : stock.getIngredients()) {
            if (stock.estDisponible(ing)) {
                disponibles.add(ing);
            }
        }
        return disponibles;
    }



}
