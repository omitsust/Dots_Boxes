/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_pro;

/**
 *
 * @author Vish Version
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame; //imports JFrame library
import javax.swing.JButton; //imports JButton library
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GamePageHard implements ActionListener {

    JFrame frame = new JFrame();
    int com_score=0,user_score=0;
    int total_cnt=0;
    static JLabel user_score_label, com_score_label;
    public BoxClass boxes[] = new BoxClass[10000];
    public LineClass lines[] = new LineClass[10000];
    public JButton buttons[] = new JButton[10000];
    public JLabel labels[] = new JLabel[10000];
    static public int line_counter = 0, box_counter = 0;

    public GamePageHard(int m_row, int n_col) {
        frame.setLayout(null);
        line_counter = 0; box_counter = 0;
        //int window_x = 700, window_y = 400;
        int window_x = 1200, window_y = 700;
        int hor_width = (window_x - 400) / n_col;
        int ver_height = (window_y - 200) / m_row;
        int thickness = ver_height / 4;
        int intial_x = 30, initial_y = 30;
        int score_dimention = 200;
        hor_width = (window_x - score_dimention - intial_x - (thickness * (n_col + 1))) / n_col;
        ver_height = (window_y - (3 * initial_y) - (thickness * (m_row + 1))) / m_row;

        int x = intial_x, y = initial_y;

        for (int i = 0; i <= 2* m_row; i++) {
            if (i % 2 == 1) //vertical
            {
                x = intial_x;
                for (int j = 0; j <= n_col; j++) {
                    line_counter++;

                    JButton bt=new JButton();
                    bt.setOpaque(true);
                    bt.setBackground(SettingsPage.col_dot);
                    bt.setEnabled(false);
                    bt.setBounds(x,y-thickness,thickness,thickness);
                    frame.add(bt);

                    if(i==2*m_row-1)
                    {
                        bt=new JButton();
                        bt.setOpaque(true);
                        bt.setBackground(SettingsPage.col_dot);
                        bt.setEnabled(false);
                        bt.setBounds(x,y+ver_height,thickness,thickness);
                        frame.add(bt);
                    }

                    buttons[line_counter] = new JButton(line_counter + "");
                    buttons[line_counter].setBounds(x, y, thickness, ver_height);
                    buttons[line_counter].setOpaque(true);
                    buttons[line_counter].setBackground(SettingsPage.col_button);
                    buttons[line_counter].setForeground(SettingsPage.col_button);
                    frame.add(buttons[line_counter]);
                    buttons[line_counter].addActionListener(this);
                    lines[line_counter] = new LineClass(i + 1, j + 1);
                    x += hor_width + thickness;
                }
                y += ver_height;
            }

            else {//horizontal
                x = intial_x + thickness;

                int j = 0;

                for (j = 0; j < n_col; j++)
                {
                    line_counter++;

                    buttons[line_counter] = new JButton(line_counter + "");
                    buttons[line_counter].setBounds(x, y, hor_width, thickness);
                    buttons[line_counter].setOpaque(true);
                    buttons[line_counter].setBackground(SettingsPage.col_button);
                    buttons[line_counter].setForeground(SettingsPage.col_button);
                    buttons[line_counter].addActionListener(this);
                    frame.add(buttons[line_counter]);

                    lines[line_counter] = new LineClass(i + 1, j + 1);

                    if (i != 0) {
                        box_counter++;
                        labels[box_counter] = new JLabel();
                        labels[box_counter].setBounds(x,y-ver_height,hor_width,ver_height);
                        labels[box_counter].setOpaque(true);
//                        labels[box_counter].setBackground(SettingsPage.col_lab);
//                        labels[box_counter].setForeground(SettingsPage.col_lab);
                        frame.add(labels[box_counter]);

                        int ln1 = returnLine(i + 1, j + 1);
                        if (lines[ln1].box1 == -1) {
                            lines[ln1].box1 = box_counter;
                        } else if (lines[ln1].box2 == -1) {
                            lines[ln1].box2 = box_counter;
                        }

                        int ln2 = returnLine(i, j + 1);
                        if (lines[ln2].box1 == -1) {
                            lines[ln2].box1 = box_counter;
                        } else if (lines[ln2].box2 == -1) {
                            lines[ln2].box2 = box_counter;
                        }

                        int ln3 = returnLine(i, j + 2);
                        if (lines[ln3].box1 == -1) {
                            lines[ln3].box1 = box_counter;
                        } else if (lines[ln3].box2 == -1) {
                            lines[ln3].box2 = box_counter;
                        }

                        int ln4 = returnLine(i - 1, j + 1);
                        if (lines[ln4].box1 == -1) {
                            lines[ln4].box1 = box_counter;
                        } else if (lines[ln4].box2 == -1) {
                            lines[ln4].box2 = box_counter;
                        }

                        boxes[box_counter] = new BoxClass(ln1, ln2, ln3, ln4);
                    }
                    x += hor_width + thickness;
                }
                y += thickness;
            }
        }

        for (int i = 1; i <= line_counter; i++) {
            System.out.println("line  "+line_counter + "   " + lines[i].r + "  " + lines[i].c + " " + lines[i].box1 + "  " + lines[i].box2);
        }
        
        for (int i = 1; i <= box_counter; i++) {
            System.out.println("Box  "+box_counter + "   " + boxes[i].line[1] + "  " + boxes[i].line[2] + " " + boxes[i].line[3] + "  " + boxes[i].line[4]);
        }

        int label_x = 1030,xPlus=50;
        Font font=new Font("Comic Sans MS", Font.BOLD, 25);

        JLabel user = new JLabel();
        user.setText(SettingsPage.userName);
        user.setFont(font);
        user.setBounds(label_x,20,250,50);
        frame.add(user);

        JLabel com = new JLabel();
        com.setText("Computer");
        com.setBounds(label_x, 600, 250, 50);
        com.setFont(font);
        frame.add(com);

        user_score_label = new JLabel();
        user_score_label.setFont(font);
        user_score_label.setText("0");
        user_score_label.setBounds(label_x+xPlus, 65, 250, 50);
        frame.add(user_score_label);

        com_score_label = new JLabel();
        com_score_label.setFont(font);
        com_score_label.setText("0");
        com_score_label.setBounds(label_x+xPlus, 560, 50, 50);
        frame.add(com_score_label);

        JLabel fpage = new JLabel();
        fpage.setOpaque(true);
        fpage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ai_pro/dots.jpg")));
        fpage.setBounds(label_x+20,160,75,350);
        fpage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("Back to main menu ");
                frame.dispose();
                Main.fp.setVisible(true);

            }
        });
        frame.add(fpage);


        ImageIcon i1=new ImageIcon("images\\sky_back.gif");
        JLabel jLab=new JLabel();
        jLab.setIcon(i1);
        jLab.setBounds(0, 0, window_x, window_y);
        frame.add(jLab);

        //System.out.println("SettingsPage.player "+SettingsPage.player);
        if(SettingsPage.player==2)
        {
            System.out.println("player2 uiuuik");
            comPlay();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame
        frame.setVisible(true); //makes frame visible
        frame.setSize(window_x, window_y);
        frame.setResizable(false);
    }

    public int returnLine(int x, int y) {
        for (int i = 1; i <= line_counter; i++) {
            if (lines[i].r == x && lines[i].c == y) {
                return i;
            }
        }

        return -1;
    }

    public void actionPerformed(ActionEvent e) {
        int line_num = Integer.parseInt(e.getActionCommand());

        if(SettingsPage.music==1) new Sound().play("sounds/notify.wav");
        
        userPlay(line_num);
    }

    private void userPlay(int line_num) {
        if (lines[line_num].flag == false) {
            buttons[line_num].setBackground(SettingsPage.col_user);
            buttons[line_num].setForeground(SettingsPage.col_user);
            lines[line_num].flag = true;

            if (fillBox(line_num, "User")) {
                comPlay();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Already Filled");
        }
    }
    int arr[]=new int[10000];
    private void comPlay() {

        int mx = 0, mx_line = 0,tem_line=0,mx_heu=0,top=0,ind,sum1,chain_box1 = 0,chain_box2 = 0,chain_box3=0,chain_box4=0,chain_mx=0;
        int stack[]=new int[100000];
        ArrayList<Integer> heu_one = new ArrayList<Integer>();
        int heu_one_th[]=new int[100000];
        int from_ind[]=new int[100000];
        Arrays.fill(from_ind, 0);
        Arrays.fill(heu_one_th, 0);
        Arrays.fill(arr, 0);
        boolean chain_flag=false;
        for(int i=1;i<=box_counter;i++)
        {
            if(boxes[i].heu==1&&arr[i]==0)
            {
                ArrayList<Integer> temp_heu = new ArrayList<Integer>();
                top=0;
                stack[top]=i;
                heu_one.add(i);
                temp_heu.add(i);
                sum1=1;
                while(top>=0)
                {
                    boolean fl_chk=false;
                    ind=stack[top];
                    
                    arr[ind]=1;
                    if(lines[boxes[ind].line[1]].flag==false)
                    {
                        if(ind-SettingsPage.n>=1&&(boxes[ind-SettingsPage.n].heu==2||heu_one_th[ind-SettingsPage.n]==2||boxes[ind-SettingsPage.n].heu==3)&&arr[ind-SettingsPage.n]==0)
                        {
                            if(boxes[ind-SettingsPage.n].heu==2)
                            {
                                fl_chk=true;
                                arr[ind-SettingsPage.n]=1;
                                heu_one.add(ind-SettingsPage.n);
                                temp_heu.add(ind-SettingsPage.n);
                                sum1++;
                                top++;
                                stack[top]=ind-SettingsPage.n;
                            }
                            else if(heu_one_th[ind-SettingsPage.n]==2&&from_ind[ind-SettingsPage.n]!=ind)
                            {
                                fl_chk=true;
                                arr[ind-SettingsPage.n]=1;
                                heu_one.add(ind-SettingsPage.n);
                                temp_heu.add(ind-SettingsPage.n);
                                sum1++;
                                top++;
                                stack[top]=ind-SettingsPage.n;
                            }
                            else if(boxes[ind-SettingsPage.n].heu==3)
                            {
                                heu_one_th[ind-SettingsPage.n]=2;
                                from_ind[ind-SettingsPage.n]=ind;
                            }
                            

                        }
                    }
                    if(lines[boxes[ind].line[2]].flag==false)
                    {
                        if(ind%SettingsPage.n!=1&&(boxes[ind-1].heu==2||heu_one_th[ind-1]==2||boxes[ind-1].heu==3)&&arr[ind-1]==0)
                        {
                            if(boxes[ind-1].heu==2)
                            {
                                fl_chk=true;
                                arr[ind-1]=1;
                                heu_one.add(ind-1);
                                temp_heu.add(ind-1);
                                sum1++;
                                top++;
                                stack[top]=ind-1;
                            }
                            else if(heu_one_th[ind-1]==2&&from_ind[ind-1]!=ind)
                            {
                                fl_chk=true;
                                arr[ind-1]=1;
                                heu_one.add(ind-1);
                                temp_heu.add(ind-1);
                                sum1++;
                                top++;
                                stack[top]=ind-1;
                            }
                            else if(boxes[ind-1].heu==3)
                            {
                                heu_one_th[ind-1]=2;
                                from_ind[ind-1]=ind;
                            }
                        }
                    }
                    if(lines[boxes[ind].line[3]].flag==false)
                    {
                        
                        if(ind%SettingsPage.n!=0&&(boxes[ind+1].heu==2||heu_one_th[ind+1]==2||boxes[ind+1].heu==3)&&arr[ind+1]==0)
                        {
                            if(boxes[ind+1].heu==2)
                            {
                                fl_chk=true;
                                arr[ind+1]=1;
                                heu_one.add(ind+1);
                                temp_heu.add(ind+1);
                                sum1++;
                                top++;
                                stack[top]=ind+1;
                            }
                            else if(heu_one_th[ind+1]==2&&from_ind[ind+1]!=ind)
                            {
                                fl_chk=true;
                                arr[ind+1]=1;
                                heu_one.add(ind+1);
                                temp_heu.add(ind+1);
                                sum1++;
                                top++;
                                stack[top]=ind+1;
                            }
                            else if(boxes[ind+1].heu==3)
                            {
                                heu_one_th[ind+1]=2;
                                from_ind[ind+1]=ind;
                            }
                        }
                    }
                    if(lines[boxes[ind].line[4]].flag==false)
                    {
                        if(ind<(box_counter-SettingsPage.n+1)&&(boxes[ind+SettingsPage.n].heu==2||heu_one_th[ind+SettingsPage.n]==2||boxes[ind+SettingsPage.n].heu==3)&&arr[ind+SettingsPage.n]==0)
                        {
                            if(boxes[ind+SettingsPage.n].heu==2)
                            {
                                fl_chk=true;
                                arr[ind+SettingsPage.n]=1;
                                heu_one.add(ind+SettingsPage.n);
                                temp_heu.add(ind+SettingsPage.n);
                                sum1++;
                                top++;
                                stack[top]=ind+SettingsPage.n;
                            }
                            else if(heu_one_th[ind+SettingsPage.n]==2&&from_ind[ind+SettingsPage.n]!=ind)
                            {
                                fl_chk=true;
                                arr[ind+SettingsPage.n]=1;
                                heu_one.add(ind+SettingsPage.n);
                                temp_heu.add(ind+SettingsPage.n);
                                sum1++;
                                top++;
                                stack[top]=ind+SettingsPage.n;
                            }
                            else if(boxes[ind+SettingsPage.n].heu==3)
                            {
                                heu_one_th[ind+SettingsPage.n]=2;
                                from_ind[ind+SettingsPage.n]=ind;
                            }
                        }
                    }
                    if(fl_chk==false)top--;
                }
                
                if(chain_flag==false)
                {
                    if(temp_heu.size()>=2)
                    {
                        for(int jj=0;jj<temp_heu.size();jj++)
                            System.out.print(temp_heu.get(jj) +" ");
                        System.out.println("");
                        for(int in=1;in<temp_heu.size();in++)
                        {
                            if(lines[boxes[temp_heu.get(in)].line[1]].flag==false&&((temp_heu.get(in)>=1&&temp_heu.get(in)<=SettingsPage.n)||(temp_heu.get(in) - SettingsPage.n>=1&&temp_heu.get(in)-SettingsPage.n<=box_counter&&boxes[temp_heu.get(in)-SettingsPage.n].heu>2&&arr[temp_heu.get(in)-SettingsPage.n]==0)))
                            {
                                if(temp_heu.get(in)>=1&&temp_heu.get(in)<=SettingsPage.n)chain_flag=true;
                                chain_mx=temp_heu.size();
                                
                                chain_box1=temp_heu.get(in);
                              
                                if(lines[boxes[temp_heu.get(in)].line[2]].flag==false)
                                {
                                    chain_box2=chain_box1-1;
                                }
                                if(lines[boxes[temp_heu.get(in)].line[3]].flag==false)
                                {
                                    chain_box2=chain_box1+1;
                                }
                                if(lines[boxes[temp_heu.get(in)].line[4]].flag==false)
                                {
                                    chain_box2=chain_box1+SettingsPage.n;
                                }
                                
                            }
                            
                            if(lines[boxes[temp_heu.get(in)].line[2]].flag==false&&(temp_heu.get(in)%SettingsPage.n==1||(temp_heu.get(in) - 1>=1&&temp_heu.get(in)-1<=box_counter&&boxes[temp_heu.get(in)-1].heu>2&&arr[temp_heu.get(in)-1]==0)))
                            {
                                if(temp_heu.get(in)%SettingsPage.n==1)chain_flag=true;
                                chain_mx=temp_heu.size();
                                
                                chain_box1=temp_heu.get(in);
                                if(lines[boxes[temp_heu.get(in)].line[1]].flag==false)
                                {
                                    chain_box2=chain_box1-SettingsPage.n;
                                }
                                if(lines[boxes[temp_heu.get(in)].line[3]].flag==false)
                                {
                                    chain_box2=chain_box1+1;
                                }
                                if(lines[boxes[temp_heu.get(in)].line[4]].flag==false)
                                {
                                    chain_box2=chain_box1+SettingsPage.n;
                                }
                                
                            }
                            
                            if(lines[boxes[temp_heu.get(in)].line[3]].flag==false&&(temp_heu.get(in)%SettingsPage.n==0||(temp_heu.get(in) +1>=1&&temp_heu.get(in)+1<=box_counter&&boxes[temp_heu.get(in)+1].heu>2&&arr[temp_heu.get(in)+1]==0)))
                            {
                                if(temp_heu.get(in)%SettingsPage.n==0)
                                {
                                    chain_flag=true;
                                }
                                chain_mx=temp_heu.size();
                              
                                chain_box1=temp_heu.get(in);
                                if(lines[boxes[temp_heu.get(in)].line[1]].flag==false)
                                {
                                    chain_box2=chain_box1-SettingsPage.n;
                                }
                                if(lines[boxes[temp_heu.get(in)].line[2]].flag==false)
                                {
                                    chain_box2=chain_box1-1;
                                }
                                if(lines[boxes[temp_heu.get(in)].line[4]].flag==false)
                                {
                                    chain_box2=chain_box1+SettingsPage.n;
                                }
                                
                            }
                            
                            if(lines[boxes[temp_heu.get(in)].line[4]].flag==false&&(temp_heu.get(in)>box_counter-SettingsPage.n||(temp_heu.get(in) + SettingsPage.n>=1&&temp_heu.get(in)+SettingsPage.n<=box_counter&&boxes[temp_heu.get(in)+SettingsPage.n].heu>2&&arr[temp_heu.get(in)+SettingsPage.n]==0)))
                            {
                                if(temp_heu.get(in)>box_counter-SettingsPage.n)chain_flag=true;
                                chain_mx=temp_heu.size();
                               
                                chain_box1=temp_heu.get(in);
                                if(lines[boxes[temp_heu.get(in)].line[1]].flag==false)
                                {
                                    chain_box2=chain_box1-SettingsPage.n;
                                }
                                if(lines[boxes[temp_heu.get(in)].line[3]].flag==false)
                                {
                                    chain_box2=chain_box1+1;
                                }
                                if(lines[boxes[temp_heu.get(in)].line[2]].flag==false)
                                {
                                    chain_box2=chain_box1-1;
                                }
                                
                            }
                        }
                    }
                    
                    if(chain_mx==0&&temp_heu.size()>=3)
                    {
                        chain_box2=temp_heu.get(temp_heu.size()-1);
                        chain_box3=temp_heu.get(temp_heu.size()-2);
                        chain_box4=temp_heu.get(temp_heu.size()-3);
                        //chain_box4=temp_heu.get(3);
                        if(chain_box2-1>=1&&chain_box2-1==chain_box3)
                        {
                            if(lines[boxes[chain_box2].line[1]].flag==false)
                            {
                                chain_box1=chain_box2-SettingsPage.n;
                            }
                            if(lines[boxes[chain_box2].line[3]].flag==false)
                            {
                                chain_box1=chain_box2+1;
                            }
                            if(lines[boxes[chain_box2].line[4]].flag==false)
                            {
                                chain_box1=chain_box2+SettingsPage.n;
                            }
                            
                            
                        }
                        if(chain_box2+1<=box_counter&&chain_box2+1==chain_box3)
                        {
                            if(lines[boxes[chain_box2].line[1]].flag==false)
                            {
                                chain_box1=chain_box2-SettingsPage.n;
                            }
                            if(lines[boxes[chain_box2].line[2]].flag==false)
                            {
                                chain_box1=chain_box2-1;
                            }
                            if(lines[boxes[chain_box2].line[4]].flag==false)
                            {
                                chain_box1=chain_box2+SettingsPage.n;
                            }
                        }
                        if(chain_box2-SettingsPage.n>=1&&chain_box2-SettingsPage.n==chain_box3)
                        {
                            if(lines[boxes[chain_box2].line[2]].flag==false)
                            {
                                chain_box1=chain_box2-1;
                            }
                            if(lines[boxes[chain_box2].line[3]].flag==false)
                            {
                                chain_box1=chain_box2+1;
                            }
                            if(lines[boxes[chain_box2].line[4]].flag==false)
                            {
                                chain_box1=chain_box2+SettingsPage.n;
                            }
                        }
                        if(chain_box2+SettingsPage.n<=box_counter&&chain_box2+SettingsPage.n==chain_box3)
                        {
                            if(lines[boxes[chain_box2].line[1]].flag==false)
                            {
                                chain_box1=chain_box2-SettingsPage.n;
                            }
                            if(lines[boxes[chain_box2].line[2]].flag==false)
                            {
                                chain_box1=chain_box2-1;
                            }
                            if(lines[boxes[chain_box2].line[3]].flag==false)
                            {
                                chain_box1=chain_box2+1;
                            }
                            
                        }
                    }
                    
                }
            }
        }


         boolean fl1=false,fl2=false;
         int mx_bx=0;
         mx=0;
         
         for (int i = 1; i <= box_counter; i++)
         {
             int mx1=0;
             int sum=0;
             int free=0,block=0;
             System.out.println("iii "+i+"  "+arr[i]);
             System.out.println("boxes[i].heu   "+boxes[i].heu);
             if(boxes[i].heu==3&&arr[i]==0)
             {
                 free=func_free(i);

             }
             block=func_block(i);
             if((boxes[i].heu==4||free==3)&&block==1)
             {
                fl1=true;
                if(lines[boxes[i].line[1]].flag==false)
                {
                     if(i>=1&&i<=SettingsPage.n)
                     {
                         sum=sum+4;
                     }
                     else
                     {
                         sum=sum+boxes[i-SettingsPage.n].heu;
                     }
                }
                if(lines[boxes[i].line[2]].flag==false)
                {
                    
                     if(SettingsPage.n==1||i%SettingsPage.n==1)
                     {
                         sum=sum+4;
                     }
                     else
                     {
                         sum=sum+boxes[i-1].heu;
                     }
                }
                if(lines[boxes[i].line[3]].flag==false)
                {
                     if(SettingsPage.n==1||i%SettingsPage.n==0)
                     {
                         sum=sum+4;
                     }
                     else
                     {
                         sum=sum+boxes[i+1].heu;
                     }

                }
                if(lines[boxes[i].line[4]].flag==false)
                {
                     if(i>=(box_counter-SettingsPage.n+1)&&i<=box_counter)
                     {
                         sum=sum+4;
                     }
                     else
                     {
                         sum=sum+boxes[i+SettingsPage.n].heu;
                     }

                }
                if(sum>mx)
                 {
                     mx_bx=i;
                     mx=sum;
                     mx_heu=boxes[i].heu;
                 }
                 else if(sum==mx&&boxes[i].heu>mx_heu){
                     mx_bx=i;
                     mx_heu=boxes[i].heu;
                 }

             }
             else
             {
                fl2=true; 
             }

         }
         if(mx_bx>=1)System.out.println("box Free: "+mx_bx);
         int min=99999;
         if(fl1==false&&fl2==true)
         { 
             top=0;ind=0;sum1=0;mx_bx=0;

             for(int i=1;i<=box_counter;i++)
             {
                 if(arr[i]==0&&boxes[i].flg==false&&boxes[i].heu==2)
                 {
                     //System.out.println("iiiii "+i);
                    Arrays.fill(stack, 0);
                    int arra[]=new int[10000];
                    Arrays.fill(arra, 0);
                    int heu_thr[]=new int[10000];
                    Arrays.fill(heu_thr, 0);
                    top=0;
                    stack[top]=i;
                    sum1=1;
                    while(top>=0)
                    {
                        boolean fl_chk=false;
                        ind=stack[top];
                        arra[ind]=1;
                        if(lines[boxes[ind].line[1]].flag==false)
                        {
                            if(ind>SettingsPage.n&&arr[ind-SettingsPage.n]==0&&arra[ind-SettingsPage.n]==0&&(boxes[ind-SettingsPage.n].heu==2||heu_thr[ind-SettingsPage.n]==2||heu_one_th[ind-SettingsPage.n]==2||boxes[ind-SettingsPage.n].heu==3))
                            {
                                //System.out.println("node "+(ind-SettingsPage.n));
                                if(boxes[ind-SettingsPage.n].heu==2)
                                {
                                    fl_chk=true;
                                    arra[ind-SettingsPage.n]=1;
                                    sum1++;
                                    top++;
                                    stack[top]=ind-SettingsPage.n;
                                }
                                else if((heu_thr[ind-SettingsPage.n]==2&&heu_thr[ind]!=1)||heu_one_th[ind-SettingsPage.n]==2)
                                {
                                    fl_chk=true;
                                    arra[ind-SettingsPage.n]=1;
                                    sum1++;
                                    top++;
                                    stack[top]=ind-SettingsPage.n;
                                }
                                else if(boxes[ind-SettingsPage.n].heu==3)
                                {
                                    heu_thr[ind-SettingsPage.n]=2;
                                    heu_thr[ind]=1;
                                }
                            }
                            
                        }
                        if(lines[boxes[ind].line[2]].flag==false)
                        {
                            if(ind%SettingsPage.n!=1&&arra[ind-1]==0&&arr[ind-1]==0&&(boxes[ind-1].heu==2||heu_thr[ind-1]==2||heu_one_th[ind-1]==2||boxes[ind-1].heu==3))
                            {
                                //System.out.println("node "+(ind-1));
                                if(boxes[ind-1].heu==2)
                                {
                                    fl_chk=true;
                                    arra[ind-1]=1;
                                    sum1++;
                                    top++;
                                    stack[top]=ind-1;
                                }
                                else if((heu_thr[ind-1]==2&&heu_thr[ind]!=1)||heu_one_th[ind-1]==2)
                                {
                                    fl_chk=true;
                                    arra[ind-1]=1;
                                    sum1++;
                                    top++;
                                    stack[top]=ind-1;
                                }
                                else if(boxes[ind-1].heu==3)
                                {
                                    heu_thr[ind-1]=2;
                                    heu_thr[ind]=1;
                                }
                            }
                        }
                        if(lines[boxes[ind].line[3]].flag==false)
                        {
                            if(ind%SettingsPage.n!=0&&arra[ind+1]==0&&arr[ind+1]==0&&(boxes[ind+1].heu==2||heu_thr[ind+1]==2||heu_one_th[ind+1]==2||boxes[ind+1].heu==3))
                            {
                                //System.out.println("node "+(ind+1));
                                if(boxes[ind+1].heu==2)
                                {
                                    fl_chk=true;
                                    arra[ind+1]=1;
                                    sum1++;
                                    top++;
                                    stack[top]=ind+1;
                                }
                                else if((heu_thr[ind+1]==2&&heu_thr[ind]!=1)||heu_one_th[ind+1]==2)
                                {
                                    fl_chk=true;
                                    arra[ind+1]=1;
                                    sum1++;
                                    top++;
                                    stack[top]=ind+1;
                                }
                                else if(boxes[ind+1].heu==3)
                                {
                                    heu_thr[ind+1]=2;
                                    heu_thr[ind]=1;
                                }
                            }
                        }
                        if(lines[boxes[ind].line[4]].flag==false)
                        {
                            if(ind<=(box_counter-SettingsPage.n)&&arra[ind+SettingsPage.n]==0&&arr[ind+SettingsPage.n]==0&&(boxes[ind+SettingsPage.n].heu==2||heu_thr[ind+SettingsPage.n]==2||heu_one_th[ind+SettingsPage.n]==2||boxes[ind+SettingsPage.n].heu==3))
                            {
                                //System.out.println("node "+(ind+Main.n));
                                if(boxes[ind+SettingsPage.n].heu==2)
                                {
                                    fl_chk=true;
                                    arra[ind+SettingsPage.n]=1;
                                    sum1++;
                                    top++;
                                    stack[top]=ind+SettingsPage.n;
                                }
                                else if((heu_thr[ind+SettingsPage.n]==2&&heu_thr[ind]!=1)||heu_one_th[ind+SettingsPage.n]==2)
                                {
                                    fl_chk=true;
                                    arra[ind+SettingsPage.n]=1;
                                    sum1++;
                                    top++;
                                    stack[top]=ind+SettingsPage.n;
                                }
                                else if(boxes[ind+SettingsPage.n].heu==3)
                                {
                                    heu_thr[ind+SettingsPage.n]=2;
                                    heu_thr[ind]=1;
                                }
                            }
                        }
                        if(fl_chk==false)top--;
                    }

                    if(sum1<min)
                    {
                        min=sum1;
                        mx_bx=i;
                        System.out.println("mmmm "+mx_bx+"  "+min);
                    }
                 }
             }
             
         }
         
         if((min>=1&&min!=99999))
         {

             if(chain_mx>=1)
             {
                 System.out.println("box selected: "+mx_bx+"  Get "+min);
                 System.out.println("chainbox: "+chain_box1+" "+chain_box2);
                 int lin=0;
                 int dif=Math.abs(chain_box1-chain_box2);
                 if(dif==1)
                 {
                     if(chain_box1>chain_box2)
                     {
                         lin=boxes[chain_box2].line[3];
                     }
                     else
                     {
                         lin=boxes[chain_box1].line[3];

                     }
                 }
                 else
                 {
                     if(chain_box1>chain_box2)
                     {
                         lin=boxes[chain_box2].line[4];
                     }
                     else
                     {
                         lin=boxes[chain_box1].line[4];

                     }
                 }

                 for(int i=0;i<heu_one.size();i++)
                 {
                     if(heu_one.get(i)!=chain_box1&&heu_one.get(i)!=chain_box2)boxes[heu_one.get(i)].flg=true;
                     for(int j=1;j<=4;j++)
                     {
                         if(lines[boxes[heu_one.get(i)].line[j]].flag==false&&boxes[heu_one.get(i)].line[j]!=lin)
                         {
                             buttons[boxes[heu_one.get(i)].line[j]].setBackground(SettingsPage.col_com);
                             buttons[boxes[heu_one.get(i)].line[j]].setForeground(SettingsPage.col_com);
                             lines[boxes[heu_one.get(i)].line[j]].flag = true;
                             fillBox(boxes[heu_one.get(i)].line[j], "Com");
                         }
                     }
                 }
                 
                 
             }
             else if(chain_mx==0&&chain_box1>=1)
             {
                 System.out.println("zzzzzzzzzzzzz");
                 System.out.println(""+chain_box1+"  "+chain_box2+"  "+chain_box3+"  "+chain_box4);
                 
                 for(int i=0;i<heu_one.size();i++)
                 {
                     if(heu_one.get(i)!=chain_box1&&heu_one.get(i)!=chain_box2&&heu_one.get(i)!=chain_box3&&heu_one.get(i)!=chain_box4)boxes[heu_one.get(i)].flg=true;
                     for(int j=1;j<=4;j++)
                     {
                         if(lines[boxes[heu_one.get(i)].line[j]].flag==false)
                         {
                             int ff=0;
                             for(int jj=1;jj<=4;jj++)
                             {
                                 if(boxes[heu_one.get(i)].line[j]==boxes[chain_box1].line[jj]||boxes[heu_one.get(i)].line[j]==boxes[chain_box2].line[jj]||boxes[heu_one.get(i)].line[j]==boxes[chain_box3].line[jj])
                                 {
                                     ff=1;
                                     break;
                                 }
                             }
                             if(ff==0&&heu_one.get(i)!=chain_box1&&heu_one.get(i)!=chain_box2&&heu_one.get(i)!=chain_box3&&heu_one.get(i)!=chain_box4)
                             {
                                buttons[boxes[heu_one.get(i)].line[j]].setBackground(SettingsPage.col_com);
                                buttons[boxes[heu_one.get(i)].line[j]].setForeground(SettingsPage.col_com);
                                lines[boxes[heu_one.get(i)].line[j]].flag = true;
                                fillBox(boxes[heu_one.get(i)].line[j], "Com");
                             }
                         }
                     }
                 }
                 int ln=0;
                 if(boxes[chain_box2].line[1]==boxes[chain_box3].line[4])ln=boxes[chain_box3].line[4];
                 if(boxes[chain_box2].line[4]==boxes[chain_box3].line[1])ln=boxes[chain_box3].line[1];
                 if(boxes[chain_box2].line[2]==boxes[chain_box3].line[3])ln=boxes[chain_box3].line[3];
                 if(boxes[chain_box2].line[3]==boxes[chain_box3].line[2])ln=boxes[chain_box3].line[2];
                 buttons[ln].setBackground(SettingsPage.col_com);
                 buttons[ln].setForeground(SettingsPage.col_com);
                 lines[ln].flag = true;
                 fillBox(ln, "Com");
                 
             }
             else
             {
                 System.out.println("alllll");
                 for(int i=0;i<heu_one.size();i++)
                 {
                     boxes[heu_one.get(i)].flg=true;
                     for(int j=1;j<=4;j++)
                     {
                         if(lines[boxes[heu_one.get(i)].line[j]].flag==false)
                         {
                             buttons[boxes[heu_one.get(i)].line[j]].setBackground(SettingsPage.col_com);
                             buttons[boxes[heu_one.get(i)].line[j]].setForeground(SettingsPage.col_com);
                             lines[boxes[heu_one.get(i)].line[j]].flag = true;
                             fillBox(boxes[heu_one.get(i)].line[j], "Com");
                         }
                     }
                 }
                 if(min==2)
                 {
                     if(lines[boxes[mx_bx].line[1]].flag==false&&mx_bx-SettingsPage.n>=1&&boxes[mx_bx-SettingsPage.n].heu==2)
                     {
                         mx_line=boxes[mx_bx].line[1];
                     }
                     if(lines[boxes[mx_bx].line[2]].flag==false&&SettingsPage.n!=1&&mx_bx%SettingsPage.n!=1&&boxes[mx_bx-SettingsPage.n].heu==2)
                     {
                         mx_line=boxes[mx_bx].line[2];
                     }
                     if(lines[boxes[mx_bx].line[3]].flag==false&&mx_bx%SettingsPage.n!=0&&mx_bx+1<=box_counter&&boxes[mx_bx+1].heu==2)
                     {
                         mx_line=boxes[mx_bx].line[3];
                     }
                     if(lines[boxes[mx_bx].line[4]].flag==false&&mx_bx+SettingsPage.n<=box_counter&&boxes[mx_bx+SettingsPage.n].heu==2)
                     {
                         mx_line=boxes[mx_bx].line[4];
                     }
                 }
                 else mx_line=colouring(mx_bx);
                 buttons[mx_line].setBackground(SettingsPage.col_com);
                 buttons[mx_line].setForeground(SettingsPage.col_com);
                 lines[mx_line].flag = true;
                 fillBox(mx_line, "Com");
             
             }


         }
         else
         {
             for(int i=0;i<heu_one.size();i++)
             {
                 boxes[heu_one.get(i)].flg=true;
                 for(int j=1;j<=4;j++)
                 {
                     if(lines[boxes[heu_one.get(i)].line[j]].flag==false)
                     {
                         buttons[boxes[heu_one.get(i)].line[j]].setBackground(SettingsPage.col_com);
                         buttons[boxes[heu_one.get(i)].line[j]].setForeground(SettingsPage.col_com);
                         lines[boxes[heu_one.get(i)].line[j]].flag = true;
                         fillBox(boxes[heu_one.get(i)].line[j], "Com");
                     }
                 }
             }
             if(mx_bx>=1) mx_line=colouring(mx_bx);
             if(mx_line>=1)
             {
                buttons[mx_line].setBackground(SettingsPage.col_com);
                buttons[mx_line].setForeground(SettingsPage.col_com);
                lines[mx_line].flag = true;
                fillBox(mx_line, "Com");
             }

         }


//        if (mx_line == 0) {
//            JOptionPane.showMessageDialog(null, "Game End");
//            return;
//        }
        
        //lines[mx_line].flag = true;
        //fillBox(mx_line, "Com");
             
    }

    private int colouring(int mx_bx)
    {
         int mx_line=0;
         int mx=0;
         if(lines[boxes[mx_bx].line[1]].flag==false)
         {
             if(mx_bx>=1&&mx_bx<=SettingsPage.n)
             {
                 mx=4;
                 mx_line=boxes[mx_bx].line[1];
             }
             else
             {
                 mx=boxes[mx_bx-SettingsPage.n].heu;
                 mx_line=boxes[mx_bx].line[1];
             }
         }
         if(lines[boxes[mx_bx].line[2]].flag==false)
         {
             if(SettingsPage.n==1||mx_bx%SettingsPage.n==1)
             {
                 mx=4;
                 mx_line=boxes[mx_bx].line[2];
             }
             else if(mx<boxes[mx_bx-1].heu)
             {
                 mx=boxes[mx_bx-1].heu;
                 mx_line=boxes[mx_bx].line[2];
             }
         }
         if(lines[boxes[mx_bx].line[3]].flag==false)
         {
             if(mx_bx%SettingsPage.n==0)
             {
                 mx=4;
                 mx_line=boxes[mx_bx].line[3];
             }
             else if(mx<boxes[mx_bx+1].heu)
             {
                 mx=boxes[mx_bx+1].heu;
                 mx_line=boxes[mx_bx].line[3];
             }
         }
         if(lines[boxes[mx_bx].line[4]].flag==false)
         {
             if(mx_bx>=box_counter-SettingsPage.n+1&&mx_bx<=box_counter)
             {
                 mx=4;
                 mx_line=boxes[mx_bx].line[4];
             }
             else if(mx<boxes[mx_bx+SettingsPage.n].heu)
             {
                 mx=boxes[mx_bx+SettingsPage.n].heu;
                 mx_line=boxes[mx_bx].line[4];
             }
         }
         return mx_line;
    }
    private boolean fillBox(int line_num, String name)
    {
        boolean flg = true;
        //System.out.println("box1: "+lines[line_num].box1+"  box2: "+lines[line_num].box2);

        if (lines[line_num].box1 > 0 && boxes[lines[line_num].box1].heu <= 1) {
            flg = false;
            boxes[lines[line_num].box1].heu = 0;
            total_cnt=total_cnt+1;
            //labels[lines[line_num].box1].setText(name);

            //scoreCalculation(name);

            labels[lines[line_num].box1].setOpaque(true);

            if(name.equals("Com")) labels[lines[line_num].box1].setBackground(SettingsPage.col_lab_com);

            else labels[lines[line_num].box1].setBackground(SettingsPage.col_lab_user);

            scoreCalculation(name);
            for (int k = 1; k <= 4; k++) {
                if (boxes[lines[line_num].box1].line[k] > 0 && lines[boxes[lines[line_num].box1].line[k]].flag == false) {
                    buttons[boxes[lines[line_num].box1].line[k]].setBackground(SettingsPage.col_com);
                    buttons[boxes[lines[line_num].box1].line[k]].setForeground(SettingsPage.col_com);
                    lines[boxes[lines[line_num].box1].line[k]].flag = true;
                }
            }

        }
        else if(lines[line_num].box1 > 0)
        {
            boxes[lines[line_num].box1].heu=boxes[lines[line_num].box1].heu-1;
        }
        if (lines[line_num].box2 > 0 && boxes[lines[line_num].box2].heu <= 1) {
            flg = false;
            boxes[lines[line_num].box2].heu = 0;
            total_cnt=total_cnt+1;
            //labels[lines[line_num].box2].setText(name);

            //scoreCalculation(name);

            labels[lines[line_num].box2].setOpaque(true);

            if(name.equals("Com")) labels[lines[line_num].box2].setBackground(SettingsPage.col_lab_com);

            else labels[lines[line_num].box2].setBackground(SettingsPage.col_lab_user);

            scoreCalculation(name);
            for (int k = 1; k <= 4; k++) {
                if (boxes[lines[line_num].box2].line[k] > 0 && lines[boxes[lines[line_num].box2].line[k]].flag == false) {
                    buttons[boxes[lines[line_num].box2].line[k]].setBackground(SettingsPage.col_com);
                    buttons[boxes[lines[line_num].box2].line[k]].setForeground(SettingsPage.col_com);
                    lines[boxes[lines[line_num].box2].line[k]].flag = true;
                }
            }
        }
        else if(lines[line_num].box2 > 0)
        {
            boxes[lines[line_num].box2].heu=boxes[lines[line_num].box2].heu-1;
        }

        if(total_cnt==box_counter)
        {
            if(com_score>user_score)JOptionPane.showMessageDialog(null, "Computer won!!!");

            if(com_score<user_score)JOptionPane.showMessageDialog(null, "Congratulations " + SettingsPage.userName + "\nYou won !!!!!");
            
            if(com_score==user_score)JOptionPane.showMessageDialog(null, "Match Drawn!!!");
            
            frame.dispose();
            Main.fp.setVisible(true);

        }
        return flg;
    }

    private int func_free(int i)
    {
        int free=0;
        if(lines[boxes[i].line[1]].flag==false&&((i>=1&&i<=SettingsPage.n)||arr[i-SettingsPage.n]==0))
        {
            free++;
        }
        if(lines[boxes[i].line[2]].flag==false&&(SettingsPage.n==1||(i%SettingsPage.n==1)||arr[i-1]==0))
        {
            free++;
        }
        if(lines[boxes[i].line[3]].flag==false&&(SettingsPage.n==1||(i%SettingsPage.n==0)||arr[i+1]==0))
        {
            free++;
        }
        System.out.println("ii"+i);
        System.out.println("box_counter-SettingsPage.n+1   "+(box_counter-SettingsPage.n+1));
        System.out.println("arr[i+SettingsPage.n]  "+arr[i+SettingsPage.n]);
        
        if(lines[boxes[i].line[4]].flag==false&&((i>=(box_counter-SettingsPage.n+1)&&i<=box_counter)||arr[i+SettingsPage.n]==0))
        {
            free++;
        }
        return free;
        
    }
    
    private int func_block(int i)
    {
        //System.out.println("nnn  "+SettingsPage.n );
        if(lines[boxes[i].line[1]].flag==false&&((i>=1&&i<=SettingsPage.n)||boxes[i-SettingsPage.n].heu>2))
        {
            return 1;
        }
        
        if(lines[boxes[i].line[3]].flag==false&&((i%SettingsPage.n==0)||boxes[i+1].heu>2))
        {
            return 1;
        }
        if(lines[boxes[i].line[2]].flag==false&&(SettingsPage.n==1||(i%SettingsPage.n==1)||boxes[i-1].heu>2))
        {
            
            return 1;
        }
        if(lines[boxes[i].line[4]].flag==false&&((i>=(box_counter-SettingsPage.n+1)&&i<=box_counter)||boxes[i+SettingsPage.n].heu>2))
        {
           return 1;
        }
        return 0;
    }
    

    private void scoreCalculation(String name)
    {
        if(name.equals("Com"))
        {
            com_score=com_score+1;
            com_score_label.setText(com_score+"");
            return;
        }

        user_score=user_score+1;
        user_score_label.setText(user_score+"");
        return;
    }

}
