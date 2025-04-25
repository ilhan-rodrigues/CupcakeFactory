package org.example.service;

import org.example.model.*;
import org.example.stock.Stock;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.within;

public class CommandeTest {

    Base base = new Base("Nature", 2.0);
    Creme creme = new Creme("Vanille", 1.5);
    Topping topping1 = new Topping("Choco", 0.5);
    Topping topping2 = new Topping("Caramel", 0.7);

    Cupcake cupcakeNormal = new Cupcake(base, creme, List.of(topping1, topping2));
    Cupcake cupcakeDuJour = new Cupcake(base, creme, List.of(topping2));

    @Test
    void testCommandeValideeEtConsommeLeStock() {
        Stock stock = new Stock();
        stock.ajouterIngredient(base, 2);
        stock.ajouterIngredient(creme, 2);
        stock.ajouterIngredient(topping1, 1);
        stock.ajouterIngredient(topping2, 2);

        Map<Cupcake, Integer> stockDuJour = new HashMap<>();
        stockDuJour.put(cupcakeDuJour, 1);

        Commande commande = new Commande();
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeDuJour);

        boolean valide = commande.validerEtConsommer(stock, stockDuJour);

        assertThat(valide).isTrue();
        assertThat(stock.getQuantite(base)).isEqualTo(0);
        assertThat(stockDuJour.get(cupcakeDuJour)).isEqualTo(0);
    }

    @Test
    void testCommandeRefuseeSiStockInsuffisant() {
        Stock stock = new Stock();
        stock.ajouterIngredient(base, 1);
        stock.ajouterIngredient(creme, 1);
        stock.ajouterIngredient(topping1, 0); // pas assez
        stock.ajouterIngredient(topping2, 1);

        Map<Cupcake, Integer> stockDuJour = new HashMap<>();
        stockDuJour.put(cupcakeDuJour, 1);

        Commande commande = new Commande();
        commande.ajouter(cupcakeNormal); // nécessite topping1
        boolean valide = commande.validerEtConsommer(stock, stockDuJour);

        assertThat(valide).isFalse();
    }

    @Test
    void testTotalAvecPromotions() {
        List<Cupcake> cupcakesDuJour = List.of(cupcakeDuJour);

        Commande commande = new Commande();
        commande.ajouter(cupcakeDuJour); // à -60%
        commande.ajouter(cupcakeNormal); // prix plein
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal);
        commande.ajouter(cupcakeNormal); // devrait déclencher la 6e gratuite

        double total = commande.calculerTotalAvecPromotions(cupcakesDuJour);

        // Le moins cher des 6 "hors promo" est cupcakeNormal (base + crème + 2 toppings - le moins cher)
        double prixUnitaireNormal = cupcakeNormal.getPrix();
        double prixPromo = cupcakeDuJour.getPrix() * 0.4;
        double attendu = prixPromo + 6 * prixUnitaireNormal - prixUnitaireNormal;

        assertThat(total).isCloseTo(attendu, within(0.001));
    }
}