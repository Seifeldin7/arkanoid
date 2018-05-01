import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class level1 extends JFrame {
    JPanel scorepnl = new JPanel(new FlowLayout());
    gamepnl gamepnl1 = new gamepnl();
    JPanel bigpnl = new JPanel(new BorderLayout());
    JLabel scorboard = new JLabel("0");
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    Timer timer;
    ImageIcon first = new ImageIcon("/Users/peternabil/Desktop/Peter/College/College Material/SPRING'18/arkanoid/first.png");
    ImageIcon enemy = new ImageIcon("/Users/peternabil/Desktop/Peter/College/College Material/SPRING'18/arkanoid/fourth.png");

    int posy = 300;
    final int posx = 15;
    images spaceship1 = new images(first,posx,posy);
    images enemy1 = new images(enemy,posx+800,posy);
    shape fire;
    public level1(){
        this.setBounds(400,200,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamepnl1.shapes.add(spaceship1);
        gamepnl1.shapes.add(enemy1);
        this.setResizable(false);
//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gamepnl1.repaint();
//            }
//        });

        scorboard.setBackground(Color.yellow);
        scorepnl.add(start);
        scorepnl.add(stop);
        scorepnl.add(scorboard);
        bigpnl.add(gamepnl1,BorderLayout.CENTER);
        bigpnl.add(scorepnl,BorderLayout.SOUTH);
        this.add(bigpnl);
//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gamepnl1.shapes.add(enemy1);
//                gamepnl1.repaint();
//            }
//        });


        gamepnl1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_UP ){
                    spaceship1.posy-=10;
                }
                else if(evt.getKeyCode()==KeyEvent.VK_DOWN ){
                    spaceship1.posy+=10;
                }
                else if (evt.getKeyCode()==KeyEvent.VK_SPACE) {
                    for (int i = 0; i < 800; i+=120) {
                        bullet b1 = new bullet(10, 10,posx+i+70,spaceship1.posy+38 ,Color.green,200);
                        gamepnl1.fires.add(b1);
                    }
                    gamepnl1.shoot();
                }
            }
        });
        gamepnl1.setFocusable(true);
        timer = new javax.swing.Timer(100,new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                gamepnl1.repaint();
            }
        });
        timer.start();
    }



    public static void main(String[] args) {
        level1 l = new level1();
        l.setVisible(true);
    }
}



abstract class shape{
    int width;
    int hight;
    int posx;
    int posy;
    Color c;
    public shape(int width,int hight,int posx,int posy,Color c){
        this.width=width;this.hight=hight;this.posx=posx;this.posy=posy;this.c=c;
    }
    public abstract void paint(Graphics g);

}
class player extends shape{
    public player(int width,int hight,int posx,int posy,Color c){super(width,hight,posx,posy,c);}
    @Override
    public void paint(Graphics g) {
      g.setColor(c);
      g.fillRect(posx,posy,width,hight);
    }
}
class enemy extends shape{
    public enemy(int width,int hight,int posx,int posy,Color c){super(width,hight,posx,posy,c);}
    @Override
    public void paint(Graphics g) {
        g.setColor(c);
        g.fillRect(posx,posy,width,hight);
    }
}





class gamepnl extends JPanel{
    public gamepnl(){
        this.setLayout(null);
    }
    ArrayList shapes = new ArrayList();
    ArrayList fires = new ArrayList();
    boolean fire = false;
    public void addShape(shape s) {
        if (s != null) {
            shapes.add(s);
        }
    }
    public void removeShape(shape s) {
        if (s != null) {
            shapes.remove(s);
        }
    }
    public void clearShape() {
        shapes.clear();
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(new ImageIcon("/Users/peternabil/Desktop/Peter/College/College Material/SPRING'18/arkanoid/Wiki-background.jpg").getImage(),0,0,null);
        for (int i = 0 ; i<shapes.size() ; i++){
            images s = (images)shapes.get(i);
            s.paint(g);
        }
        if (fire){
            for (int j = 1 ; j<fires.size();j++){
                bullet b = (bullet)fires.get(j);
                b.paint(g);
                fires.remove(j);
            }
        }
    }
    public void shoot(){
        fire = true;
        this.repaint();
    }
}

class images{
    public int posx,posy;
    public ImageIcon i;
    public images(ImageIcon i , int posx , int posy){
        this.posx = posx; this.posy = posy;this.i=i;
    }
    public void paint(Graphics g) {
        g.drawImage(i.getImage(),posx,posy,null);
    }
}

class bullet extends shape {
    public double speed;
    public bullet(int width, int hight, int posx, int posy, Color c, double speed) {
        super(width, hight, posx, posy, c);
        this.speed = speed;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(c);
        g.fillOval(posx,posy,width,hight);
    }
}