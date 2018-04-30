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
    int posy = 10;
    final int posx = 15;
    images spaceship1 = new images(first,posx,posy);
    shape fire;
    public level1(){
        this.setBounds(400,200,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fire = new shoot(10,10,posx+90,posy+100,Color.ORANGE);
        gamepnl1.shapes.add(spaceship1);
        gamepnl1.repaint();
        gamepnl1.fires.add(fire);
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
        gamepnl1.setFocusable(true);
        gamepnl1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_UP ){
                    spaceship1.posy-=10;
                }
                else if(evt.getKeyCode()==KeyEvent.VK_DOWN ){
                    spaceship1.posy+=10;
                }
//                else if (evt.getKeyCode()==KeyEvent.VK_SPACE){
//                    for (int i = 0 ; i < 800 ; i++){
//                        fire.posx+=10;
//                        gamepnl1.repaint();
//                    }
//                }
            }
        });

        timer = new javax.swing.Timer(100,new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                gamepnl1.repaint();
            }
        });
        timer.start();
    }



    public static void main(String[] args) {
        level1 l = new level1();
        STATE state = Testframe.getGamestate();
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
class shoot extends shape{
    public shoot(int width,int hight,int posx,int posy,Color c){super(width,hight,posx,posy,c);}
    @Override
    public void paint(Graphics g) {
        g.setColor(c);
        g.fillOval(posx,posy,width,hight);
    }
    public void shootfire(){ posx+=10; }
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
        for (int j = 0 ; j<shapes.size() ; j++){
            shape s = (shape) fires.get(j);
            s.paint(g);
        }
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