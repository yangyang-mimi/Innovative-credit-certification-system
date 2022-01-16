package 大二上学期实训;

import 大二上学期实训.teacher.成绩评定;
import 大二上学期实训.teacher.教师查看自己名下学生;
import 大二上学期实训.teacher.教师认定标准;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class 教师操作页面 {
    private JFrame frame;
    private JPanel panel;
    private JMenu menu1;
    private JMenu menu2;
    private JMenu menu3;
    private JMenu menu4;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JMenuBar menuBar;
    private JLabel label;
    static String s1,s2,s3,s4,s5,getAll,s6,s7,s8;
    static BufferedReader br = null;
    static PrintWriter pw = null;
    Socket client = null;
    public void lianjie() {
        //普通代码块总比他们先执行
        {
            try {
                client = new Socket("127.0.0.1", 1100);
                System.out.println("连接成功！");
                pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
                br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public 教师操作页面(){
        frame = new JFrame();
        panel =new JPanel();
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

        label = new JLabel(Client.name+"老师，您好！欢迎来到创新学分认证系统！"+"\n请完善自己信息！");
        label.setFont(new Font("楷体",Font.BOLD,20));
        panel.add(label);


        menuBar = new JMenuBar();
        menu1 = new JMenu("审核学分");
        menu1.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu1);
        menuItem1 = new JMenuItem("点击查看");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lianjie();
                s1 = menuItem1.getText();
                new 教师查看自己名下学生();
            }
        });
        menuItem1.setFont(new Font("楷体",Font.BOLD,30));
        menu1.add(menuItem1);

        menu2 = new JMenu("留言与聊天");
        menu2.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu2);
        menuItem2 =new JMenuItem("查看留言");
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lianjie();
                s2 = menuItem2.getText();
                new 留言板();
            }
        });
        menuItem3 = new JMenuItem("聊天");
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s3 = menuItem3.getText();
                new ChatServer();
            }
        });
        menuItem2.setFont(new Font("楷体",Font.BOLD,30));
        menuItem3.setFont(new Font("楷体",Font.BOLD,30));
        menu2.add(menuItem2);
        menu2.add(menuItem3);

        menu3 =  new JMenu("个人信息");
        menu3.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu3);
        menuItem4 = new JMenuItem("查看个人信息");
        menuItem4.setFont(new Font("楷体",Font.BOLD,30));
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s4= menuItem4.getText();
                lianjie();
                pw.println(s4+","+Client.name);
                new 教师查看个人信息(Client.name);
                frame.dispose();
            }
        });
        menu3.add(menuItem4);
        menuItem5 = new JMenuItem("修改个人信息");
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lianjie();
                s5 =  menuItem5.getText();
                pw.println(s5+","+Client.name);
                new 教师完善信息();
            }
        });
        menuItem5.setFont(new Font("楷体",Font.BOLD,30));
        menu3.add(menuItem5);

        menu4 = new JMenu("查看成绩评定标准");
        menu4.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu4);
        menuItem6 = new JMenuItem("认定范围");
        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 教师认定标准();
            }
        });
        menuItem6.setFont(new Font("楷体",Font.BOLD,30));
        menu4.add(menuItem6);
        JMenuItem menuItem7 = new JMenuItem("评定标准");
        menuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 成绩评定();
            }
        });
        menuItem7.setFont(new Font("楷体",Font.BOLD,30));
        menu4.add(menuItem7);

        JMenu menu5 = new JMenu("名下学生");
        menu5.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu5);
        JMenuItem menuItem8 = new JMenuItem("姓名查询");
        menuItem8.setFont(new Font("楷体",Font.BOLD,30));
        menuItem8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new name();
            }
        });
        menu5.add(menuItem8);
        JMenuItem menuItem10 = new JMenuItem("学院查询");
        menuItem10.setFont(new Font("楷体",Font.BOLD,30));
        menuItem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s8 = menuItem10.getText();
                new Dept();
            }
        });
        menu5.add(menuItem10);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.setLocation(500,500);
        frame.setSize(1100,800);
    }

    public static void main(String[] args) {
        new 教师操作页面();
    }
}
