package com.zetcode.configuration;


import com.zetcode.view.OptionsView;

public class Config extends OptionsView {
    private static int LR;
    private static int ER;
    private static int LF;
    private static int EF;
    private static int agentMax;
    private static int agentRoom;

    public static int getLR() {
        return LR;
    }
    public static void setLR(Object object, int LR) {
        if (object instanceof OptionsView optionsView) {
            if (optionsView.isActive() && optionsView.isVisible()) {
                Config.LR = LR;
            }
        }
    }

   public static int getER() {
       return ER;
   }
   public static void setER(Object object, int ER) {
        if (object instanceof OptionsView optionsView) {
            if (optionsView.isActive() && optionsView.isVisible()) {
                Config.ER = ER;
            }
        }
    }

   public static int getLF() {
        return LF;
   }
   public static void setLF(Object object, int LF) {
        if (object instanceof OptionsView optionsView) {
            if (optionsView.isActive() && optionsView.isVisible()) {
                Config.LF = LF;
            }
        }
    }

   public static int getEF() {
       return EF;
   }
    public static void setEF(Object object, int EF) {
        if (object instanceof OptionsView optionsView) {
            if (optionsView.isActive() && optionsView.isVisible()) {
                Config.EF = EF;
            }
        }
    }

    public static int getAgentMax() {
        return agentMax;
    }
    public static void setAgentMax(Object object, int agentMax) {
        if (object instanceof OptionsView optionsView) {
            if (optionsView.isActive() && optionsView.isVisible()) {
                Config.agentMax = agentMax;
            }
        }
    }

    public static int getAgentRoom() {
        return agentRoom;
    }
    public static void setAgentRoom(Object object, int agentRoom) {
        if (object instanceof OptionsView optionsView) {
            if (optionsView.isActive() && optionsView.isVisible()) {
                Config.agentRoom = agentRoom;
            }
        }
    }
}
