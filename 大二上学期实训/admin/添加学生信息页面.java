package 大二上学期实训.admin;

import 大二上学期实训.student.查看学生信息页面;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class 添加学生信息页面 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label5;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField6;
    private JComboBox ComboBox;
    static String name,sex,id,password,dept;
    static BufferedReader br = null;
    static PrintWriter pw = null;
    Socket client = null;
    String c;
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


    public 添加学生信息页面() {

        frame = new JFrame("学生信息添加页面");
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

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


        label1 = new JLabel("学生姓名：");
        label1.setFont(new Font("楷体",Font.BOLD,40));
        label1.setBounds(10,20,400,50);
        panel.add(label1);
        textField1 = new JTextField();
        textField1.setBounds(300,20,400,50);
        textField1.setFont(new Font("楷体",Font.BOLD,30));
        panel.add(textField1);

        JLabel label7 = new JLabel("  性别：");
        label7.setFont(new Font("楷体",Font.BOLD,40));
        label7.setBounds(10,100,400,50);
        panel.add(label7);
        JTextField textField4 = new JTextField(20);
        textField4.setFont(new Font("楷体",Font.BOLD,30));
        textField4.setBounds(300,100,400,50);
        panel.add(textField4);

        label3 = new JLabel("  账号：");
        label3.setFont(new Font("楷体",Font.BOLD,40));
        label3.setBounds(10,180,400,50);
        panel.add(label3);
        textField3 = new JTextField(15);
        textField3.setFont(new Font("楷体",Font.BOLD,30));
        textField3.setBounds(300,180,400,50);
        panel.add(textField3);

        label2 = new JLabel("  密码：");
        label2.setBounds(10,260,400,50);
        label2.setFont(new Font("楷体",Font.BOLD,40));
        panel.add(label2);
        textField2 = new JTextField(15);
        textField2.setFont(new Font("楷体",Font.BOLD,30));
        textField2.setBounds(300,260,400,50);
        panel.add(textField2);

        label5 = new JLabel("  学院：");
        label5.setBounds(10,340,400,50);
        label5.setFont(new Font("楷体",Font.BOLD,40));
        panel.add(label5);
        String[] shiti = {"软件学院","国际教育学院", "计算机与通信技术学院", "经管院","艺术设计学院"};
        ComboBox = new JComboBox(shiti);
        ComboBox.setBounds(300,340,300,50);
        ComboBox.setFont(new Font("楷体",Font.BOLD,30));
        for (int i = 0; i < shiti.length; i++) {
            ComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    c = (String) ComboBox.getSelectedItem();
                }
            });
        }
        panel.add(ComboBox);

        button1 = new JButton("确定");
        button1.setFont(new Font("楷体",Font.BOLD,30));
        button1.setBounds(100,500,200,30);
        button1.setBackground(Color.CYAN);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = textField1.getText();
                sex = textField4.getText();
                id = textField3.getText();
                password = textField2.getText();
                String getAll = 管理员操作页面.s2+","+name+","+sex+","+id+","+password+","+c;
                pw.println(getAll);
                if(textField1.getText().isEmpty()&&textField2.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"姓名不能为空！！！");
                    JOptionPane.showMessageDialog(null,"密码不能为空！！！");
                    JOptionPane.showMessageDialog(null,"数据操作失败！","失败",JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "注册成功!");
                }
            }
        });
        panel.add(button1);

        button2 = new JButton("清空");
        button2.setFont(new Font("楷体",Font.BOLD,30));
        button2.setBounds(400,500,200,30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(null);
                textField2.setText(null);
                textField3.setText(null);
                textField5.setText(null);
                textField6.setText(null);
            }
        });
        button2.setBackground(Color.CYAN);
        panel.add(button2);

        button3 = new JButton("查看");
        button3.setFont(new Font("楷体",Font.BOLD,30));
        button3.setBounds(700,500,200,30);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //跳转页面
                new 查看学生信息页面();
            }
        });
        button3.setBackground(Color.CYAN);
        panel.add(button3);

        frame.setSize(1000,700);
        frame.setVisible(true);
        frame.setLocation(700,400);
    }

    public static void main(String[] args) {
        new 添加学生信息页面();
    }
}
