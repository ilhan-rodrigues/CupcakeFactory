package org.example.model;

import java.util.List;

public class Cupcake {
    private final Base base;
    private final Creme creme;
    private final List<Topping> toppings;

    public Cupcake(Base base, Creme creme, List<Topping> toppings) {
        if (toppings.size() > 2) {
            throw new IllegalArgumentException("Un cupcake ne peut pas avoir plus de 2 toppings 😭");
        }
        this.base = base;
        this.creme = creme;
        this.toppings = toppings;
    }

    public Base getBase() {
        return base;
    }

    public Creme getCreme() {
        return creme;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public double getPrix() {
        double total = base.getPrix() + creme.getPrix();
        if (toppings.isEmpty()) return total;

        double prixToppings = 0;
        double moinsCher = toppings.get(0).getPrix();

        for (Topping topping : toppings) {
            double prix = topping.getPrix();
            prixToppings += prix;
            if (prix < moinsCher) {
                moinsCher = prix;
            }
        }

        return total + prixToppings - moinsCher;
    }
}
