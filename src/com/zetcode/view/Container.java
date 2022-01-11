package com.zetcode.view;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {
    private static final String TAG_PLAY = "tag_play";
    private static final String TAG_HELP = "tag_help";
    private static final String TAG_OPTIONS = "tag_options";
    private static final String TAG_VIEW = "tag_view";
    private static final String TAG_MENU = "tag_menu";
    private static final String TAG_GAME = "game_view";
    private PlayView playView;
    private HelpView helpView;
    private OptionsView optionsView;
    private ViewView viewView;
    private HomeView homeView;
    private UI ui;
    private CardLayout CardLayout;

    public Container() {
        this.setBackground(Color.WHITE);
        this.CardLayout = new CardLayout();
        this.setLayout(this.CardLayout);

        this.homeView = new HomeView(this);
        this.add(this.homeView, "tag_menu");
        this.playView = new PlayView(this);
        this.add(this.playView, "tag_play");
        this.helpView = new HelpView(this);
        this.add(this.helpView, "tag_help");
        this.viewView = new ViewView(this);
        this.add(this.viewView, "tag_view");
        this.ui = new UI(this);
        this.add(this.ui, "game_view");
        this.setShowMenu();
    }

    public void showPlayView() {
        this.CardLayout.show(this, "tag_play");
        this.playView.requestFocus();
    }
    public void showHelpView() {
        this.CardLayout.show(this, "tag_help");
        this.helpView.requestFocus();
    }
    public void showOptionsView() {
        this.CardLayout.show(this, "tag_options");
        this.optionsView.requestFocus();
    }
    public void showViewView() {
        this.CardLayout.show(this, "tag_view");
        this.viewView.requestFocus();
    }
    public void showNewMap() {
        this.CardLayout.show(this,"game_view");
        this.ui.requestFocus();
    }
    public void setShowMenu() {
        this.CardLayout.show(this, "tag_menu");
        this.homeView.requestFocus();
    }


}
