package com.zetcode.test.controllertest;

import com.zetcode.model.Facility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.zetcode.controller.buttoncontroller.OptionsController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OptionsControllerUnitTest {

    @Test
    void whenAssertingException_thenThrow() {
    //    Exception e = assertThrows(UnsupportedLookAndFeelException.class, OptionsController.actionPerformed());
        Facility f1 = new Facility();
        boolean x = f1.checkCollision(null);
        assertFalse(x);
    }

}
