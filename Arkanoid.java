
package arkanoid;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
abstract class Shape{
    protected Color c;
    protected int x;
    protected int y;
    public Shape(int x,int y,Color c){
        this.x=x;
        this.c=c;
        this.y=y;
    }
    public abstract void paint(Graphics g);


    }

 class Rec extends Shape{
    
    private int w;
    private int h;
    public Rec(int w,int h,Color c,int x,int y){
        super(x,y,c);
        this.w=w;
        this.h=h;
    }
    public  void paint(Graphics g){
        g.setColor(c);
        g.fillRect(x, y, w, h);
        
    }
}
class circ extends Shape{
    
    private int w;
    private int h;
    public circ(int w,int h,Color c,int x,int y){
        super(x,y,c);
        this.w=w;
        this.h=h;
    }
    public void paint(Graphics g) {
        g.setColor(c);
        g.fillOval(x, y, w, h);

    }
}
class ptpanel extends JPanel {

    private ArrayList shapes = new ArrayList();
    private int x=500;
    private int y=770;
    private int recx;
    private circ c;
    public ptpanel(int x,int y,int recx,circ c){
        this.x=x;
        this.y=y;
        this.recx=recx;
       this.c=c;
    }
    public void addShape(Shape s) {
        if (s != null) {
            shapes.add(s);
        }
    }

    public void removeShape(Shape s) {
        if (s != null) {
            shapes.remove(s);
        }
    }

    public void clearShape() {
        shapes.clear();
    }
    public void move(){
        y-=10;
        this.sety(y);
        
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(recx,800, 200, 30);
        c.paint(g);
        for (int i = 0; i < shapes.size(); i++) {
            ((Shape) (shapes.get(i))).paint(g);
        }
    }
    public void setx(int x){
        this.x=x;
    }
    public void sety(int y){
        this.y=y;
    }
     public int gety(){
        return this.y;
    }
    public void setrecx(int x){
        this.recx=x;
    }
     public void setc(circ c){
        this.c=c;
    }
    
}
public class Arkanoid extends JFrame{
    private Shape[] row1=new Shape[40];
    private Shape[] row2=new Shape[120];
    private int score;private int x=500;private int y=770;
    private int recx;
    private JLabel Score =new JLabel("0");
    private javax.swing.Timer t;
    private circ c=new circ(30,30,Color.ORANGE,x,y);
    private ptpanel draw =new ptpanel(x,y,recx,c);
    public Arkanoid(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        draw.setBackground(Color.BLUE);
        Score.setForeground(Color.yellow);
        draw.setLayout(null);
        Score.setBounds(1000,900,120,20);
        draw.add(Score);
        this.setBounds(100,100,1200,1000);
        this.add(draw);
        for (int i = 0; i < 40; i++) {
            if(i%2==0){
            row1[i]=new Rec(30,30,Color.GREEN,i*30,0);
            row2[i]=new circ(30,30,Color.red,i*30,60); 
            }
            else{
               row1[i]=new circ(30,30,Color.GREEN,i*30,0); 
               row2[i]=new Rec(30,30,Color.red,i*30,60);
            }
        }
         
        for (int i = 0; i < 40; i++) {
            draw.addShape(row1[i]);
            draw.addShape(row2[i]);
        }
        draw.repaint();
        draw.addMouseListener(new MouseAdapter() {
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
       
        draw.addMouseMotionListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                int x=e.getX();
                int y=e.getY();
                //draw.setx(x);
               // draw.sety(y);
                draw.setrecx(x);
                draw.repaint();
            }
        });
    }
    public void CalcScore(){
        for (int i = 0; i < 40; i++) {
            if((c.x+30>=row1[i].x&&c.x+30<=row1[i].x+30||(c.x<row1[i].x&&c.x>row1[i].x))&&(row1[i].y>=c.y)){
                score+=10;
                Score.setText(score+"");
                draw.removeShape(row1[i]);
                
                
            }
            else if((c.x+30>=row2[i].x&&c.x+30<=row2[i].x+30||(c.x<row2[i].x&&c.x>row2[i].x))&&(row2[i].y>=c.y)){
                score+=10;
                Score.setText(score+"");
                draw.removeShape(row2[i]);
                y += 50;
                c = new circ(30, 30, Color.ORANGE, x, y);
                draw.setc(c);
                draw.repaint();
                t.stop();
            }
        }
        draw.repaint();
    }
    public static void main(String[] args) {
        Arkanoid ar =new Arkanoid();
        ar.setVisible(true);
    }
    
}
