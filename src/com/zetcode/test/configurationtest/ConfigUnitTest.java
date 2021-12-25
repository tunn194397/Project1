package com.zetcode.test.configurationtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.zetcode.configuration.Config;
import com.zetcode.view.OptionsView;

import java.awt.event.ActionEvent;

public class ConfigUnitTest {

    OptionsView test1 = new OptionsView();
    
    @Test
    void checkIfLR_ValueEqualLR_Slider() {
    //    assertEquals(Config.getLR(), test1.LR_ApplyActionPerformed(ActionEvent (e)));
    }
}
