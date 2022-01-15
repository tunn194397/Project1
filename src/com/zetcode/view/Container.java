package com.zetcode.view;

import com.zetcode.validate.Validate;

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
    public GameView gameView;
    private SettingView settingView;

    private AboutTeamView aboutTeamView;
    private AboutGameView aboutGameView;

    private ThemeView themeView;

    private AGVValidateView agvValidateView;
    private AgentValidateView agentValidateView;
    private AudioValidateView audioValidateView;

    private PlayerView playerView;
    private HighScoreView highScoreView;

    private CardLayout CardLayout;

    public Validate validate = new Validate();
    public int isInGame = 1; // 1 la chua vao game, 2 la dang trong game
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
        this.gameView = new GameView(this);
        this.add(this.gameView, "game_view");
        this.settingView = new SettingView(this);
        this.add(this.settingView, "setting_view");
        this.themeView = new ThemeView(this);
        this.add(this.themeView, "theme_view");

        this.aboutGameView = new AboutGameView(this);
        this.add(this.aboutGameView, "tag_about_game");
        this.aboutTeamView = new AboutTeamView(this);
        this.add(this.aboutTeamView, "tag_about_team");

        this.agvValidateView = new AGVValidateView(this);
        this.add(this.agvValidateView, "agv_validate_view");
        this.agentValidateView = new AgentValidateView(this);
        this.add(this.agentValidateView, "agent_validate_view");
        this.audioValidateView = new AudioValidateView(this);
        this.add(this.audioValidateView, "audio_validate_view");

        this.playerView = new PlayerView(this);
        this.add(this.playerView, "player_view");
        this.highScoreView = new HighScoreView(this);
        this.add(this.highScoreView, "high_score_view");

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
        this.CardLayout.show(this, "setting_view");
        this.settingView.requestFocus();
    }
    public void showViewView() {
        this.CardLayout.show(this, "tag_view");
        this.viewView.requestFocus();
    }
    public void showNewMap() {
        this.CardLayout.show(this,"game_view");
        this.isInGame = 2;
        this.gameView.requestFocus();
    }
    public void setShowMenu() {
        this.CardLayout.show(this, "tag_menu");
        this.homeView.requestFocus();
    }
    public void showAboutGameView() {
        this.CardLayout.show(this, "tag_about_game");
        this.aboutGameView.requestFocus();
    }
    public void showAboutTeamView(){
        this.CardLayout.show(this, "tag_about_team");
        this.aboutTeamView.requestFocus();
    }
    public void showThemeView() {
        this.CardLayout.show(this, "theme_view");
        this.themeView.requestFocus();
    }
    public void showAGVView() {
        this.CardLayout.show(this, "agv_validate_view");
        this.agvValidateView.requestFocus();
    }
    public void showAgentView() {
        this.CardLayout.show(this, "agent_validate_view");
        this.agentValidateView.requestFocus();
    }
    public void showAudioView() {
        this.CardLayout.show(this, "audio_validate_view");
        this.audioValidateView.requestFocus();
    }
    public void showPlayerView() {
        this.CardLayout.show(this, "player_view");
        this.playView.requestFocus();
    }
    public void showHighScoreView() {
        this.CardLayout.show(this, "high_score_view");
        this.highScoreView.requestFocus();
    }
}
