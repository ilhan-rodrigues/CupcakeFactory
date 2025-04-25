package org.example.stock;

import org.example.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private final Map<Ingredient, Integer> ingredients = new HashMap<>();

    public void ajouterIngredient(Ingredient ingredient, int quantite) {
        ingredients.put(ingredient, quantite);
    }

    public boolean estDisponible(Ingredient ingredient) {
        return ingredients.getOrDefault(ingredient, 0) > 0;
    }

    public boolean consommer(Ingredient ingredient) {
        if (estDisponible(ingredient)) {
            int quantite = ingredients.get(ingredient);
            if (quantite > 0) {
                ingredients.put(ingredient, quantite - 1);
                return true;
            }
        }
        return false;
    }

    public int getQuantite(Ingredient ingredient) {
        return ingredients.getOrDefault(ingredient, 0);
    }

}
