package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener {//实现键盘监听器

    Tank cy=null;
    Tank mzk=null;
    public MyPanel(){
        this.cy = new Tank(10, 10, 0, 0);//初始化己方坦克x，y，方向，类型
        this.mzk = new Tank(500, 500, 0, 1);//初始化敌方坦克x，y，方向，类型
    }

    @Override
    public void paint(Graphics g) {//绘制面板
        super.paint(g);//初始化
        g.fillRect(0,0,1000,750);//绘制区域

        drawTank(g,cy);//绘制己方坦克
        drawTank(g,mzk);//绘制敌方坦克

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
            default:
                System.out.println("+++");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {//输出字符

    }

    @Override
    public void keyPressed(KeyEvent e) {//按下键盘
        int x=cy.getX();//获取按下键盘时cy的横坐标
        int y=cy.getY();//获取按下键盘时cy的纵坐标
        switch (e.getKeyCode()){//检测按下的键
            case KeyEvent.VK_W://向上
                cy.setY(--y);
                break;
            case KeyEvent.VK_S://向下
                cy.setY(++y);
                break;
            case KeyEvent.VK_D://向右
                cy.setX(++x);
                break;
            case KeyEvent.VK_A://向左
                cy.setX(--x);
                break;
            default:
        }
        this.repaint();//重绘
    }

    @Override
    public void keyReleased(KeyEvent e) {//松开键盘

    }
}
