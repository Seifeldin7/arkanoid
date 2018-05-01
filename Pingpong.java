
package pingpong;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
class pConst{
    public final static int WIDTH = 20;
    public final static int GRID_WIDTH = 50;
    public final static int GRID_HEIGHT = 30;
    public final static int DELAY = 80;
    
}
abstract class Shape{
    
    protected int x;
    protected int y;
    protected Color c;
    protected  BufferedImage m ;
    public Shape(int x, int y,Color c){
        this.x=x*pConst.WIDTH;
        this.y=y*pConst.WIDTH;
        this.c=c;
    }
    
    public int getX() {
        return x/pConst.WIDTH;
    }
    
    public int getY(){
        return y/pConst.WIDTH;
    }
    public abstract void paint(Graphics g);

    }
class ball extends Shape{

    public ball(int x,int y,Color c){
        super(x,y,c);   
    }
    public void paint(Graphics g){
       g.setColor(c);
    g.fillOval(x, y, pConst.WIDTH, pConst.WIDTH);
    
}
}
class Rec extends Shape{
    
    private int w;
    private int h;
    public Rec(int x,int y,Color c){
        super(x,y,c);
    }
    public  void paint(Graphics g){
        g.setColor(c);  
      g.fillRect(x, y, 150, 20);   
        
    }
}
class gameFrame extends JPanel{
    private ball b;
    private Rec r1;
    private Rec r2;
    public gameFrame(ball b,Rec r1,Rec r2){
        this.b=b;
        this.r1=r1;
        this.r2=r2;
    }
public void paint(Graphics g){
    super.paint(g);
    b.paint(g);
    r1.paint(g);
    r2.paint(g);
}
public void setrec2(Rec r){
    this.r2=r;
}
public void setrec1(Rec r){
    this.r1=r;
}
public void setball(ball b){
    this.b=b;
}
}

public class Pingpong extends JFrame{
    private gameFrame game ;
    private ball b ;
    private Rec  rec1;
    private Rec  rec2;
    private int b1x=pConst.GRID_WIDTH/2;
    private int b1y=pConst.GRID_HEIGHT/2;
    private int rec1x=pConst.GRID_WIDTH/2-3;
    private int rec1y=1;
    private int rec2x=pConst.GRID_WIDTH/2-3;
    private int rec2y=pConst.GRID_HEIGHT-2;
    private javax.swing.Timer t;
    private int balldisx=0;
    private int balldisy=1;
    public Pingpong(){
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setResizable(false);
         this.setTitle("PINGPONG");
         b=new ball(b1x,b1y,Color.BLUE);
         rec1=new Rec(rec1x,rec1y,Color.black);
         rec2=new Rec(rec2x,rec2y,Color.black);
         game =new gameFrame(b,rec1,rec2);
         game.setPreferredSize(new Dimension(pConst.WIDTH*pConst.GRID_WIDTH,pConst.WIDTH*pConst.GRID_HEIGHT));
         game.setBackground(Color.YELLOW);
         this.add(game);
         this.pack();
          game.setFocusable(true);
         game.addMouseMotionListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                rec2x=e.getX()/pConst.WIDTH;
                rec2=new Rec(rec2x,rec2y,Color.black);
                game.setrec2(rec2);
                game.repaint();
            }
         });
        game.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_LEFT)
                    rec1x-=1;
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                   rec1x+=1;  
                rec1=new Rec(rec1x,rec1y,Color.black);
                game.setrec1(rec1);
                game.repaint();
                
            } 
        });
       t=new javax.swing.Timer(100, new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   b1x+=balldisx;
                   b1y+=balldisy;
                   b=new ball(b1x,b1y,Color.BLUE);
                   game.setball(b);
                   game.repaint();
                   if((b1y==pConst.GRID_HEIGHT-3)&&((b1x>=rec2x)&&(b1x<=rec2x+(150/pConst.WIDTH)))){
                       if(b1x>=rec2x+(90/pConst.WIDTH))
                            balldisx=1;
                       else if(b1x<=rec2x+(50/pConst.WIDTH))
                           balldisx=-1;
                       else 
                           balldisx=0;
                        balldisy-=1;
                   }
                   
                    if ((b1y == 2) && ((b1x >= rec1x) && (b1x <= rec1x + (150 / pConst.WIDTH)))) {
                       if (b1x >= rec2x + (90 / pConst.WIDTH)) {
                           balldisx = 1;
                       } else if (b1x <= rec2x + (50 / pConst.WIDTH)) {
                           balldisx = -1;
                       }
                        else 
                           balldisx=0;
                       balldisy += 1;
                   }
                    if(b1x==pConst.GRID_WIDTH-1)
                        balldisx--;
                    if(b1x==0)
                        balldisx++;
                   
               }
       });
       t.start();
    }
    
    public static void main(String[] args) {
     Pingpong p =new Pingpong();
     p.setVisible(true);
    }
    
}
