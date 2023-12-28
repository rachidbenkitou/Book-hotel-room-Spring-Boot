package com.benkitou.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UnitTestingPracticeTest {

    // Will executed before very test
    @BeforeEach
    public void init() {

    }

    @Test
    public void testSuccessDivision() {
        assertThat(10).isEqualTo(calculateDivision(100, 10));
    }

    // Ensure an exception is thrown in the case of division by zero.
    @Test
    public void testThrowExceptionWhenDivisionByZero() {
        assertThatThrownBy(() -> calculateDivision(50, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("Impossible to devise by zero.");
    }

    private int calculateDivision(int a, int b) {
        return a / b;
    }
}
