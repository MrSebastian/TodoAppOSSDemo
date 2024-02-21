package de.mrsebastian.todoappdemo.backend.workflowtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCase {

    @Test
    void isLargerThanZero() {
        Assertions.assertTrue(0 < new HelperClass().doSth1());
    }

}
