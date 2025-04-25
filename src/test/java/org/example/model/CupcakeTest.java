package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CupcakeTest {
    Base base = new Base("Nature", 2.0);
    Creme creme = new Creme("Vanille", 1.5);
    Topping topping1 = new Topping("Choco", 0.5);
    Topping topping2 = new Topping("Fruits", 0.8);
    Topping topping3 = new Topping("Caramel", 1.0);

    @Test
    void testCupcakeSansTopping() {
        Cupcake cupcake = new Cupcake(base, creme, List.of());
        assertThat(cupcake.getPrix()).isEqualTo(3.5);
    }

    @Test
    void testCupcakeAvecUnTopping() {
        Cupcake cupcake = new Cupcake(base, creme, List.of(topping2)); // 0.8 offert
        assertThat(cupcake.getPrix()).isEqualTo(3.5);
    }

    @Test
    void testCupcakeAvecDeuxToppings() {
        Cupcake cupcake = new Cupcake(base, creme, List.of(topping1, topping3)); // 0.5 offert
        assertThat(cupcake.getPrix()).isEqualTo(4.5); // 2.0 + 1.5 + 1.0
    }

    @Test
    void testCupcakeTropDeToppings() {
        assertThatThrownBy(() -> new Cupcake(base, creme, List.of(topping1, topping2, topping3)))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
