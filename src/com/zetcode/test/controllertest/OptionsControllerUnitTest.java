package com.zetcode.test.controllertest;

import com.zetcode.model.Facility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class OptionsControllerUnitTest {

    @Test
    void whenAssertingException_thenThrow() {
    //    Exception e = assertThrows(UnsupportedLookAndFeelException.class, OptionsController.actionPerformed());
        Facility f1 = new Facility();
        boolean x = f1.checkCollision((Facility) null);
        assertFalse(x);
    }

}
