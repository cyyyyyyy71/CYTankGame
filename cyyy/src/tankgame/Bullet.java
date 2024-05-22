package tankgame;

public class Bullet implements Runnable{//实现多线程
    private int x;//子弹x坐标
    private int y;//子弹y坐标
    private int direction=0;//子弹方向
    private int speed=5;//子弹速度
    private boolean isLive=true;//子弹存活状态

    public Bullet(Tank tank) {//用发出子弹时的坦克状态来初始化子弹状态
        this.direction = tank.getDirection();//方向为坦克方向
        switch (direction){//由子弹方向决定子弹初始化位置
            case 0://向上
                this.x = tank.getX()+20;
                this.y = tank.getY();
                break;
            case 1://向下
                this.x = tank.getX()+20;
                this.y = tank.getY()+60;
                break;
            case 2://向右
                this.x = tank.getX()+60;
                this.y = tank.getY()+20;
                break;
            case 3://向左
                this.x = tank.getX();
                this.y = tank.getY()+20;
                break;
        }
    }

    @Override
    public void run() {//子弹多线程
        while(isLive){//当子弹还存活
            try {
                Thread.sleep(speed);//间隔一段时间移动一次
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direction){//移动方式由方向决定
                case 0://向上
                    y--;
                    break;
                case 1://向下
                    y++;
                    break;
                case 2://向右
                    x++;
                    break;
                case 3://向左
                    x--;
                    break;
            }
            if (!(x>=0 && x<=1000 && y>=0 && y<=750)){//当子弹移动到面板外
                isLive = false;//子弹死亡

            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
