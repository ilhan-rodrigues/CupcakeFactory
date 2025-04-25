package org.example;

import org.example.model.*;
import org.example.service.Commande;
import org.example.service.Menu;
import org.example.service.HistoriqueVentes;
import org.example.stock.Stock;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Ingrédients
        Base baseNature = new Base("Nature", 2.0);
        Creme cremeVanille = new Creme("Vanille", 1.5);
        Topping choco = new Topping("Choco", 0.5);
        Topping caramel = new Topping("Caramel", 0.7);

        // Stock initial
        Stock stock = new Stock();
        stock.ajouterIngredient(baseNature, 50);
        stock.ajouterIngredient(cremeVanille, 30);
        stock.ajouterIngredient(choco, 25);
        stock.ajouterIngredient(caramel, 20);

        // Cupcakes du jour
        Cupcake cupcakeDuJour = new Cupcake(baseNature, cremeVanille, List.of(choco));
        Cupcake cupcakeNormal = new Cupcake(baseNature, cremeVanille, List.of(choco, caramel));

        Menu menu = new Menu(stock);
        menu.ajouterCupcakeDuJour(cupcakeDuJour, 3);

        Scanner scanner = new Scanner(System.in);
        HistoriqueVentes historique = new HistoriqueVentes();
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n=== Cupcakes du jour ===");
            for (Cupcake c : menu.getCupcakesDuJourDisponibles()) {
                System.out.println("- " + c + " à " + String.format("%.2f", c.getPrix() * 0.4) + "€");
            }

            System.out.println("\n=== Ingrédients disponibles ===");
            for (Ingredient ing : menu.getIngredientsDisponibles()) {
                System.out.println("- " + ing.getNom() + " (" + stock.getQuantite(ing) + ")");
            }

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
                historique.enregistrerVente(total);
            } else {
                System.out.println("\nCommande refusée : stock insuffisant.");
            }

            System.out.printf("Chiffre d'affaires total : %.2f€%n", historique.getTotal());

            System.out.print("\nSouhaitez-vous passer une autre commande ? (o/n) : ");
            String reponse = scanner.nextLine();
            if (!reponse.equalsIgnoreCase("o")) {
                continuer = false;
            }
        }
        scanner.close();
    }
}