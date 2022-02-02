package ui.painters;

import java.awt.*;
import javax.swing.Timer;

import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.playMode.*;
import java.awt.event.*;
import java.util.Arrays;

public class NoblePainter implements IGameObjectPainter {

    private static final int BLINK_DELAY = 100;
    private Noble noble;
    private Color nobleColor = Color.BLUE;

    enum ColorEvent {
        CHANCE_DECREASE, CHANCE_INCREASED, ABILITY_ACHIEVED
    }

    public NoblePainter(Noble noble) {
        this.noble = noble;
        PlayModeObserver.getInstance().subscribe(EventName.CHANCE_DECREASED, e -> ChangeColor(
                ColorEvent.CHANCE_DECREASE));
        PlayModeObserver.getInstance().subscribe(EventName.CHANCE_INCREASED, e -> ChangeColor(
                ColorEvent.CHANCE_INCREASED));
        PlayModeObserver.getInstance().subscribe(EventName.ABILITY_ACHIEVED, e -> ChangeColor(
                ColorEvent.ABILITY_ACHIEVED));

    }

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(nobleColor);

        g.fillPolygon(noble.getBounds());       

    }
    
    private void ChangeColor(ColorEvent colorEvent) {

        if (colorEvent.equals(ColorEvent.CHANCE_DECREASE))
            FlashColor(Color.RED);
        else if (colorEvent.equals(ColorEvent.CHANCE_INCREASED))
            FlashColor(Color.GREEN);
        else if (colorEvent.equals(ColorEvent.ABILITY_ACHIEVED))
            FlashColor(Color.MAGENTA);

    }

    private void FlashColor(Color color) {
        CreateDelayer(0, e -> setNobleColor(color));
        CreateDelayer(BLINK_DELAY, e -> setNobleColor(Color.BLUE));
        CreateDelayer(BLINK_DELAY * 2, e -> setNobleColor(color));
        CreateDelayer(BLINK_DELAY * 3, e -> setNobleColor(Color.BLUE));
    }

    private void CreateDelayer(int delay, ActionListener action) {
        var timer = new Timer(delay, action);
        timer.setRepeats(false);
        timer.start();
    }

    public void setNobleColor(Color nobleColor) {
        this.nobleColor = nobleColor;
    }

}
