package tankgame;

import javax.swing.*;

public class CYTankGame extends JFrame {

    MyPanel mp=null;//定义面板

    public static void main(String[] args) {
        CYTankGame cyTankGame = new CYTankGame();//开始游戏
    }
    public CYTankGame(){
        mp=new MyPanel();//初始化面板
        new Thread(mp).start();//启动多线程，不断重绘面板
        this.add(mp);//在窗口中加入面板
        this.addKeyListener(mp);//窗口添加键盘监听器
        this.setSize(1000,750);//设置窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭程序方式为JFrame.EXIT_ON_CLOSE
        this.setVisible(true);//可视化
    }
}
