package 大二上学期实训;

import 大二上学期实训.student.查询个人信息;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class 学生操作页面 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenu menu2;
    private JMenu menu3;
    private JMenu menu4;
    private JMenu menu5;
    private JMenu menu6;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JMenuItem menuItem7;
    private JMenuItem menuItem8;
    static public String str,s1,s2;
    static PrintWriter pw = null;
    static BufferedReader br = null;
    Socket client = null;
    //普通代码块总比他们先执行
//    {
//        try {
//            client = new Socket("127.0.0.1",1010);
//            System.out.println("连接成功！");
//            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
//            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public 学生操作页面() {
        frame = new JFrame("学生操作页面");
        panel = new JPanel();
        panel.setBackground(Color.CYAN);
        frame.add(panel);

        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\学生操作页面1.jpeg");
        //panelTop，顶层容器
        JPanel panelTop= new JPanel();
        panelTop=(JPanel)frame.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        panel.setOpaque(false);

        JLabel label1=new JLabel(tt);
        frame.getLayeredPane().add(label1,new Integer(Integer.MIN_VALUE));
        label1.setSize(tt.getIconWidth(),tt.getIconHeight());

        label = new JLabel(Client.name+"同学，你好！欢迎来到创新学分认证系统！"+"\n请完善自己信息！");
        label.setFont(new Font("楷体",Font.BOLD,30));
        panel.add(label);

        menu1 = new JMenu("学分申请");
        menu1.setFont(new Font("楷体",Font.BOLD,30));
        menuItem1 = new JMenuItem("申请");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = menuItem1.getText();
                new DateChooser();
                JOptionPane.showMessageDialog(null,Client.name+"同学，你好！请按照步骤操作！");
            }
        });
        menuItem1.setFont(new Font("楷体",Font.BOLD,30));
        menu1.add(menuItem1);

        menu2 = new JMenu("通信留言");
        menu2.setFont(new Font("楷体",Font.BOLD,30));
        menuItem2 = new JMenuItem("点击留言");
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 留言板();
            }
        });
        menuItem2.setFont(new Font("楷体",Font.BOLD,30));
        menuItem3 = new JMenuItem("通信聊天");
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChatClient();
            }
        });
        menuItem3.setFont(new Font("楷体",Font.BOLD,30));
        menu2.add(menuItem2);
        menu2.add(menuItem3);

        menu3 = new JMenu("查看认证标准");
        menu3.setFont(new Font("楷体",Font.BOLD,30));
        menuItem4 = new JMenuItem("学术论文及科研类");
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 查看认证标准();
            }
        });
        menuItem4.setFont(new Font("楷体",Font.BOLD,30));
        menu3.add(menuItem4);
        JMenuItem menuItem9 = new JMenuItem("科技成果及创业实践");
        menuItem9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 查看认证标准2();
            }
        });
        menuItem9.setFont(new Font("楷体",Font.BOLD,30));
        menu3.add(menuItem9);

        menu4 = new JMenu("修改个人密码");
        menu4.setFont(new Font("楷体",Font.BOLD,30));
        menuItem5 = new JMenuItem("更改密码");
        menuItem5.setFont(new Font("楷体",Font.BOLD,30));
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdatePassword();
                frame.dispose();
            }
        });
        menu4.add(menuItem5);

        menu6 = new JMenu("修改及查询个人信息");
        menu6.setFont(new Font("楷体",Font.BOLD,30));
        menuItem7 = new JMenuItem("修改信息");
        menuItem7.setFont(new Font("楷体",Font.BOLD,30));
        menuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 完善信息(Client.name);
            }
        });
        menuItem8 = new JMenuItem("查询个人信息");
        menuItem8.setFont(new Font("楷体",Font.BOLD,30));
        menuItem8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 查询个人信息(Client.name);
            }
        });
        menu6.add(menuItem7);
        menu6.add(menuItem8);

        menu5 = new JMenu("离开页面");
        menu5.setFont(new Font("楷体",Font.BOLD,30));
        menuItem6 = new JMenuItem("退出");
        menuItem6.setFont(new Font("楷体",Font.BOLD,30));
        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu5.add(menuItem6);

        menuBar = new JMenuBar();
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);
        menuBar.add(menu6);
        menuBar.add(menu5);
        frame.setJMenuBar(menuBar);
        frame.setLocation(400,400);
        frame.setSize(1500,700);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new 学生操作页面();
    }
}
