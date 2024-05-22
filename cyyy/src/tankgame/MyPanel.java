package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener,Runnable {//实现键盘监听器与多线程

    private Hero cy=null;
    private Enemy mzk=null;
    public MyPanel(){
        this.cy = new Hero(10, 10, 0, 0);//初始化己方坦克x，y，方向，类型
        this.mzk = new Enemy(500, 500, 0, 1);//初始化敌方坦克x，y，方向，类型
        new Thread(mzk).start();//启动敌方坦克线程，自动运行
    }

    @Override
    public void paint(Graphics g) {//绘制面板
        super.paint(g);//初始化
        g.fillRect(0,0,1000,750);//绘制区域

        drawTank(g,cy);//绘制己方坦克
        drawTank(g,mzk);//绘制敌方坦克

        drawBullets(g,cy);//绘制己方子弹
        drawBullets(g,mzk);//绘制敌方子弹
    }

    public void drawBullets(Graphics g,Tank tank){//绘制子弹
        //System.out.println(tank.getBullets().size());
        if (tank.getBullets().size()>0){//该坦克有子弹在队列中
            for (int i = 0; i < tank.getBullets().size(); i++) {//遍历子弹队列
                Bullet bullet=tank.getBullets().get(i);//获取第i颗子弹
                //之所以需要isLive是由于可能在上一次遍历绘制时，某一子弹还是存活状态
                //但紧接着就变成了死亡，这时候它还处于队列中，下一次遍历绘制时还会检测到它
                //此时如果发现它死亡，则将其删除
                if(bullet.isLive()){//如果该子弹处于存活状态
                    g.fill3DRect(bullet.getX()-1,bullet.getY()-1,3,3,false);//绘制子弹
                }
                else{//如果该子弹处于死亡状态
                    tank.getBullets().remove(i);//从队列中移除该子弹
                }
            }
        }
    }
    public void drawTank(Graphics g,Tank tank) {//绘制坦克
        int x=tank.getX();//获取坦克横坐标
        int y=tank.getY();//获取坦克纵坐标
        switch (tank.getType()) {//检测坦克类型
            case 0://己方
                g.setColor(Color.GREEN);//绿色
                break;
            case 1://敌方
                g.setColor(Color.yellow);//黄色
                break;
            default:
        }
        switch (tank.getDirection()){//检测坦克方向
            case 0://方向向上
                g.fill3DRect(x,y,10,60,false);//左轮
                g.fill3DRect(x+30,y,10,60,false);//右轮
                g.fill3DRect(x+10,y+10,20,40,false);//身体
                g.fillOval(x+10,y+20,20,20);//炮塔
                g.drawLine(x+20,y+30,x+20,y);//炮管
                break;
            case 1://方向向下
                g.fill3DRect(x,y,10,60,false);//左轮
                g.fill3DRect(x+30,y,10,60,false);//右轮
                g.fill3DRect(x+10,y+10,20,40,false);//身体
                g.fillOval(x+10,y+20,20,20);//炮塔
                g.drawLine(x+20,y+30,x+20,y+60);//炮管
                break;
            case 2://方向向右
                g.fill3DRect(x,y,60,10,false);//上轮
                g.fill3DRect(x,y+30,60,10,false);//下轮
                g.fill3DRect(x+10,y+10,40,20,false);//身体
                g.fillOval(x+20,y+10,20,20);//炮塔
                g.drawLine(x+30,y+20,x+60,y+20);//炮管
                break;
            case 3://方向向左
                g.fill3DRect(x,y,60,10,false);//上轮
                g.fill3DRect(x,y+30,60,10,false);//下轮
                g.fill3DRect(x+10,y+10,40,20,false);//身体
                g.fillOval(x+20,y+10,20,20);//炮塔
                g.drawLine(x+30,y+20,x,y+20);//炮管
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {//输出字符

    }

    @Override
    public void keyPressed(KeyEvent e) {//按下键盘
        switch (e.getKeyCode()){//检测按下的键
            case KeyEvent.VK_W://向上
                cy.moveUp();
                cy.setDirection(0);
                break;
            case KeyEvent.VK_S://向下
                cy.moveDown();
                cy.setDirection(1);
                break;
            case KeyEvent.VK_D://向右
                cy.moveRight();
                cy.setDirection(2);
                break;
            case KeyEvent.VK_A://向左
                cy.moveLeft ();
                cy.setDirection(3);
                break;
            case KeyEvent.VK_J://发射子弹
                Bullet bullet = new Bullet(cy);//以己方坦克状态创建子弹
//                cy.setBullet(bullet);//设置为己方子弹
//                new Thread(bullet).start();//开启子弹线程
                cy.getBullets().add(bullet);//将该子弹加入到己方坦克的子弹队列
                new Thread(bullet).start();//开启该子弹线程
                break;
            default:
                break;
        }
        this.repaint();//按一次键盘就会重绘
    }

    @Override
    public void keyReleased(KeyEvent e) {//松开键盘

    }

    @Override
    public void run() {//面板多线程实现不断重绘
        while (true) {//
            try {
                Thread.sleep(10);//间歇100ms重绘一次
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }
}
