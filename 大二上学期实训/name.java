package 大二上学期实训;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class name {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button1;
    static PrintWriter pw = null;
    static BufferedReader br = null;
    Socket client = null;
    static String s1,s2;
//    //普通代码块总比他们先执行
//    {
//        try {
//            client = new Socket("127.0.0.1",1010);
//            System.out.println("姓名查询连接成功！");
//            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
//            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public name(){
        frame = new JFrame("姓名查询");
        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\333.jfif");
        //panelTop，顶层容器
        JPanel panelTop= new JPanel();
        panelTop=(JPanel)frame.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        panel.setOpaque(false);

        JLabel label1=new JLabel(tt);
        frame.getLayeredPane().add(label1,new Integer(Integer.MIN_VALUE));
        label1.setSize(tt.getIconWidth(),tt.getIconHeight());

        label = new JLabel("请输入姓名：");
        label.setFont(new Font("华文琥珀",Font.BOLD,50));
        label.setBounds(10,100,400,60);
        panel.add(label);
        textField = new JTextField(15);
        textField.setFont(new Font("楷体",Font.BOLD,30));
        textField.setBounds(300,100,400,60);

        button1 = new JButton("确定");
        button1.setBackground(Color.RED);
        button1.setFont(new Font("楷体",Font.BOLD,40));
        button1.setBounds(200,250,300,40);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s2 = textField.getText();
                s1 = button1.getText();
                //pw.println(s1+","+s2);
                new nameSelect();
            }
        });
        panel.add(button1);

        panel.add(textField);
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setLocation(500,600);
    }

    public static void main(String[] args) {
        new name();
    }
}
