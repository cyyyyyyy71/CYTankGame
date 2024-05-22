package tankgame;

public class Enemy extends Tank implements Runnable{//敌方坦克为自动转向和发射子弹，实现多线程
    private int bulletInterval = 1000;//子弹间隔时间
    public Enemy(int x, int y, int direction, int type) {
        super(x, y, direction, type);
    }

    @Override
    public void run() {//自动发射子弹
        while (true){
            try {
                Thread.sleep(bulletInterval);//每隔一秒发射一颗子弹
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bullet bullet = new Bullet(this);//以当前坦克状态创建子弹
            this.getBullets().add(bullet);//将新子弹添加入坦克子弹队列
            new Thread(bullet).start();//开启子弹线程
        }

    }
}
