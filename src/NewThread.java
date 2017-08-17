/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ai_pro;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Vish Version
 */
public class  NewThread extends Thread{

    Thread t;
    JLabel lab;
    int count=0,sleep=500;

    ImageIcon i1=new ImageIcon("images\\sky_back.gif");
    ImageIcon i2=new ImageIcon("images\\three\\j2.jpg");
    ImageIcon i3=new ImageIcon("images\\three\\j3.jpg");
    ImageIcon i4=new ImageIcon("images\\three\\j4.jpg");
    ImageIcon i5=new ImageIcon("images\\three\\j5.jpg");
    ImageIcon i6=new ImageIcon("images\\three\\j6.jpg");
    ImageIcon i7=new ImageIcon("images\\three\\i7.jpg");
    ImageIcon i8=new ImageIcon("images\\three\\i8.jpg");
    ImageIcon i9=new ImageIcon("images\\three\\i9.jpg");
    ImageIcon i10=new ImageIcon("images\\three\\i10.jpg");
    ImageIcon i11=new ImageIcon("images\\three\\i11.jpg");
    ImageIcon i12=new ImageIcon("images\\three\\i12.jpg");
    ImageIcon i13=new ImageIcon("images\\three\\i13.jpg");
    ImageIcon i14=new ImageIcon("images\\three\\i14.jpg");
    ImageIcon i15=new ImageIcon("images\\three\\i15.jpg");
    ImageIcon i16=new ImageIcon("images\\three\\i16.jpg");
    ImageIcon i17=new ImageIcon("images\\three\\i17.jpg");
    ImageIcon i18=new ImageIcon("images\\three\\i18.jpg");

//    ImageIcon i1=new ImageIcon("images\\two\\i1.jpg");
//    ImageIcon i2=new ImageIcon("images\\two\\i2.jpg");
//    ImageIcon i3=new ImageIcon("images\\two\\i3.jpg");
//    ImageIcon i4=new ImageIcon("images\\two\\i4.jpg");
//    ImageIcon i5=new ImageIcon("images\\two\\i5.jpg");
//    ImageIcon i6=new ImageIcon("images\\two\\i6.jpg");
//    ImageIcon i7=new ImageIcon("images\\two\\i7.jpg");
//    ImageIcon i8=new ImageIcon("images\\two\\i8.jpg");
//    ImageIcon i9=new ImageIcon("images\\two\\i9.jpg");
//    ImageIcon i10=new ImageIcon("images\\two\\i10.jpg");
//    ImageIcon i11=new ImageIcon("images\\two\\i11.jpg");
//    ImageIcon i12=new ImageIcon("images\\two\\i12.jpg");
//    ImageIcon i13=new ImageIcon("images\\two\\i13.jpg");
//    ImageIcon i14=new ImageIcon("images\\two\\i14.jpg");
//    ImageIcon i15=new ImageIcon("images\\two\\i15.jpg");
//    ImageIcon i16=new ImageIcon("images\\two\\i16.jpg");
//    ImageIcon i17=new ImageIcon("images\\two\\i17.jpg");
//    ImageIcon i18=new ImageIcon("images\\two\\i18.jpg");
//    ImageIcon i19=new ImageIcon("images\\one\\i19.jpg");
//    ImageIcon i20=new ImageIcon("images\\one\\i20.jpg");




    NewThread(JLabel Lab) {

        lab=Lab;

        t=new Thread(this);

        t.start();
    }

    public void run()
    {
       while(true)
       {
           count++;

           lab.setIcon(i1);

//           int nn=1+count%6;
//
//           if(nn==1) lab.setIcon(i1);
//           else if(nn == 2) lab.setIcon(i1);
//           else if(nn == 3) lab.setIcon(i1);
//           else if(nn == 4) lab.setIcon(i1);
//           else if(nn == 5) lab.setIcon(i1);
//           else if(nn == 6) lab.setIcon(i6);
//           else if(nn == 7) lab.setIcon(i7);
//           else if(nn == 8) lab.setIcon(i8);
//           else if(nn == 9) lab.setIcon(i9);
//           else if(nn == 10) lab.setIcon(i10);
//           else if(nn == 11) lab.setIcon(i11);
//           else if(nn == 12) lab.setIcon(i12);
//           else if(nn == 13) lab.setIcon(i13);
//           else if(nn == 14) lab.setIcon(i14);
//           else if(nn == 15) lab.setIcon(i15);
//           else if(nn == 16) lab.setIcon(i16);
//           else if(nn == 17) lab.setIcon(i17);
//           else if(nn == 18) lab.setIcon(i18);
//           else if(nn == 19) lab.setIcon(i19);
//           else if(nn == 20) lab.setIcon(i20);

           try{
               Thread.sleep(sleep);
           }catch(Exception e){}

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
       }

    }
}
