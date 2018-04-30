
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

enum STATE {
    MENU, GAME
};
//In all methods, add the following "if" condition:
//if(state=STATE.GAME){
//your code in the method}
//In the draw/paint/render methods, add the same "if" condition but change
//the "state.GAME" to "state.MENU", in order to draw the main menu not the game itself

public class ChangingStates {
    gamepnl gamepnl2 = new gamepnl();

    public static void main(String[] args) {
        Testframe f = new Testframe();
        f.setVisible(true);
    }

}
class PlayingArea extends gamepnl{
    public Rectangle playbutton = new Rectangle(250,150,100,50);
    public Rectangle quitbutton = new Rectangle(250,250,100,50);

    int posx;
    final int posy = 270;

    shape p = new player(15,15,posx,posy,Color.ORANGE);
    shape o = new player(15,50,posx+100,posy,Color.WHITE);
    public PlayingArea(){
        this.addShape(p);
        this.addShape(o);
    }
    public void paint(Graphics g){
        STATE state = Testframe.getGamestate();
        this.setBackground(Color.BLACK);
        if(state==STATE.MENU){
        Graphics2D g2d = (Graphics2D) g;
        Font f1 = new Font("arial",Font.BOLD,50); //create font of game title "Arkanoid"
        g.setFont(f1);
        g.setColor(Color.white);
        g.drawString("Arkanoid", 180, 100);
        Font f2 = new Font("arial",Font.BOLD,20); //create font of the play,quit buttons
        g.setFont(f2);
        g.drawString("Play",playbutton.x+30,playbutton.y+30);
        g.drawString("Quit",quitbutton.x+30,quitbutton.y+35);
        g2d.draw(playbutton);
        g2d.draw(quitbutton);}
        else if(state==STATE.GAME){
            super.paint(g);
            for (int i = 0 ; i<shapes.size() ; i++){
                shape s = (shape)shapes.get(i);
                g.setColor(s.c);
                s.paint(g);
            }
            this.addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
//                    shape s = (shape) this.shapes.get(0);
                    if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
                        p.posx+=10;
                    }
                    else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
                        p.posx-=10;
                    }
                }
            });

            Timer timer = new javax.swing.Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    repaint();
                }
            });
            timer.start();
        }

    }
}
class Testframe extends JFrame {
    static STATE gamestate = STATE.MENU;
    PlayingArea area = new PlayingArea();
    Timer timer;

    public Testframe() {
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Arkanoid v 1.0");
        setResizable(false);
        area.setBackground(Color.black);
        Container c = getContentPane();
        c.add(area);

    }

    // add the functionality of the game here


        //area.removeMouseListener(new MouseAdapter() {});
    public static STATE getGamestate() {
        return gamestate;
    }
}

