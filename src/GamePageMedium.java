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
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame; //imports JFrame library
import javax.swing.JButton; //imports JButton library
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GamePageMedium implements ActionListener {

    JFrame frame = new JFrame();
    int com_score=0,user_score=0;
    int total_cnt=0;
    static JLabel user_score_label, com_score_label;
    public BoxClass boxes[] = new BoxClass[10000];
    public LineClass lines[] = new LineClass[10000];
    public JButton buttons[] = new JButton[10000];
    public JLabel labels[] = new JLabel[10000];
    static public int line_counter = 0, box_counter = 0;

    public GamePageMedium(int m_row, int n_col) {
       
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
            System.out.println(line_counter + "   " + lines[i].r + "  " + lines[i].c + " " + lines[i].box1 + "  " + lines[i].box2);
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


    public int returnLine(int x, int y)
    {
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

    private void comPlay() {
        int mx = 0, mx_line = 0,tem_line=0,mx_heu=0;
        boolean chk = false;
        while(true){
            chk=false;
            for (int i = 1; i <= box_counter; i++) {
                //System.out.println(""+boxes[i].heu);
                if (boxes[i].heu == 1) {

                    chk = true;
                    for (int j = 1; j <= 4; j++)
                    {
                        if (lines[boxes[i].line[j]].flag == false) {

                            buttons[boxes[i].line[j]].setBackground(SettingsPage.col_com);
                            buttons[boxes[i].line[j]].setForeground(SettingsPage.col_com);
                            lines[boxes[i].line[j]].flag = true;
                            fillBox(boxes[i].line[j], "Com");
                            break;
                        }
                    }
                    //comPlay();
                }
            }
            if(chk==false)break;
        }
        System.out.println("start:");
        boolean fl1=false,fl2=false;
         for (int i = 1; i <= box_counter; i++)
         {
             int mx1=0;
             int sum=0;
             if(boxes[i].heu>2)
             {
                 fl1=true;
                 if(lines[boxes[i].line[1]].flag==false){
                     if(i>=1&&i<=SettingsPage.n){
                         tem_line=boxes[i].line[1];
                         sum=sum+4;
                         mx1=4;
                     }
                     else{

                         sum=sum+boxes[i-SettingsPage.n].heu;
                         if(boxes[i-SettingsPage.n].heu>mx1)
                         {
                             tem_line=boxes[i].line[3];
                             mx1=boxes[i-SettingsPage.n].heu;
                         }
                     }
                 }
                 if(lines[boxes[i].line[2]].flag==false){
                     if(SettingsPage.n==1||i%SettingsPage.n==1){
                         tem_line=boxes[i].line[2];
                         sum=sum+4;
                         mx1=4;
                     }
                     else
                     {
                         sum=sum+boxes[i-1].heu;
                         if(boxes[i-1].heu>mx1)
                         {
                             tem_line=boxes[i].line[2];
                             mx1=boxes[i-1].heu;
                         }
                     }
                 }
                 if(lines[boxes[i].line[3]].flag==false)
                 {
                     if(i%SettingsPage.n==0)
                     {
                         tem_line=boxes[i].line[3];
                         sum=sum+4;
                         mx1=4;
                     }
                     else
                     {
                         sum=sum+boxes[i+1].heu;
                         if(boxes[i+1].heu>mx1)
                         {
                             tem_line=boxes[i].line[3];
                             mx1=boxes[i+1].heu;
                         }
                     }


                 }
                 if(lines[boxes[i].line[4]].flag==false)
                 {
                     if(i>=(box_counter-SettingsPage.n+1)&&i<=box_counter){
                         tem_line=boxes[i].line[4];
                         sum=sum+4;
                         mx1=4;
                     }
                     else{
                         sum=sum+boxes[i+SettingsPage.n].heu;
                         if(boxes[i+SettingsPage.n].heu>mx1)
                         {
                             tem_line=boxes[i].line[4];
                             mx1=boxes[i+SettingsPage.n].heu;
                         }
                     }


                 }


                 if(sum>mx)
                 {
                     mx_line=tem_line;
                     mx=sum;
                     mx_heu=boxes[i].heu;
                 }
                 else if(sum==mx&&boxes[i].heu>mx_heu){
                     mx_line=tem_line;
                     mx_heu=boxes[i].heu;
                 }
            }
            else if(boxes[i].heu==2)
            {
                fl2=true;
            }

         }
                                
        if(fl1==false&&fl2==true)
        {
            int top=0,min=99999,ind=0,sum1=0,mx_ind=0;

            for(int i=1;i<=box_counter;i++)
            {
                if(boxes[i].heu==2)
                {
                    int stack[]=new int[10000];
                    int arr[]=new int[10000];
                    Arrays.fill(arr, 0);
                    top=0;
                    stack[top]=i;
                    sum1=0;
                    while(top>=0)
                    {
                        boolean fl_chk=false;
                        ind=stack[top];
                        arr[ind]=1;
                        if(lines[boxes[ind].line[1]].flag==false)
                        {
                            if(ind-SettingsPage.n>=1 && boxes[ind-SettingsPage.n].heu==2 && arr[ind-SettingsPage.n]==0)
                            {
                                fl_chk=true;
                                arr[ind-SettingsPage.n]=1;
                                sum1++;
                                top++;
                                stack[top]=ind-SettingsPage.n;
                            }
                        }
                        if(lines[boxes[ind].line[2]].flag==false)
                        {
                            if(ind-1>=1&&boxes[ind-1].heu==2&&arr[ind-1]==0)
                            {
                                fl_chk=true;
                                arr[ind-1]=1;
                                sum1++;
                                top++;
                                stack[top]=ind-1;
                            }
                        }
                        if(lines[boxes[ind].line[3]].flag==false)
                        {
                            if(ind+1<=box_counter&&boxes[ind+1].heu==2&&arr[ind+1]==0)
                            {
                                fl_chk=true;
                                arr[ind+1]=1;
                                sum1++;
                                top++;
                                stack[top]=ind+1;
                            }
                        }
                        if(lines[boxes[ind].line[4]].flag==false)
                        {
                            if(ind+SettingsPage.n<=box_counter&&boxes[ind+SettingsPage.n].heu==2&&arr[ind+SettingsPage.n]==0)
                            {
                                fl_chk=true;
                                arr[ind+SettingsPage.n]=1;
                                sum1++;
                                top++;
                                stack[top]=ind+SettingsPage.n;
                            }
                        }
                        if(fl_chk==false)top--;
                    }

                    if(sum1<min)
                    {
                        min=sum1;
                        mx_ind=i;
                    }
                }
            }

            for(int in=1;in<=4;in++)
            {
                if(lines[boxes[mx_ind].line[in]].flag==false)mx_line=boxes[mx_ind].line[in];
            }
        }
        
        
        
        buttons[mx_line].setBackground(SettingsPage.col_com);
        buttons[mx_line].setForeground(SettingsPage.col_com);
        lines[mx_line].flag = true;
        fillBox(mx_line, "Com");
             
    }

    private boolean fillBox(int line_num, String name)
    {
        boolean flg = true;
        //System.out.println("box1: "+lines[line_num].box1+"  box2: "+lines[line_num].box2);
        
        if (lines[line_num].box1 > 0 && boxes[lines[line_num].box1].heu <= 1) {
            flg = false;
            boxes[lines[line_num].box1].heu = 0;
            total_cnt=total_cnt+1;
           // labels[lines[line_num].box1].setText(name);
            scoreCalculation(name);

            labels[lines[line_num].box1].setOpaque(true);

            if(name.equals("Com")) labels[lines[line_num].box1].setBackground(SettingsPage.col_lab_com);

            else labels[lines[line_num].box1].setBackground(SettingsPage.col_lab_user);

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
           // labels[lines[line_num].box2].setText(name);

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

    private void scoreCalculation(String name)
    {
        if(name.equals("Com"))
        {
            com_score++;
            com_score_label.setText(com_score+"");
            return;
        }

        user_score++;
        user_score_label.setText(user_score+"");
        return;
    }

}
