package TankGame;

public class Tank {
    private int x;//横坐标
    private int y;//纵坐标
    private int direction;//0代表向上，1代表向下，2代表向右，3代表向左
    private int type;//0代表己方，1代表敌方
    private int speed=20;//移动速度
    private Bullet bullet=null;

    public Tank(int x, int y, int direction, int type) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.type = type;
    }

    public void moveUp(){//向上移动
        y=y-speed;
    }
    public void moveDown(){//向下移动
        y=y+speed;
    }
    public void moveRight(){//向右移动
        x=x+speed;
    }
    public void moveLeft(){//向左移动
        x=x-speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
