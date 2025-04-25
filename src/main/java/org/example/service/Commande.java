package org.example.service;

import org.example.model.Cupcake;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private final List<Cupcake> cupcakes = new ArrayList<>();

    public void ajouter(Cupcake cupcake) {
        cupcakes.add(cupcake);
    }

    public List<Cupcake> getCupcakes() {
        return cupcakes;
    }

    public double calculerTotal() {
        double total = 0;
        for (Cupcake cupcake : cupcakes) {
            total += cupcake.getPrix();
        }
        return total;
    }

    public int getQuantite() {
        return cupcakes.size();
    }

    public double calculerTotalAvecPromotions(List<Cupcake> cupcakesDuJour) {
        List<Cupcake> horsPromo = new ArrayList<>();
        double total = appliquerRemiseCupcakesDuJour(cupcakesDuJour, horsPromo);
        total -= appliquerRemiseSixiemeGratuit(horsPromo);
        return total;
    }

    private double appliquerRemiseCupcakesDuJour(List<Cupcake> cupcakesDuJour, List<Cupcake> horsPromo) {
        double total = 0;
        for (Cupcake cupcake : cupcakes) {
            if (cupcakesDuJour.contains(cupcake)) {
                total += cupcake.getPrix() * 0.4;
            } else {
                horsPromo.add(cupcake);
                total += cupcake.getPrix();
            }
        }
        return total;
    }

    private double appliquerRemiseSixiemeGratuit(List<Cupcake> horsPromo) {
        if (horsPromo.size() < 6) return 0;

        double moinsCher = horsPromo.get(0).getPrix();
        for (Cupcake cupcake : horsPromo) {
            if (cupcake.getPrix() < moinsCher) {
                moinsCher = cupcake.getPrix();
            }
        }
        return moinsCher;
    }
    public boolean validerEtConsommer(org.example.stock.Stock stock, java.util.Map<Cupcake, Integer> stockCupcakesDuJour) {
        for (Cupcake cupcake : cupcakes) {
            if (!stock.estDisponible(cupcake.getBase()) ||
                !stock.estDisponible(cupcake.getCreme())) {
                return false;
            }
            for (var topping : cupcake.getToppings()) {
                if (!stock.estDisponible(topping)) {
                    return false;
                }
            }
            if (stockCupcakesDuJour != null && stockCupcakesDuJour.containsKey(cupcake)) {
                int restant = stockCupcakesDuJour.get(cupcake);
                if (restant <= 0) {
                    return false;
                }
            }
        }

        for (Cupcake cupcake : cupcakes) {
            stock.consommer(cupcake.getBase());
            stock.consommer(cupcake.getCreme());
            for (var topping : cupcake.getToppings()) {
                stock.consommer(topping);
            }
            if (stockCupcakesDuJour != null && stockCupcakesDuJour.containsKey(cupcake)) {
                stockCupcakesDuJour.put(cupcake, stockCupcakesDuJour.get(cupcake) - 1);
            }
        }

        return true;
    }
}