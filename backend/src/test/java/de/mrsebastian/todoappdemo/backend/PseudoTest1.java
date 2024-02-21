package de.mrsebastian.todoappdemo.backend;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PseudoTest1 {

    @Test
    void assertSthIsTrue() {
        Assertions.assertTrue(0 >= Math.random());
    }

    @Test
    void assertMoreStuff() {
        val value1 = Math.random();
        val value2 = Math.random();
        Assertions.assertTrue(value1 == value2 || value1 != value2);
    }
}
