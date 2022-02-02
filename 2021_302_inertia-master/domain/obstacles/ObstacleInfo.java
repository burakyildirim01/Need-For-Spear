package domain.obstacles;

public class ObstacleInfo {

    protected int x;
    protected int y;

    public ObstacleTypes type;
    protected int life = 1;

    public ObstacleInfo(int x, int y, ObstacleTypes type, int life) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.life = life;
    }

    public ObstacleInfo(int x, int y, ObstacleTypes type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.life = 1;
    }

}
