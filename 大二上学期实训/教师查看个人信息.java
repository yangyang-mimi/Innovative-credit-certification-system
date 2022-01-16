package 大二上学期实训;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class 教师查看个人信息 implements Runnable{
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    String name;
    JButton button1;

    public 教师查看个人信息(String name){
        this.name = name;
        Thread t = new Thread(this);
        t.start();
        frame = new JFrame("查看个人信息");
        panel = new JPanel();
        panel.setLayout(null);
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


        label1 = new JLabel("    姓名：");
        label1.setBounds(10,20,400,50);
        label1.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label1);
        textField1 = new JTextField();
        textField1.setBounds(300,20,400,60);
        textField1.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(textField1);

        label2 = new JLabel("     性别：");
        label2.setBounds(10,100,400,50);
        label2.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label2);
        textField2 = new JTextField();
        textField2.setBounds(300,100,400,60);
        textField2.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(textField2);

        label3 = new JLabel("    学院：");
        label3.setBounds(10,200,500,40);
        label3.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label3);
        textField3 = new JTextField();
        textField3.setBounds(300,200,400,60);
        textField3.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(textField3);

        label4 = new JLabel("   毕业院校：");
        label4.setBounds(10,300,500,40);
        label4.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label4);
        textField4 = new JTextField();
        textField4.setBounds(300,300,400,60);
        textField4.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(textField4);


        button1 = new JButton("返回上一页面");
        button1.setBounds(170,700,500,30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 教师操作页面();
                frame.dispose();
            }
        });
        button1.setFont(new Font("楷体",Font.BOLD,30));
        button1.setBackground(Color.RED);
        panel.add(button1);

        frame.setVisible(true);
        frame.setLocation(700,400);
        frame.setSize(800,1000);
    }

    @Override
    public void run() {
        Connection conn = null;
        PreparedStatement stmt =  null;
        ResultSet rs = null;

        while (true) {
            //注册驱动
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                //获取连接
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
                //预编译sql语句
                String sql = "select * from teacher where name ='"+name+"' ";
                //连接数据库的对象
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count++;
                }
                rs = stmt.executeQuery();
                String[] str = new String[4];
                count = 0;
                while (rs.next()) {
                    str[0] = rs.getString("name");
                    str[1] = rs.getString("sex");
                    str[2] = rs.getString("dept");
                    str[3] = rs.getString("graSchool");
                }
                textField1.setText(str[0]);
                textField2.setText(str[1]);
                textField3.setText(str[2]);
                textField4.setText(str[3]);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("已查询到"+ Client.name+"的信息！");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
