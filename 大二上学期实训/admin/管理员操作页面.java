package 大二上学期实训.admin;

import 大二上学期实训.student.查看学生信息;
import 大二上学期实训.查看教师信息;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class 管理员操作页面 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton button1;
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenu menu2;
    private JMenu menu3;
    private JMenu menu4;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    static String s1,s2,s3,s4,s5;
    public 管理员操作页面() {
        frame = new JFrame("管理员操作页面");
        panel = new JPanel();
        frame.add(panel);

        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\背景1.jpeg");
        //panelTop，顶层容器
        JPanel panelTop= new JPanel();
        panelTop=(JPanel)frame.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        panel.setOpaque(false);

        JLabel label1=new JLabel(tt);
        frame.getLayeredPane().add(label1,new Integer(Integer.MIN_VALUE));
        label1.setSize(tt.getIconWidth(),tt.getIconHeight());

        label = new JLabel("管理员你好，欢迎使用创新学分管理系统！！！");
        label.setForeground(Color.RED);
        label.setFont(new Font("华文琥珀",Font.BOLD,30));
        panel.add(label);

        button1 = new JButton("请点击");
        button1.setBounds(200,200,2002,200);
        button1.setBackground(Color.CYAN);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textArea = new JTextArea(20,20);
                textArea.setFont(new Font("华文琥珀",Font.BOLD,30));
                textArea.setBackground(Color.BLUE);
                textArea.append("请管理员注意："+"\n"+"每位教师初始账号为zzuli.teacher"+"\n"+"每位学生初始账号为zzuli.student");
                panel.add(textArea,BorderLayout.CENTER);
            }
        });
        panel.add(button1);

        menuBar = new JMenuBar();
        //设置一级菜单
        menu1 = new JMenu("添加信息");
        menu1.setFont(new Font("楷体",Font.BOLD,30));

        menuBar.add(menu1);
        menu2 = new JMenu("查看学生信息");
        menu2.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu2);
        menuItem3 = new JMenuItem("点击查看");
        menuItem3.setFont(new Font("楷体",Font.BOLD,30));
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 查看学生信息();
            }
        });
        menu2.add(menuItem3);

        menuItem1 = new JMenuItem("添加教师信息");
        menuItem1.setFont(new Font("楷体",Font.BOLD,30));
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s1 = menuItem1.getText();
                new 添加教师信息页面();
            }
        });
        menu1.add(menuItem1);

        menuItem2 = new JMenuItem("添加学生信息");
        menuItem2.setFont(new Font("楷体",Font.BOLD,30));
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s2 = menuItem2.getText();
                new 添加学生信息页面();
            }
        });
        menu1.add(menuItem2);

        menu4 = new JMenu("查看教师信息");
        menu4.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu4);
        menuItem5 = new JMenuItem("教师信息");
        menuItem5.setFont(new Font("楷体",Font.BOLD,30));
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //跳转页面
                new 查看教师信息();
            }
        });
        menu4.add(menuItem5);

        JMenu menu5 = new JMenu("分配导师");
        menu5.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu5);
        JMenuItem menuItem5 = new JMenuItem("依据学院分配");
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s3 = menuItem5.getText();
                new 分配导师();
            }
        });
        menuItem5.setFont(new Font("楷体",Font.BOLD,30));
        menu5.add(menuItem5);

        menu3 = new JMenu("退出页面");
        menu3.setFont(new Font("楷体",Font.BOLD,30));
        menuBar.add(menu3);
        menuItem4 = new JMenuItem("点击离开");
        menuItem4.setFont(new Font("楷体",Font.BOLD,30));
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu3.add(menuItem4);

        menu1.insertSeparator(1);
        frame.setJMenuBar(menuBar);

        frame.setLocation(500,500);
        frame.setSize(980,800);
        frame.setVisible(true);
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new 管理员操作页面();
    }
}
