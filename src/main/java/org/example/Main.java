package org.example;

import org.example.model.*;
import org.example.service.Commande;
import org.example.service.Menu;
import org.example.stock.Stock;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Ingrédients
        Base baseNature = new Base("Nature", 2.0);
        Creme cremeVanille = new Creme("Vanille", 1.5);
        Topping choco = new Topping("Choco", 0.5);
        Topping caramel = new Topping("Caramel", 0.7);

        // Stock initial
        Stock stock = new Stock();
        stock.ajouterIngredient(baseNature, 10);
        stock.ajouterIngredient(cremeVanille, 10);
        stock.ajouterIngredient(choco, 5);
        stock.ajouterIngredient(caramel, 5);

        // Cupcakes du jour
        Cupcake cupcakeDuJour = new Cupcake(baseNature, cremeVanille, List.of(choco));
        Cupcake cupcakeNormal = new Cupcake(baseNature, cremeVanille, List.of(choco, caramel));

        Menu menu = new Menu(stock);
        menu.ajouterCupcakeDuJour(cupcakeDuJour, 3);

        System.out.println("=== Cupcakes du jour ===");
        for (Cupcake c : menu.getCupcakesDuJourDisponibles()) {
            System.out.println("- " + c + " à " + c.getPrix() * 0.4 + "€");
        }

        System.out.println("\n=== Ingrédients disponibles 😍 ===");
        for (Ingredient ing : menu.getIngredientsDisponibles()) {
            System.out.println("- " + ing.getNom() + " (" + stock.getQuantite(ing) + ")");
        }

        // Simuler une commande
        Commande commande = new Commande();
        commande.ajouter(cupcakeDuJour);
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal);

        boolean valide = commande.validerEtConsommer(stock, menu.getStockCupcakesDuJour());

        if (valide) {
            double total = commande.calculerTotalAvecPromotions(menu.getCupcakesDuJourDisponibles());
            System.out.println("\nCommande validée !");
            System.out.printf("Total à payer : %.2f€%n", total);
        } else {
            System.out.println("\nCommande refusée : stock insuffisant.");
        }
    }
}