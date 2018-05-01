package gametest3;

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

public class GameTest3 {

    public static void main(String[] args) {
        Testframe f = new Testframe();
        f.setVisible(true);
    }

}

class PlayingArea extends JPanel {

    private Rectangle playbutton = new Rectangle(247, 100, 100, 50);
    private Rectangle scorebutton = new Rectangle(247, 175, 100, 50);
    private Rectangle quitbutton = new Rectangle(247, 250, 100, 50);
    private ImageIcon image = new ImageIcon("Arkanoid2.jpg");
    private JLabel label = new JLabel(image);
    
    public PlayingArea(){
        setLayout(new BorderLayout());
        add(label);
    }
    
    public void paint(Graphics g) {
        STATE state = Testframe.getGamestate();
        if (state == STATE.MENU) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
//            Font f1 = new Font("arial", Font.BOLD, 50); //create font of game title "Arkanoid"
//            g.setFont(f1);
//            g.setColor(Color.white);
//            g.drawString("Arkanoid", 185, 80);
            Font f2 = new Font("arial", Font.BOLD, 20); //create font of the play,quit buttons
            g.setFont(f2);
            g.setColor(Color.white);
            g.drawString("Play", playbutton.x + 30, playbutton.y + 32);
            g.drawString("Score",scorebutton.x + 22, scorebutton.y + 32);
            g.drawString("Quit", quitbutton.x + 30, quitbutton.y + 32);
            g2d.draw(playbutton);
            g2d.draw(scorebutton);
            g2d.draw(quitbutton);
        } else if (state == STATE.GAME) {
            remove(label);
            super.paint(g);
        }
    }
}

class Testframe extends JFrame {

    static STATE gamestate = STATE.MENU;
    PlayingArea area = new PlayingArea();

    public Testframe() {
        setBounds(100, 100, 600, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Arkanoid v 1.0");
        setResizable(false);
        area.setBackground(Color.white);
        Container c = getContentPane();
        c.add(area);
        area.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x >= 247 && x <= 347) {
                    if (y <= 150 && y >= 100) {
                        gamestate = STATE.GAME;
                    }
                    area.repaint();
                }
                if (x >= 247 && x <= 347) {
                    if (y <= 300 && y >= 250) {
                        System.exit(0);
                    }
                }
            }
        });
        //area.removeMouseListener(new MouseAdapter() {});
    }

    public static STATE getGamestate() {
        return gamestate;
    }
}
