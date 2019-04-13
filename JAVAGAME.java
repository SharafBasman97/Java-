package fgame;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

class Game extends JFrame{
  Container c ;   
  Game(){
      
        JFXPanel music= new JFXPanel();
        String musicFile = new File("C:\\Users\\Sharaf\\Downloads\\game.mp3").toURI().toString();
        new MediaPlayer(new Media(musicFile)).play();
      
       this.setTitle("COLLECT FRUITS !");
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Toolkit kit = Toolkit.getDefaultToolkit();
       Image icon = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\download.jpg");
       this.setIconImage(icon);
       Dimension dim = kit.getScreenSize();
       this.setSize((int)dim.getWidth(), (int)dim.getHeight());
       this.setLocation(0,0);
       c=this.getContentPane();
       c.add(new game());
       this.setVisible(true);
       
    }
 class gameover extends JPanel{
     gameover(){
     this.setFocusable(true);
      }
 public void paintComponent(Graphics g)   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      Toolkit kit = Toolkit.getDefaultToolkit();
    // Image background = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\B.png");
    Image background = kit.getImage("C:\\Users\\Sharaf\\Downloads\\55.jpg");
    Image gameover = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\3.png");
       g2.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
      g2.drawImage(gameover, 0, 0, this.getWidth(), this.getHeight(), this);
       
       
 }
 } 
 
 class game extends JPanel{
     JPanel panel=this;
     Font font=new Font("Copperplate Gothic Bold",3,40);
     JLabel poi = new JLabel("0");
     JLabel points = new JLabel("Points-->  ");
     JLabel total = new JLabel(":: Total-->   ");
     JLabel tot = new JLabel("0");
     int x,y,i,life;
     int H[] =new int [10];
     int W[] =new int [10];
     int randomh[] =new int [10];
     game(){
         
        this.addKeyListener(new KL());
       life = 3 ;
        for(int i=0 ; i<10;i++){
              W[i]=(int)(Math.random()*1300);
              if(i<5)
               randomh[i]=(100*i);
              else 
                randomh[i]=(300*i);   
         }
        points.setFont(font);
        poi.setFont(font);
        tot.setFont(font);
        total.setFont(font);
        poi.setForeground(Color.black);
        points.setForeground(Color.black);
        total.setForeground(Color.black);
        tot.setForeground(Color.black);
        this.add(points); 
        this.add(poi);
        this.add(total);
        this.add(tot);
        this.setFocusable(true);
        //this.addKeyListener(new KL());
        }
    
  public void paintComponent(Graphics g)   {
      super.paintComponent(g);
      
       Graphics2D g2 = (Graphics2D)g;
      Toolkit kit = Toolkit.getDefaultToolkit();
      y= this.getHeight()-150;
      Image background = kit.getImage("C:\\Users\\Sharaf\\Downloads\\55.jpg");
      Image basket = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\2.png");
      g2.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
      g2.drawImage(basket, x,y, this);
      
            Image imgs[] = new Image[8];
            imgs[0] = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\1.png");
            imgs[1] = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\4.png");
            imgs[2] = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\5.png");
            imgs[3] = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\6.png");
            imgs[4] = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\7.png");
            imgs[5] = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\8.png");
            imgs[6] = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\9.png");
            imgs[7] = kit.getImage("C:\\Users\\Sharaf\\Desktop\\FGAME\\10.png");

      for(i=0 ; i<8 ;i++){
          int l =H[i]-randomh[i];
          g2.drawImage(imgs[i],W[i],l, this);
          new Timer(i).start();
          
          
          if(Find(W[i],  l) !=0 ){
           g2.setFont(font);
           W[i] = (int)(Math.random()*1300);
           H[i]=100;    
          poi.setText(String.valueOf((Integer.parseInt(poi.getText()) )  +  10));
          tot.setText(String.valueOf((Integer.parseInt(tot.getText()) )  +  1));
         
         
       }  
        if ( (Find(W[i],  l) == 0)&&( l == this.getHeight()) ) { life -- ;
                  g2.setColor(Color.PINK);
                  g2.setFont(font); 
                 poi.setText(String.valueOf((Integer.parseInt(poi.getText()))-10));
                 
                 if (life == 0){
                     //panel.removeAll();
                     c.removeAll();
                    c.add(new gameover());
                    c.doLayout();
                 }
           }
        
  
     }
  }
  int Find (int xa,int ya ){
     if ( (xa>=x && xa<=x+239)&&(ya>=y && ya<=y+170))
       return x;
    
    return 0;
}
 class KL implements KeyListener{
        boolean shift=false;
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode()==KeyEvent.VK_SHIFT)
  
    shift=true;
if (e.getKeyCode() == KeyEvent.VK_LEFT)
  {if (shift)
        x-=20;
if(x<0)
x= x*-1;
else 
    x-=10;
}
    else
if (e.getKeyCode() == KeyEvent.VK_RIGHT)    
{ if (shift  && x>1100)
        x=x-20;
if(shift)
    x+=20;


if(x>1100)
x=x-10;
else 
    x+=10;}
panel.repaint();
        }
        public void keyTyped(KeyEvent e){
        }
        public void keyReleased(KeyEvent e){
         if (e.getKeyCode()==KeyEvent.VK_SHIFT)
         shift=false;
        }
 }//KL 
 
 class Timer extends Thread{
 int i ;

          public Timer(int i) {
              this.i = i;
          }

 
 public void run(){
      try{
          while (true){
           
           H[i]+=1;
           Thread.sleep(500);
           panel.repaint();
          }
          
          }catch(Exception e){
              e.printStackTrace();
          }
      }
  } //   
  
 }//panel
}//Frame





public class FGAME {

    public static void main(String[] args) {
        new Game();
    }
    
}
