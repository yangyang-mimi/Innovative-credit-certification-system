package 大二上学期实训.admin;

import 大二上学期实训.student.查看学生信息;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/*
依据学生学院给每位学生分配导师
 */
public class 分配导师 {
     JFrame frame;
     JPanel panel;
     JLabel label1;
     JLabel label2;
     JTextField textField1;
     JTextField textField2;
     JButton button1;
     JButton button2;
     String teacher,dept;
    static BufferedReader br = null;
    static PrintWriter pw = null;
    Socket client = null;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",9090);
            System.out.println("连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public 分配导师() {
            frame =  new JFrame("分配导师");
            panel = new JPanel();
            panel.setLayout(null);
            frame.add(panel);

            ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\天空.jpg");
            //panelTop，顶层容器
            JPanel panelTop= new JPanel();
            panelTop=(JPanel)frame.getContentPane();
            //panel和panelTop设置透明
            panelTop.setOpaque(false);
            panel.setOpaque(false);

            JLabel label1=new JLabel(tt);
            frame.getLayeredPane().add(label1,new Integer(Integer.MIN_VALUE));
            label1.setSize(tt.getIconWidth(),tt.getIconHeight());


            label1 =new JLabel("  请输入学院：    ");
            label1.setFont(new Font("楷体",Font.BOLD,40));
            label1.setBounds(20,20,400,50);
            panel.add(label1);
            textField1 =  new JTextField();
            textField1.setFont(new Font("楷体",Font.BOLD,40));
            textField1.setBounds(300,20,400,50);
            panel.add(textField1);

            label2 =new JLabel("分配的导师的姓名：");
            label2.setFont(new Font("楷体",Font.BOLD,40));
            label2.setBounds(20,100,500,50);
            panel.add(label2);
            textField2 = new JTextField(15);
            textField2.setFont(new Font("楷体",Font.BOLD,40));
            textField2.setBounds(400,100,400,50);
            panel.add(textField2);

            button1 = new JButton("确定");
            button1.setFont(new Font("楷体",Font.BOLD,30));
            button1.setBounds(200,300,200,30);
            button1.setBackground(Color.RED);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dept = textField1.getText();
                    teacher = textField2.getText();
                    String getAll = 管理员操作页面.s3+","+dept+","+teacher;
                    pw.println(getAll);
                    if(Server1.MyThread.k==0) {
                        JOptionPane.showMessageDialog(null, "插入成功！");
                    }else {
                        JOptionPane.showMessageDialog(null,"插入失败","失败",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            button1.setBackground(Color.RED);
            panel.add(button1);

            button2 = new JButton("查看");
            button2.setFont(new Font("楷体",Font.BOLD,30));
            button2.setBounds(500,300,200,30);
            button2.setBackground(Color.RED);
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new 查看学生信息();
                }
            });
            button2.setBackground(Color.RED);
            panel.add(button2);

            frame.setVisible(true);
            frame.setSize(1000,800);
            frame.setLocation(500,500);
            frame.setDefaultCloseOperation(3);
        }

    public static void main(String[] args) {
        new 分配导师();
    }
}
