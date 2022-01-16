package 大二上学期实训;

import com.mysql.jdbc.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;

public class 教师完善信息 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    String dept,graSchool,name;
    static BufferedReader br = null;
    static PrintWriter pw = null;
    Socket client = null;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",1100);
            System.out.println("连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public 教师完善信息() {
        frame = new JFrame("修改信息");
        panel = new JPanel();
        panel.setLayout(null);

        label1 = new JLabel("学院：");
        label1.setFont(new Font("楷体",Font.BOLD,30));
        label1.setBounds(130,0,200,50);
        panel.add(label1);
        textField1 = new JTextField(20);
        textField1.setBounds(220,5,250,40);
        textField1.setFont(new Font("楷体",Font.BOLD,30));
        panel.add(textField1);


        label2 = new JLabel("毕业院校：");
        label2.setFont(new Font("楷体",Font.BOLD,30));
        label2.setBounds(80,50,200,100);
        panel.add(label2);
        textField2 = new JTextField(15);
        textField2.setBounds(220,80,250,40);
        textField2.setFont(new Font("楷体",Font.BOLD,30));
        panel.add(textField2);

        button1 = new JButton("确定");
        button1.setBorderPainted(false);
        button1.setBounds(200,300,80,50);
        panel.add(button1);

        button2 = new JButton("查看");
        button2.setBorderPainted(false);
        button2.setBounds(300,300,80,50);
        panel.add(button2);

        frame.add(panel);
        frame.setSize(600,700);
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = button1.getText();
                dept = textField1.getText();
                graSchool = textField2.getText();
                name = Client.name;
                int k = update(dept,graSchool,name);
                pw.println(s1+","+dept+","+graSchool+","+name);
                if(k == 0){
                    JOptionPane.showMessageDialog(null,"已完善！","成功",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    public int update(String dept,String graSchool,String name){
        Connection conn = null;
        java.sql.PreparedStatement stmt = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //获取连接
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                    "3306/lfy","root","123456");
            //执行SQL语句
            String sql = "update teacher set  `dept` = ? ,`graSchool` = ?where `name` = ?";
            stmt = conn.prepareStatement(sql);
            //手动给参数赋值
            stmt.setString(1,dept);
            stmt.setString(2,graSchool);
            stmt.setString(3,name);
            int i = stmt.executeUpdate();
            System.out.println(i);
            if(i>0){
                //插入成功
                return 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        if(textField1.getText().isEmpty()&&textField2.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"学号不能为空！！！");
            JOptionPane.showMessageDialog(null,"身份证不能为空！！！");
            JOptionPane.showMessageDialog(null,"数据操作失败！","失败",JOptionPane.ERROR_MESSAGE);
        }
        return 1;
    }

    public static void main(String[] args) {
        new 教师完善信息();
    }
}
