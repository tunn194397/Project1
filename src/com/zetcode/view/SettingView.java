package com.zetcode.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingView extends JPanel implements ActionListener {
    private final Container container;
    private final JButton roomButton = new JButton();
    private final JButton agvButton = new JButton();
    private final JButton agentButton = new JButton();
    private final JButton audioButton = new JButton();
    private final JButton backButton = new JButton();
    private final JButton resumeButton = new JButton();
    public JPanel validatePanel = new JPanel();

    public SettingView(Container Container) {
        this.container = Container;
        this.setLayout((LayoutManager)null);
        this.initCompts();
    }

    public void initCompts() {
        setFocusable(true);
        setBackground(Color.gray);
        setLayout((LayoutManager)null);
        setBounds(0, 0, 900, 700);
        add(validatePanel);

        ImageIcon iconRoomValidate = new ImageIcon("src/images/page/roomValidate.png");
        roomButton.setBounds(50, 150, 300, 80);
        roomButton.addActionListener(this);
        roomButton.setContentAreaFilled(false);
        roomButton.setBorder((Border)null);
        roomButton.setIcon(iconRoomValidate);
        add(this.roomButton);

        ImageIcon iconAgvValidate = new ImageIcon("src/images/page/agvValidate.png");
        agvButton.setBounds(50, 230, 300, 80);
        agvButton.addActionListener(this);
        agvButton.setContentAreaFilled(false);
        agvButton.setBorder((Border)null);
        agvButton.setIcon(iconAgvValidate);
        add(this.agvButton);

        ImageIcon iconAgentAudio = new ImageIcon("src/images/page/agentValidate.png");
        agentButton.setBounds(50, 310, 300, 80);
        agentButton.addActionListener(this);
        agentButton.setContentAreaFilled(false);
        agentButton.setBorder((Border)null);
        agentButton.setIcon(iconAgentAudio);
        add(this.agentButton);

        ImageIcon iconAudio = new ImageIcon("src/images/page/audioButton.png");
        audioButton.setBounds(50, 470, 300, 80);
        audioButton.addActionListener(this);
        audioButton.setContentAreaFilled(false);
        audioButton.setBorder((Border)null);
        audioButton.setIcon(iconAudio);
        add(this.audioButton);

        ImageIcon iconBack = new ImageIcon("src/images/page/backButton.png");
        backButton.setBounds(80, 50, 80, 80);
        backButton.addActionListener(this);
        backButton.setContentAreaFilled(false);
        backButton.setBorder((Border)null);
        backButton.setIcon(iconBack);
        add(this.backButton);

        ImageIcon iconResume = new ImageIcon("src/images/page/resumeButton.png");
        resumeButton.setBounds(1170, 550, 100, 50);
        resumeButton.addActionListener(this);
        resumeButton.setContentAreaFilled(false);
        resumeButton.setBorder((Border)null);
        resumeButton.setIcon(new ImageIcon(iconResume.getImage().getScaledInstance(100,50,1)));
        add(this.resumeButton);

        setUpValidateView();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.roomButton) {
            container.showOptionsView();
        }
        if (e.getSource() == this.agvButton) {
            container.showAGVView();
        }
        if (e.getSource() == this.agentButton) {
            container.showAgentView();
        }
        if (e.getSource() == this.audioButton) {
            container.showAudioView();
        }
        if (e.getSource() == this.backButton) {
            if (container.isInGame == 1) this.container.setShowMenu();
        }
        if (e.getSource() == this.resumeButton) {
            if (container.isInGame == 2) this.container.showNewMap();
        }
    }

    public void setUpValidateView() {

        JSlider maxRoom = new JSlider(JSlider.HORIZONTAL, 0,20, container.validate.maxNumberOfRoom);
        JPanel maxRoomPanel = setUpSliderPanel("S??? ph??ng t???i ??a :",maxRoom, new JTextField(),10,10);

        JSlider maxSizeOfRoom = new JSlider(JSlider.HORIZONTAL, 0,1000, container.validate.maxSizeOfRoom);
        JPanel maxSizeOfRoomPanel = setUpSliderPanel("Di???n t??ch t???i ??a:  ", maxSizeOfRoom, new JTextField(),10,70);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.validate.setValidateRoom(maxRoom.getValue(), maxSizeOfRoom.getValue());
            }
        });
        applyButton.setBounds(250,400, 80,30);
        validatePanel.setBounds(350,150,800,450);
        validatePanel.setLayout(null);
        validatePanel.add(maxRoomPanel);
        validatePanel.add(maxSizeOfRoomPanel);
        validatePanel.add(applyButton);
        validatePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        validatePanel.setBackground(Color.white);
    }

    public JPanel setUpSliderPanel(String s, JSlider slider, JTextField tf, int x, int y) {
        JLabel label = new JLabel(s);
        label.setFont(new Font("Time New Romans",Font.BOLD,18));

        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(null);
        tmpPanel.setBounds(x+10,y+10,700, 60);

        label.setBounds(50,20,300,30);
        slider.setBounds(310,10,300,60);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(slider.getMaximum()/20);
        slider.setMajorTickSpacing(slider.getMaximum()/10);
        slider.setBackground(Color.white);
        tf.setHorizontalAlignment(JLabel.CENTER);
        tf.setBounds(650,25,50,20);
        tf.setText(""+ slider.getValue());

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tf.setText("" + slider.getValue());
            }
        });
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tf.getText().equals("")) {
                    slider.setValue(Integer.parseInt((tf.getText())));
                }
            }
        });
        tmpPanel.add(label);
        tmpPanel.add(slider);
        tmpPanel.add(tf);
        tmpPanel.setBackground(Color.white);
        return tmpPanel;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background_empty.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
        if (container.isInGame == 1) {
            resumeButton.setVisible(false);
            backButton.setVisible(true);
        } else {
            if (container.isInGame == 2) {
                backButton.setVisible(false);
                resumeButton.setVisible(true);
            }
        }
    }
}