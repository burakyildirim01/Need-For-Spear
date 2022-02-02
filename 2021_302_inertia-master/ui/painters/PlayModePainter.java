package ui.painters;

import domain.playMode.PlayMode;
import java.util.ArrayList;

public class PlayModePainter {

    private PlayMode playMode;

    public PlayModePainter(PlayMode playMode) {
        this.playMode = playMode;
    }

    public ArrayList<IGameObjectPainter> InitPainters() {
        var painters = new ArrayList<IGameObjectPainter>();

        painters.add(new NoblePainter(playMode.getNoble()));
        painters.add(new SpherePainter(playMode.getSphere()));
        painters.add(new ObstaclesPainter(playMode.getObstacles()));
        painters.add(new AbilityBoxPainter(playMode.getAbilityBoxes()));
        painters.add(new CannonPainter(playMode.getCanons()));
        painters.add(new BulletPainter());

        return painters;
    }
}
