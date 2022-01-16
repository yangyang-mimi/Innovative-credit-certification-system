package 大二上学期实训;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.net.Socket;

public class 修改密码 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField textField1;
    private JPasswordField textField2;
    private JPasswordField textField3;
    private JCheckBox checkBox1;
    static PrintWriter pw = null;
    static BufferedReader br = null;
    Socket client = null;
    String s1,s2,s3,s4;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",9090);
            System.out.println("提示：修改页面连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public 修改密码(){
        frame = new JFrame("修改密码");
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

        label1 = new JLabel("请输入旧的密码：");
        label1.setFont(new Font("楷体",Font.BOLD,40));
        label1.setBounds(10,50,400,50);
        panel.add(label1);
        textField1 = new JPasswordField();
        textField1.setFont(new Font("楷体",Font.BOLD,40));
        textField1.setBounds(330,50,500,60);
        panel.add(textField1);

        label2 = new JLabel("请输入新的密码：");
        label2.setFont(new Font("楷体",Font.BOLD,40));
        label2.setBounds(10,150,400,50);
        panel.add(label2);
        textField2 = new JPasswordField();
        textField2.setFont(new Font("楷体",Font.BOLD,40));
        textField2.setBounds(330,150,500,60);
        panel.add(textField2);

        label3 = new JLabel("请再次输入新的密码：");
        label3.setFont(new Font("楷体",Font.BOLD,40));
        label3.setBounds(10,250,500,40);
        panel.add(label3);
        textField3 = new JPasswordField();
        textField3.setFont(new Font("楷体",Font.BOLD,40));
        textField3.setBounds(430,250,450,60);
        panel.add(textField3);

        checkBox1 = new JCheckBox("显示密码");
        checkBox1.setBounds(20,400,200,50);
        checkBox1.setFont(new Font("楷体",Font.BOLD,30));
        checkBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    textField1.setEchoChar((char)0);
                    textField2.setEchoChar((char)0);
                    textField3.setEchoChar((char)0);
                }else{
                    textField1.setEchoChar('*');
                    textField2.setEchoChar('*');
                    textField3.setEchoChar('*');
                }
            }
        });
        panel.add(checkBox1);

        JButton button1 = new JButton("确认修改");
        button1.setBackground(Color.RED);
        button1.setFont(new Font("楷体",Font.BOLD,30));
        button1.setBounds(280,400,300,50);
        button1.setBorderPainted(false);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 s1 = button1.getText();
                 s2 = textField1.getText();
                 s3 = textField2.getText();
                 s4 = textField3.getText();
                 String s5 = UpdatePassword.textField2.getText();
                System.out.println(s5);
                pw.println(s1+","+s2+","+s3+","+s4+","+s5);
                if(!s3.equals(s4)){
                    JOptionPane.showMessageDialog(null,"密码输入错误！","错误",JOptionPane.ERROR_MESSAGE);
                }
                try {
                    String msg = br.readLine();
                    if(msg.equals("修改成功")){
                        JOptionPane.showMessageDialog(null,"修改成功！您将退出重新登录！");
                        frame.dispose();
                        new Client();
                    }else {
                        JOptionPane.showMessageDialog(null,"修改失败","失败",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.add(button1);

        JButton button2 = new JButton("重新输入");
        button2.setBackground(Color.RED);
        button2.setFont(new Font("楷体",Font.BOLD,30));
        button2.setBounds(630,400,300,50);
        button2.setBorderPainted(false);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(null);
                textField2.setText(null);
                textField3.setText(null);
            }
        });
        panel.add(button2);

        frame.setVisible(true);
        frame.setSize(1000,1000);
        frame.setLocation(700,400);
    }

    public static void main(String[] args) {
        new 修改密码();
    }
}
