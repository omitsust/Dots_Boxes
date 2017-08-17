/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ai_pro;

/**
 *
 * @author Vish Version
 */
public class LineClass {

    public boolean flag=false;
    int id,r,c,box1=-1,box2=-1;
    public static int counter=0;

    public LineClass(int x,int y)
    {
        id=GamePageHard.line_counter;
        r=x;
        c=y;
        
    }

}
