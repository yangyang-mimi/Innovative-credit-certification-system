package 大二上学期实训;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class UpdatePassword {
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField1;
    static JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    static PrintWriter pw = null;
    static BufferedReader br = null;
    Socket client = null;
    String s1,s2,s3,s4,getAll;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",1010);
            System.out.println("提示：修改密码界面连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UpdatePassword(){
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


        label1 = new JLabel("你好，想要修改密码，首先请进行实名认证！");
        label1.setFont(new Font("楷体",Font.BOLD,40));
        label1.setBounds(50,40,900,50);
        panel.add(label1);

        label2 = new JLabel("请输入身份证：");
        label2.setFont(new Font("楷体",Font.BOLD,40));
        label2.setBounds(10,140,400,50);
        panel.add(label2);
        textField1 = new JTextField();
        textField1.setBounds(300,140,400,50);
        textField1.setFont(new Font("楷体",Font.BOLD,40));
        panel.add(textField1);

        label3 = new JLabel("请输入姓名：");
        label3.setFont(new Font("楷体",Font.BOLD,40));
        label3.setBounds(10,240,400,50);
        panel.add(label3);
        textField2 = new JTextField();
        textField2.setFont(new Font("楷体",Font.BOLD,40));
        textField2.setBounds(300,240,400,50);
        panel.add(textField2);

        label4 = new JLabel("请输入学号：");
        label4.setBounds(10,340,400,50);
        label4.setFont(new Font("楷体",Font.BOLD,40));
        panel.add(label4);
        textField3 = new JTextField();
        textField3.setFont(new Font("楷体",Font.BOLD,40));
        textField3.setBounds(300,340,400,50);
        panel.add(textField3);

        JButton button1 = new JButton("提交信息");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s1 = textField1.getText();
                s2 = textField2.getText();
                s3 = textField3.getText();
                s4 = button1.getText();
                getAll = s4+","+s1+","+s2+","+s3;
                pw.println(getAll);
                try {
                    String msg = br.readLine();
                    if(msg.equals("提交成功")){
                        JOptionPane.showMessageDialog(null,"您将进入修改密码界面！");
                        new 修改密码();
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"数据出现错误","失败",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        button1.setFont(new Font("楷体",Font.BOLD,40));
        button1.setBackground(Color.RED);
        button1.setBounds(90,540,300,40);
        panel.add(button1);

        JButton button2 = new JButton("清空所填");
        button2.setBounds(500,540,300,40);
        button2.setFont(new Font("楷体",Font.BOLD,40));
        button2.setBackground(Color.RED);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(null);
                textField2.setText(null);
                textField3.setText(null);
            }
        });
        panel.add(button2);

        frame.setSize(900,1000);
        frame.setLocation(700,300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UpdatePassword();
    }
}
