


package arkanoid;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
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
class paddle extends Shape{
    
    private int w;
    private int h;
    public paddle(int x,int y,Color c){
        super(x,y,c);
    }
    public  void paint(Graphics g){
        g.setColor(c);  
      g.fillRect(x, y, 150, 20);   
        
    }
}
class Rec extends Shape{
    
    private int w;
    private int h;
    public Rec(int w ,int h,Color c,int x,int y){
        super(x,y,c);
        this.w=w;
        this.h=h;
              
    }
    public  void paint(Graphics g){
      g.setColor(c);  
      g.fillRect(x, y, w, h);   
        
    }
}
class gameFrame extends JPanel{
    private ball b;
    private paddle r1;
    ArrayList<Rec> Recs =new ArrayList();
    public void addRec(Rec r) {
        if (r != null) {
            Recs.add(r);
        }
    }

    public void removeRec(Rec r) {
        if (r != null) {
            Recs.remove(r);
        }
    }

    public gameFrame(ball b,paddle r1){
        this.b=b;
        this.r1=r1;
       
    }
    public void paint(Graphics g) {
        super.paint(g);
        b.paint(g);
        r1.paint(g);
        for (int i = 0; i < Recs.size(); i++) {
            ((Recs.get(i))).paint(g);
        }
    }

public void setpaddle(paddle r){
    this.r1=r;
}
public void setball(ball b){
    this.b=b;
}
}
public class Arkanoid extends JFrame{
    private ArrayList<Rec> row1=new ArrayList();
    private ArrayList<Rec> row2=new ArrayList();
    private ArrayList<Rec> row3=new ArrayList();
    private ArrayList<Rec> row4=new ArrayList();
    private int score;
    private JLabel Score =new JLabel("0");
    private gameFrame game ;
    private ball b ;
    private paddle  rec;
    private int b1x=pConst.GRID_WIDTH/2;
    private int b1y=pConst.GRID_HEIGHT-3;
    private int recx=pConst.GRID_WIDTH/2-3;
    private int recy=pConst.GRID_HEIGHT-2;
    private javax.swing.Timer t;
    private int balldisx=0;
    private int balldisy=0;
    public Arkanoid(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Arkanoid Level_1");
        b=new ball(b1x,b1y,Color.RED);
        rec=new paddle(recx,recy,Color.black);
        game=new gameFrame(b,rec);
        game.setBackground(Color.BLUE);
        Score.setForeground(Color.black);
        game.setLayout(null);
        Score.setBounds(20,20,120,20);
        game.add(Score);
        game.setPreferredSize(new Dimension(pConst.WIDTH*pConst.GRID_WIDTH,pConst.WIDTH*pConst.GRID_HEIGHT));
        this.add(game);
        this.pack();
        game.setFocusable(true);
        for (int i = 2; i < pConst.GRID_WIDTH-2; i+=2) {
            row1.add(new Rec(30,30,Color.red,i,1));
            row2.add(new Rec(30,30,Color.WHITE,i,3));
            row3.add(new Rec(30,30,Color.black,i,5));
            row4.add(new Rec(30,30,Color.green,i,7));
            
        }
         
        for (int i = 0; i <row2.size(); i++) {
            game.addRec(row1.get(i));
            game.addRec(row2.get(i));
            game.addRec(row3.get(i));
            game.addRec(row4.get(i));
        }
        game.repaint();
          t=new javax.swing.Timer(100, new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   b1x+=balldisx;
                   b1y+=balldisy;
                   b=new ball(b1x,b1y,Color.red);
                   game.setball(b);
                   game.repaint();
                   iscollidewithpaddleorwall();
                   iscollidewithbricks();
                   if(b1y==0)
                        balldisy++;
                   
               }
       });
       t.start();
    
    
    
      /*  draw.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                y-=10;
                c=new circ(30,30,Color.ORANGE,x,y);
                draw.setc(c);
                draw.repaint();
                t = new javax.swing.Timer(100, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CalcScore();
                        y -= 20;
                        c = new circ(30, 30, Color.ORANGE, x, y);
                        draw.setc(c);
                        draw.repaint();
                    }
                });
                t.start();
            }
        });
       */
        game.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_LEFT)
                    recx-=1;
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                   recx+=1;  
                rec=new paddle(recx,recy,Color.black);
                game.setpaddle(rec);
                game.repaint();
                
            } 
        });
    }
    public void iscollidewithpaddleorwall(){
            if((b1y==pConst.GRID_HEIGHT-3)&&((b1x>=recx)&&(b1x<=recx+(150/pConst.WIDTH)))){
                       if(b1x>=recx+(90/pConst.WIDTH))
                            balldisx=1;
                       else if(b1x<=recx+(50/pConst.WIDTH))
                           balldisx=-1;
                       else 
                           balldisx=0;
                        balldisy-=1;
                   }
                   
                    if(b1x==pConst.GRID_WIDTH-1)
                        balldisx--;
                    if(b1x==0)
                        balldisx++;
                    if(b1y==0)
                        balldisy++;
    }
     public void iscollidewithbricks(){
           for (int i = 0; i < row2.size(); i+=2) {
           if(((b1x+1>=row1.get(i).getX()&&b1x+1<=row1.get(i).getX()+1.5)&&((row1.get(i).getY()==b1y-1)||(row1.get(i).getY()==b1y+1)))){
                score+=10;
                Score.setText(score+"");
                game.removeRec(row1.get(i));
                if(row1.get(i).getY()==b1y-1)
                    balldisy=1;
                else if(row1.get(i).getY()==b1y+1)
                    balldisy-=1;
                
                 row1.remove(row1.get(i));
                
            }
            if((b1x+1>=row2.get(i).getX()&&b1x+1<=row2.get(i).getX()+1.5)&&((row2.get(i).getY()==b1y-1)||(row2.get(i).getY()==b1y+1))){
                score+=10;
                Score.setText(score+"");
                game.removeRec(row2.get(i));
                if(row2.get(i).getY()==b1y-1)
                    balldisy=1;
                else if(row2.get(i).getY()==b1y+1)
                    balldisy-=1;
                
                 row2.remove(row2.get(i));
                
                
            }
        }
        
     }
  
    public static void main(String[] args) {
        Arkanoid ar =new Arkanoid();
        ar.setVisible(true);
    }
    
}
