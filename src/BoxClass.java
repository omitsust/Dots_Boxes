/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ai_pro;

import java.util.Arrays;

/**
 *
 * @author Vish Version
 */
public class BoxClass {

    boolean col;
    int heu;
    int line[]=new int[6];
    public static int counter=0;
    boolean flg;
    public BoxClass(int l1,int l2,int l3,int l4)
    {
        heu=4;
        flg=false;
        line[1]=l1;
        line[2]=l2;
        line[3]=l3;
        line[4]=l4;
        Arrays.sort(line, 1, 5);
        System.out.println("line   "+line[1]+" "+line[2]+" "+line[3]+" "+line[4]);
    }



}
