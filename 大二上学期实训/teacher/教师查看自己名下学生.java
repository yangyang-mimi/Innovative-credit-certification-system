package 大二上学期实训.teacher;

import shixun.*;
import 大二上学期实训.Dept;
import 大二上学期实训.name;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class 教师查看自己名下学生 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JComboBox ComboBox;
    String c;
    static BufferedReader br = null;
    static PrintWriter pw = null;
    Socket client = null;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",1100);
            System.out.println("查询方式连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public 教师查看自己名下学生() {
        frame = new JFrame("选择查看方式");
        panel = new JPanel();
        panel.setBackground(Color.CYAN);
        frame.add(panel);

        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\666.jpeg");
        //panelTop，顶层容器
        JPanel panelTop= new JPanel();
        panelTop=(JPanel)frame.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        panel.setOpaque(false);

        JLabel label=new JLabel(tt);
        frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        label.setSize(tt.getIconWidth(),tt.getIconHeight());


        label = new JLabel("请选择查看方式：");
        label.setFont(new Font("华文琥珀",Font.BOLD,30));
        panel.add(label);
        String[] shiti = {"姓名", "学院"};
        ComboBox = new JComboBox(shiti);
        ComboBox.setFont(new Font("楷体",Font.BOLD,30));
        panel.add(ComboBox);
        for (int i = 0; i < shiti.length; i++) {
            ComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    c = (String) ComboBox.getSelectedItem();
                }
            });
        }


        button1 = new JButton("确定");
        button1.setFont(new Font("楷体",Font.BOLD,25));
        panel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = button1.getText();
                pw.println(c);
                JOptionPane.showMessageDialog(null,"您选择依照"+c+"查看学生信息");
                if(c.equals("姓名")){
                    new name();
                }
                if(c.equals("学院")){
                    new Dept();
                }
                frame.dispose();
            }
        });

        button2 = new JButton("返回");
        button2.setFont(new Font("楷体",Font.BOLD,25));
        panel.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new day2$1();
            }
        });

        frame.setLocation(400, 200);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new 教师查看自己名下学生();
    }
}
