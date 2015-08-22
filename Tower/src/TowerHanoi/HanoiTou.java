package TowerHanoi;
import java.awt.*;
import javax.swing.*;
public class HanoiTou { 
   long ct = 0;
   public void idou(int n,Tou src,Tou dst,Tou tmp){
      if (n > 0){
         idou(n-1,src,tmp,dst);
         
         int disk=src.rmDisk();
         dst.addDisk(disk);
         try {
             Thread.sleep(500);
           }
         catch(InterruptedException ex) {
             System.err.println(ex);
           }
         ct++;
         idou(n-1,tmp,dst,src);
       }
   } 
   public HanoiTou(int maisuu){
      Tou.dmax=maisuu;
      Tou tou1 = new Tou('A',maisuu);
      Tou tou2 = new Tou('B');
      Tou tou3 = new Tou('C');
      JFrame  dai  = new JFrame("Hanoi");
      dai.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      dai.setLayout(new GridLayout(1,3,0,0));
      dai.add(tou1);
      dai.add(tou2);
      dai.add(tou3);
      dai.pack();
      dai.setVisible(true);
      try { Thread.sleep(500); }
      catch(InterruptedException ex) { System.err.println(ex); }
      idou(maisuu,tou1,tou3,tou2);
   }
   public static void main( String[] args ) {
      int n = Integer.parseInt(args[0]);
      HanoiTou hanoi = new HanoiTou(n);
      System.out.println(hanoi.ct + "a");
   }
}