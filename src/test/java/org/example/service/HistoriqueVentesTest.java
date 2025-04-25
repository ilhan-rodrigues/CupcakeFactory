package org.example.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoriqueVentesTest {

    @Test
    void testInitialTotalIsZero() {
        HistoriqueVentes historique = new HistoriqueVentes();
        assertThat(historique.getTotal()).isEqualTo(0.0);
    }

    @Test
    void testEnregistrerUneVente() {
        HistoriqueVentes historique = new HistoriqueVentes();
        historique.enregistrerVente(12.5);
        assertThat(historique.getTotal()).isEqualTo(12.5);
    }

    @Test
    void testEnregistrerPlusieursVentes() {
        HistoriqueVentes historique = new HistoriqueVentes();
        historique.enregistrerVente(10.0);
        historique.enregistrerVente(5.5);
        historique.enregistrerVente(3.0);
        assertThat(historique.getTotal()).isEqualTo(18.5);
    }

    @Test
    void testResetHistorique() {
        HistoriqueVentes historique = new HistoriqueVentes();
        historique.enregistrerVente(20.0);
        historique.reset();
        assertThat(historique.getTotal()).isEqualTo(0.0);
    }
}