package 大二上学期实训;

import com.mysql.jdbc.Connection;
import 大二上学期实训.student.查看学生信息页面;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;

public class 完善信息 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    String schoolID,ID,name;
    String name1;

    public 完善信息(String name1) {
        this.name1 = name1;
        frame = new JFrame("修改信息");
        panel = new JPanel();
        panel.setLayout(null);

        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\背景.jpeg");
        //panelTop，顶层容器
        JPanel panelTop= new JPanel();
        panelTop=(JPanel)frame.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        panel.setOpaque(false);

        JLabel label2=new JLabel(tt);
        frame.getLayeredPane().add(label2,new Integer(Integer.MIN_VALUE));
        label2.setSize(tt.getIconWidth(),tt.getIconHeight());

        label1 = new JLabel("学号：");
        label1.setFont(new Font("楷体",Font.BOLD,30));
        label1.setBounds(130,0,200,50);
        panel.add(label1);
        textField1 = new JTextField(20);
        textField1.setBounds(220,5,250,40);
        textField1.setFont(new Font("楷体",Font.BOLD,30));
        panel.add(textField1);


        label2 = new JLabel("身份证号：");
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
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 查看学生信息页面();
            }
        });
        button2.setBounds(300,300,80,50);
        panel.add(button2);

        frame.add(panel);
        frame.setSize(600,700);
        frame.setVisible(true);
        frame.setLocation(500,500);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               schoolID = textField1.getText();
               ID = textField2.getText();
               name = Client.name;
               int k = update(schoolID,ID,name);
               if(k == 0){
                   JOptionPane.showMessageDialog(null,"已完善！","成功",JOptionPane.INFORMATION_MESSAGE);
               }
            }
        });
    }
    public int update(String schoolID,String ID,String name){
        Connection conn = null;
        java.sql.PreparedStatement stmt = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //获取连接
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                    "3306/lfy","root","123456");
            //执行SQL语句
            String sql = "update student set  `schoolID` = ? ,`identification` = ?where `name` = ?";
            stmt = conn.prepareStatement(sql);
            //手动给参数赋值
            stmt.setString(1,schoolID);
            stmt.setString(2,ID);
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
        new 完善信息(Client.name);
    }
}
