package TowerHanoi;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*; 
public class Tou extends JPanel{ 
   static int dmax;
   char name;
   LinkedList<Integer> pile = new LinkedList<Integer>();
   BufferedImage buffimg = new BufferedImage(
                   200,200,BufferedImage.TYPE_INT_RGB);
   Graphics2D bfg = buffimg.createGraphics();
   
   public Tou(char nam,int maisuu) { 
      name = nam;
      while ( maisuu >0 ){
         pile.add(maisuu);
         maisuu--;
      }
      drawPile(); 
   }
   public Tou(char nam) { 
      name = nam;
      pile.clear();
      drawPile(); 
   }
   public String toString(){ 
       String x = name + ":";
       for ( int p : pile ){
           x= x + p + " ";
       }
       return x;
   }
   public int rmDisk(){
      int diskn = pile.removeLast();
      AnimeRemove(diskn);  
      System.out.println("exec rmDisk "+ diskn);
      drawPile();    
      return diskn;
   }
   public void addDisk(int diskn){
      AnimeAdd(diskn);   
      pile.add(diskn);
      System.out.println("exec addDisk "+ diskn);
      drawPile();   
   }
   public int getMaisuu(){
      return pile.size();    
   }
   public Dimension getPreferredSize() {
      return new Dimension(200,200);
   }
   public void paintComponent(Graphics myg){
      super.paintComponent(myg);
      Graphics2D myg2 = (Graphics2D)myg;
      myg2.drawImage(buffimg,0,0,getSize().width,getSize().height,this);
      
   }
   public void drawPile(){   
      Color bg =new Color(0xee, 0xee, 0xee);
      bfg.setColor(bg);
      bfg.fillRect(0, 0, buffimg.getWidth(), buffimg.getHeight());
      double boux =buffimg.getWidth()/2;
      double minrad =boux*0.1;
      double dr     =boux*0.8/(dmax-1);
      double bottom =buffimg.getHeight()*0.9;
      double dh     =buffimg.getHeight()*0.8/dmax;
      Rectangle2D.Double rec = new Rectangle2D.Double();
      int mai = 0;
      for ( int diskn : pile ){
         mai++;
         int g =(int)(127+128/dmax*diskn);
         int b =(int)(255-255/dmax*(diskn-1));
         bfg.setColor(new Color(0,g,b));
         double radius = minrad+dr*(diskn-1);
         double x = boux-radius;
         double y = bottom-dh*mai;
         rec.setRect(x,y,radius*2,dh);
         bfg.fill(rec);
         bfg.setColor(Color.black);
         bfg.draw(rec);
      }
     repaint();  
   }


   public void AnimeRemove(int diskn){ 
       Color bg =new Color(0xee, 0xee, 0xee);
       int     mai  = getMaisuu()+1; 
       double boux =buffimg.getWidth()/2;
       double minrad =boux*0.1;
       double dr     =boux*0.8/(dmax-1);
       double bottom =buffimg.getHeight()*0.9;
       double dh     =buffimg.getHeight()*0.8/dmax;
       double radius = minrad+dr*(diskn-1);
       double x = boux-radius;
       double y = bottom-dh*mai;
       int g =(int)(127+128/dmax*diskn);
       int b =(int)(255-255/dmax*(diskn-1));
       Color diskcolor = new Color(0,g,b);
       Rectangle2D.Double rec =
           new Rectangle2D.Double(x,y,radius*2,dh-1  );
       bfg.setColor(bg);
       bfg.fill(rec);
       bfg.draw(rec);
       double movdh=(bottom-dh*(mai))/8;
       int movn=0;
       while(y+dh>0){
           movn++;
           y = bottom-dh*mai -movdh*movn;
           rec.setRect(x,y,radius*2,dh);
           bfg.setColor(diskcolor);
           bfg.fill(rec);
           repaint();
           try {Thread.sleep(100);}
           catch(InterruptedException ex) {System.err.println(ex);}
           bfg.setColor(bg);
           bfg.fill(rec);
           bfg.draw(rec);
       }
   }
   public void AnimeAdd(int diskn){ 
       Color bg =new Color(0xee, 0xee, 0xee);
       int   mai  = getMaisuu()+1;
       double boux =buffimg.getWidth()/2;
       double minrad =boux*0.1;
       double dr     =boux*0.8/(dmax-1);
       double bottom =buffimg.getHeight()*0.9;
       double dh     =buffimg.getHeight()*0.8/dmax;
       double radius = minrad+dr*(diskn-1);
       double x = boux-radius;
       double y = 0;
       Rectangle2D.Double rec
          = new Rectangle2D.Double(x,y,radius*2,dh);
       int ct = 0;
       double movdh=(bottom-dh*(mai))/8;
       int g =(int)(127+128/dmax*diskn);
       int b =(int)(255-255/dmax*(diskn-1));
       Color diskcolor = new Color(0,g,b);
       while(bottom-dh*(mai)>y){
           bfg.setColor(diskcolor);
           bfg.fill(rec);
           bfg.setColor(Color.black);
           bfg.draw(rec);
           //paintImmediately((int)x,(int)y,(int)(radius*2),(int)dh);
           repaint();
           try {Thread.sleep(100);}
           catch(InterruptedException ex) {System.err.println(ex);}
           bfg.setColor(bg);
           bfg.fill(rec);
           bfg.draw(rec);
           ct++;
           y = movdh*ct;
           rec.setRect(x,y,radius*2,dh);
      }
   }
}