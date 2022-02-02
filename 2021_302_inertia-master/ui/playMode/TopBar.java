package ui.playMode;

import domain.*;
import domain.abilities.AbilityTypes;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import ui.editMode.LoginScreen;

import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TopBar extends JPanel {

    private static final Color YmirPurple = Color.decode("#8031A7");
    private int life;
    private int score;
    private boolean hex;
    private boolean expansion;
    private boolean unstoppable;
    private JFrame frame;
    private JButton pauseButton;
    private BufferedImage heartImage;
    private static Rectangle abilitiesRect;

    private String YmirAbilityText = "";
    private Color topBarColor;

    public TopBar(JFrame frame) {
        this.frame = frame;
        YmirAbilityText = "";
        // super();
        PlayModeObserver observer = PlayModeObserver.getInstance();
        observer.subscribe(EventName.UPDATE_CHANCES,
                life -> UpdateLives((int) life));
        observer.subscribe(EventName.UPDATE_SCORE,
                score -> UpdateScore((int) score));
        observer.subscribe(EventName.UPDATE_HEX,
                hex -> UpdateAbility(AbilityTypes.HEX, (boolean) hex));
        observer.subscribe(EventName.UPDATE_EXP,
                expansion -> UpdateAbility(AbilityTypes.EXPANSION, (boolean) expansion));
        observer.subscribe(EventName.UPDATE_UNSTOP,
                unstoppable -> UpdateAbility(AbilityTypes.UNSTOPPABLE, (boolean) unstoppable));
        observer.subscribe(EventName.PAUSE_GAME,
                e -> ChangePauseText());
        observer.subscribe(EventName.FORCE_PAUSE_GAME,
                e -> ChangePauseText());
        observer.subscribe(EventName.YMIR_ABILITY_TEXT,
                str -> setYmirAbilityText((String) str));

        InitButtons();

        try {
            heartImage = ImageIO
                    .read(new File("Assets/Heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        topBarColor = Color.BLUE;
    }

    private void InitButtons() {
        PlayModeObserver obs = PlayModeObserver.getInstance();

        setLayout(null);
        var loadButton = new JButton();
        var width = 80;
        var height = 60;
        loadButton.setBounds(0, Constants.TOP_BAR_HEIGHT / 2 - height / 2, width, height);
        loadButton.setText("Load");
        loadButton.setVisible(true);
        loadButton.setFocusable(false);

        loadButton.addActionListener(e -> {
            obs.notify(EventName.FORCE_PAUSE_GAME, null);
            LoginScreen.getInstance().showLoadScreen(frame);
        });

        add(loadButton);

        var saveButton = new JButton();
        saveButton.setBounds(width, Constants.TOP_BAR_HEIGHT / 2 - height / 2, width, height);
        saveButton.setText("Save");
        saveButton.setVisible(true);
        saveButton.setFocusable(false);

        saveButton.addActionListener(e -> {
            obs.notify(EventName.FORCE_PAUSE_GAME, null);
            LoginScreen.getInstance().showSaveScreen(frame);
        });
        add(saveButton);

        var pauseButton = new JButton();
        pauseButton.setBounds(width * 2, Constants.TOP_BAR_HEIGHT / 2 - height / 2, width, height);
        this.pauseButton = pauseButton;
        pauseButton.setText("Pause");
        pauseButton.setVisible(true);
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(e -> obs.notify(EventName.PAUSE_GAME, null));
        add(pauseButton);

        var quitButton = new JButton();
        quitButton.setBounds(width * 3, Constants.TOP_BAR_HEIGHT / 2 - height / 2, width, height);
        quitButton.setText("Quit");
        quitButton.setVisible(true);
        quitButton.setFocusable(false);
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton);
    }

    private void ChangePauseText() {
        var isPause = pauseButton.getText() == "Pause";

        if (isPause) {
            pauseButton.setText("Resume");
        } else {
            pauseButton.setText("Pause");
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(topBarColor);
        if (topBarColor.getRGB() == YmirPurple.getRGB())
            g.fillRect(0, 0, getWidth(), getHeight());
        else
            g.drawRect(0, 0, getWidth(), getHeight());

        var chancesRect = DrawChancesRect(g);

        var scoreRect = DrawScoreRect(g, chancesRect);

        DrawAbilitiesRect(g);

        DrawYmirAbilityRect(g, scoreRect);

    }

    private Rectangle DrawChancesRect(Graphics g) {
        var chancesDim = new Dimension(60, 40);
        g.setColor(Color.RED);
        var chancesRect = new Rectangle(
                getWidth() - chancesDim.width - 10,
                getHeight() / 2 - chancesDim.height / 2,
                chancesDim.width,
                chancesDim.height);
        g.drawRect(chancesRect.x, chancesRect.y, chancesRect.width, chancesRect.height);

        var livesTextRect = new Rectangle(chancesRect.x + chancesRect.width / 2, chancesRect.y, chancesRect.width / 2,
                chancesRect.height);

        g.drawImage(heartImage, chancesRect.x + 2, chancesRect.y, chancesRect.width / 2, chancesRect.height, null);
        DrawChances(life, g, livesTextRect, Color.BLACK);
        return chancesRect;
    }

    private void DrawChances(int life, Graphics g, Rectangle rect, Color txtColor) {
        String lifeStr = String.valueOf(life);

        Graphics2D g2 = (Graphics2D) g;
        int fontSize = 15;
        Font f = new Font("MS Gothic", Font.PLAIN, fontSize);

        FontMetrics metrics = g.getFontMetrics(f);
        int x = rect.x + (rect.width - metrics.stringWidth(lifeStr)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2.setFont(f);
        g2.setColor(txtColor);
        g2.drawString(lifeStr, x, y);
    }

    private Rectangle DrawScoreRect(Graphics g, Rectangle chancesRect) {
        var scoreDim = new Dimension(150, 40);
        g.setColor(Color.GREEN);
        var scoreRect = new Rectangle(
                getWidth() - scoreDim.width - chancesRect.width - 20,
                getHeight() / 2 - scoreDim.height / 2,
                scoreDim.width,
                scoreDim.height);

        g.drawRect(scoreRect.x, scoreRect.y, scoreRect.width, scoreRect.height);

        var scoreTextRect = new Rectangle(scoreRect.x + 25, scoreRect.y, scoreRect.width / 2,
                scoreRect.height);
        DrawScore(score, g, scoreTextRect, Color.BLACK);
        return scoreRect;
    }

    private void DrawScore(int score, Graphics g, Rectangle rect, Color txtColor) {
        String scoreStr = String.valueOf(score);

        Graphics2D g2 = (Graphics2D) g;
        int fontSize = 15;
        Font f = new Font("MS Gothic", Font.PLAIN, fontSize);

        FontMetrics metrics = g.getFontMetrics(f);
        int x = rect.x + (rect.width) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2.setFont(f);
        g2.setColor(txtColor);
        g2.drawString("Score: ", x - 50, y);
        g2.drawString(scoreStr, x, y);
    }

    private void DrawYmirAbilityRect(Graphics g, Rectangle scoreRectangle) {
        var ymirDim = new Dimension(100, 40);
        int margin = 10;
        var rect = new Rectangle(
                scoreRectangle.x - ymirDim.width - margin,
                (getHeight() - ymirDim.height) / 2,
                ymirDim.width,
                ymirDim.height);

        g.setColor(YmirPurple);

        drawRect(g, rect);
        DrawYmirAbility(g, rect, YmirAbilityText, YmirPurple);

    }

    private void DrawYmirAbility(Graphics g, Rectangle rect, String text, Color txtColor) {
        Graphics2D g2 = (Graphics2D) g;
        int fontSize = 12;
        Font f = new Font("MS Gothic", Font.PLAIN, fontSize);

        FontMetrics metrics = g.getFontMetrics(f);
        g2.setFont(f);
        g2.setColor(txtColor);

        String ymirStr = "Current Ymir:";
        int x1 = rect.x + (rect.width) / 2 - metrics.stringWidth(ymirStr) / 2;
        int y1 = rect.y + ((rect.height - metrics.getHeight()) / 6) + metrics.getAscent();
        g2.drawString(ymirStr, x1, y1);

        f = new Font("MS Gothic", Font.BOLD, 14);
        metrics = g.getFontMetrics(f);
        g2.setFont(f);

        int x2 = rect.x + rect.width / 2 - metrics.stringWidth(text) / 2;
        int y2 = rect.y + ((rect.height - metrics.getHeight()) * 11 / 12) + metrics.getAscent();
        g2.drawString(text, x2, y2);

    }

    private void DrawAbilitiesRect(Graphics g) {
        var abilitiesDim = new Dimension(210, 40);
        g.setColor(Color.magenta);
        var abilitiesRect = new Rectangle(
                getWidth() - abilitiesDim.width - abilitiesDim.width - 140,
                getHeight() / 2 - abilitiesDim.height / 2,
                abilitiesDim.width,
                abilitiesDim.height);
        TopBar.abilitiesRect = abilitiesRect;

        g.drawRect(abilitiesRect.x, abilitiesRect.y, abilitiesRect.width, abilitiesRect.height);
        g.drawLine(abilitiesRect.x + 70, abilitiesRect.y, abilitiesRect.x + 70, abilitiesRect.y + abilitiesRect.height);
        g.drawLine(abilitiesRect.x + 140, abilitiesRect.y, abilitiesRect.x + 140,
                abilitiesRect.y + abilitiesRect.height);
        DrawAbilities(g, abilitiesRect, Color.BLACK);
    }

    private void DrawAbilities(Graphics g, Rectangle rect, Color txtColor) {
        Graphics2D g2 = (Graphics2D) g;
        int fontSize = 15;
        Font f = new Font("MS Gothic", Font.PLAIN, fontSize);

        FontMetrics metrics = g.getFontMetrics(f);
        int x = rect.x + (rect.width) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2.setFont(f);
        g2.setColor(txtColor);
        if (hex)
            g2.drawString("HEX", x - 90, y);
        if (unstoppable)
            g2.drawString("UNS", x - 20, y);
        if (expansion)
            g2.drawString("EXP", x + 50, y);
    }

    public static Rectangle getAbilitiesRect() {
        return abilitiesRect;
    }

    private void UpdateLives(int life) {
        this.life = life;
        repaint();
    }

    private void UpdateScore(int score) {
        this.score = score;
        repaint();
    }

    private void UpdateAbility(AbilityTypes type, boolean toggle) {
        var isNewAbility = false;
        if (type.equals(AbilityTypes.HEX)) {
            isNewAbility = !hex && toggle;
            hex = toggle;
        } else if (type.equals(AbilityTypes.EXPANSION)) {
            isNewAbility = !expansion && toggle;
            expansion = toggle;
        } else {
            isNewAbility = !unstoppable && toggle;
            unstoppable = toggle;
        }
        if (isNewAbility)
            PlayModeObserver.getInstance().notify(EventName.ABILITY_ACHIEVED, null);
        repaint();
    }

    private void drawRect(Graphics g, Rectangle rect) {
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    private void FlashColor(Color color) {

        int BLINK_DELAY = 150;

        CreateDelayer(0, e -> setTopBarColor(color));
        CreateDelayer(BLINK_DELAY, e -> setTopBarColor(Color.BLUE));
        CreateDelayer(BLINK_DELAY * 2, e -> setTopBarColor(color));
        CreateDelayer(BLINK_DELAY * 3, e -> setTopBarColor(Color.BLUE));
    }

    public void setTopBarColor(Color topBarColor) {
        this.topBarColor = topBarColor;
        repaint();
    }

    public void setYmirAbilityText(String ymirAbilityText) {
        YmirAbilityText = ymirAbilityText;
        repaint();
        if (!ymirAbilityText.isEmpty())
            FlashColor(YmirPurple);
    }

    private void CreateDelayer(int delay, ActionListener action) {
        var timer = new Timer(delay, action);
        timer.setRepeats(false);
        timer.start();
    }

}
