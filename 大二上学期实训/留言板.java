package 大二上学期实训;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.Date;

public class 留言板 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private JTextArea jTextArea = null;
    private JScrollPane jScrollPane = null;
    private JLabel jLabel1 = null;
    private JComboBox jComboBox = null;
    private JComboBox jComboBox1 = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel3 = null;
    private JTextField jTextField = null;
    private JButton jButton = null;
    private JButton jButton1 = null;
    private JButton jButton2 = null;
    private JButton jButton3 = null;
    private JButton jButton4 = null;
    private JButton jButton5 = null;
    static Connection conn = null;
    static Statement stmt = null;
    static PrintWriter pw = null;
    static BufferedReader br = null;
    static String s1,s2,s3,s4;
    String[] str3;
    Socket client = null;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",1010);
            System.out.println("连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This is the default constructor
     */
    public 留言板() {
        super();
        initialize();
    }


    private void initialize() {
        this.setContentPane(getJContentPane());
        this.setTitle("留言板程序");
        this.setBounds(new Rectangle(0, 0, 700, 680));
        this.setVisible(true);
        this.setLocation(700,500);
    }


    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel2 = new JLabel();
            jLabel2.setBounds(new Rectangle(210, 407, 90, 24));
            jLabel2.setFont(new Font("Dialog", Font.BOLD, 14));
            jLabel2.setText("输入留言：");
            jLabel1 = new JLabel();
            jLabel1.setBounds(new Rectangle(40, 407, 90, 24));
            jLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
            jLabel1.setText("选择角色：");
            jLabel3 =  new JLabel();
            jLabel3.setBounds(new Rectangle(40,450,90,24));
            jLabel3.setFont(new Font("Dialog", Font.BOLD, 14));
            jLabel3.setText("回复对象");
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(283, 15, 45, 20));
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setFont(new Font("Dialog", Font.BOLD, 14));
            jLabel.setText("留言板");
            jContentPane = new JPanel();

            ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\背景1.jpeg");
            //panelTop，顶层容器
            JPanel panelTop= new JPanel();
            panelTop=(JPanel)this.getContentPane();
            //panel和panelTop设置透明
            panelTop.setOpaque(false);
            jContentPane.setOpaque(false);

            JLabel label2=new JLabel(tt);
            this.getLayeredPane().add(label2,new Integer(Integer.MIN_VALUE));
            label2.setSize(tt.getIconWidth(),tt.getIconHeight());

            jContentPane.setLayout(null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJScrollPane(), null);
            jContentPane.add(jLabel1, null);
            jContentPane.add(getJComboBox(), null);
            jContentPane.add(getJComboBox1(), null);
            jContentPane.add(jLabel2, null);
            jContentPane.add(jLabel3, null);
            jContentPane.add(getJTextField(), null);
            jContentPane.add(getJButton(), null);
            jContentPane.add(getJButton1(), null);
            jContentPane.add(getJButton2(), null);
            jContentPane.add(getJButton3(), null);
            jContentPane.add(getJButton4(), null);
            jContentPane.add(getJButton5(), null);
        }
        return jContentPane;
    }


    private JTextArea getJTextArea() {
        if (jTextArea == null) {
            jTextArea = new JTextArea();
            jTextArea.setText("留言内容：");
            jTextArea.setEditable(false);
        }
        return jTextArea;
    }


    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setBounds(new Rectangle(22, 49, 534, 347));
            jScrollPane.setViewportView(getJTextArea());
        }
        return jScrollPane;
    }


    private JComboBox getJComboBox() {
        if (jComboBox == null) {
            jComboBox = new JComboBox();
            jComboBox.setBounds(new Rectangle(110, 407, 100, 24));
            String[] mycbox={"管理员","教师","学生"};
            jComboBox.addItem(mycbox[0]);
            jComboBox.addItem(mycbox[1]);
            jComboBox.addItem(mycbox[2]);
        }
        return jComboBox;
    }

    private JComboBox getJComboBox1(){
        com.mysql.jdbc.Connection conn = null;
        com.mysql.jdbc.PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //获取连接
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy","root","123456");
            //执行sql语句
            String sql = "select * from student where teacher = ?";
            //获取数据库操作对象
            ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1,"孙海燕");
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            rs = ps.executeQuery();
            String[] str1 = new String[1];
            str1[0] = new String("");
            count = 0;
            while (rs.next()){
                str1[0] += rs.getString("name")+",";
            }
            str3 = str1[0].split(",");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String[] name = {str3[0],str3[1]};
        jComboBox1 = new JComboBox(name);
        //jComboBox1.setFont(new Font("楷体",Font.BOLD,40));
        jComboBox1.setBounds(new Rectangle(110, 450, 100, 24));
        for (int i = 0; i < name.length; i++) {
            jComboBox1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    s3 = (String) jComboBox1.getSelectedItem();
                }
            });
        }
        return jComboBox1;
    }

    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setBounds(new Rectangle(280, 407, 231, 200));
        }
        return jTextField;
    }


    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setBounds(new Rectangle(505, 407, 70, 24));
            jButton.setText("发帖");
            jButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String s1 = jButton.getText();
                    Date date = new Date();
                    //注册驱动
                    try {
                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                        //获取连接
                        conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                "3306/lfy","root","123456");

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    jTextArea.setText(jTextArea.getText()+"\r\n"+date.toString()+ "  " + Client.name+jComboBox.getSelectedItem().toString()+"   留言："+jTextField.getText());
                    pw.println(s1+","+ Client.name+","+date.toString()+","+jTextField.getText());
                }
            });
        }
        return jButton;
    }

    private JButton getJButton5(){
        if(jButton5 == null){
            jButton5 = new JButton();
            jButton5.setBounds(new Rectangle(505,450,70,24));
            jButton5.setText("回复");
            jButton5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    s4 = jButton5.getText();
                    Date date = new Date();
                    if(s3.equals(str3[0])){
                        jTextArea.setText(jTextArea.getText()+"\r\n"+date.toString()+ "  "+ Client.name+jComboBox.getSelectedItem().toString()+"回复"+s3+"："+jTextField.getText()+"\n");
                        pw.println(s4+","+ Client.name+","+date.toString()+","+s3+","+jTextField.getText());
                    }else if(s3.equals(str3[1])){
                        jTextArea.setText(jTextArea.getText()+"\r\n"+date.toString()+ "  "+ Client.name+jComboBox.getSelectedItem().toString()+"回复"+s3+"："+jTextField.getText()+"\n");
                        pw.println(s4+","+ Client.name+","+date.toString()+","+s3+","+jTextField.getText());
                    }else if(s3.equals(str3[2])){
                        jTextArea.setText(jTextArea.getText()+"\r\n"+date.toString()+ "  "+ Client.name+jComboBox.getSelectedItem().toString()+"回复"+s3+"："+jTextField.getText()+"\n");
                        pw.println(s4+","+ Client.name+","+date.toString()+","+s3+","+jTextField.getText());
                    }
                }
            });
        }
        return jButton5;
    }

    private JButton getJButton1() {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setBounds(new Rectangle(565, 51, 60, 32));
            jButton1.setText("删帖");
            jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    s2 =  jButton1.getText();
                    pw.println(Client.name+","+s2);
                       jTextArea.setText("留言内容：");
                       if(jComboBox.getSelectedItem().toString() == "管理员" || jComboBox.getSelectedItem().toString() =="博主" ){

                       }
                }
            });
        }
        return jButton1;
    }


    private JButton getJButton2() {
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setBounds(new Rectangle(560, 112, 100, 32));
            jButton2.setText("从头浏览");
            jButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jTextArea.setCaretPosition(0);
                }
            });
        }
        return jButton2;
    }

    /**
     * This method initializes jButton3
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton3() {
        if (jButton3 == null) {
            jButton3 = new JButton();
            jButton3.setBounds(new Rectangle(560, 173, 100, 32));
            jButton3.setText("从尾浏览");
            jButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jTextArea.setCaretPosition((int)jTextArea.getText().length());
                }
            });
        }
        return jButton3;
    }

    private JButton getJButton4(){
        if(jButton4 == null){
            jButton4 =  new JButton();
            jButton4.setBounds(560,234,100,32);
            jButton4.setText("查看留言");
            jButton4.addActionListener(new ActionListener() {
                com.mysql.jdbc.Connection conn = null;
                com.mysql.jdbc.PreparedStatement ps = null;
                ResultSet rs = null;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Client.insertsex.equals("教师登录")) {
                        s1 = jButton4.getText();
                        pw.println(s1 + "," + Client.name);
                        //注册驱动
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
                            //执行sql语句
                            String sql = "select * from student where teacher = ?";
                            //获取数据库操作对象
                            ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                            ps.setString(1, Client.name.trim());
                            rs = ps.executeQuery();
                            int count = 0;
                            while (rs.next()) {
                                count++;
                            }
                            rs = ps.executeQuery();
                            String[] str1 = new String[1];
                            str1[0] = new String("");
                            count = 0;
                            while (rs.next()) {
                                str1[0] += rs.getString("name") + ",";
                            }
                            String[] str3 = str1[0].split(",");
                            for (int i = 0; i < str3.length; i++) {
                                String sql1 = "select * from 留言板 where name = ?";
                                //获取数据库操作对象
                                ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);
                                ps.setString(1, str3[i].trim());
                                rs = ps.executeQuery();
                                int count1 = 0;
                                while (rs.next()) {
                                    count1++;
                                }
                                rs = ps.executeQuery();
                                String[] str2 = new String[3];
                                count1 = 0;
                                while (rs.next()) {
                                    str2[0] = rs.getString("name");
                                    str2[1] = rs.getString("date");
                                    str2[2] = rs.getString("留言");
                                }
                                System.out.println("111");
                                //pw.println("留言" + "," + str2[0] + "," + str2[1] + "," + str2[2]);
                                System.out.println("留言" + "," + str2[0] + "," + str2[1] + "," + str2[2]);
                                System.out.println("留言查询成功！");
                                if (jComboBox.getSelectedItem().toString() == "教师") {
                                    jTextArea.append(str2[0] + "在" + str2[1] + "留言：" + str2[2] + "\n");
                                } else {
                                    JOptionPane.showMessageDialog(null, "请选择教师身份，才能查看留言", "失败", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if(Client.insertsex.equals("学生登录")){
                        s1 = jButton4.getText();
                        pw.println(s1 + "," + Client.name);
                        //注册驱动
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
                            //执行sql语句
                            String sql = "select * from 回复留言 where stuName = ?";
                            //获取数据库操作对象
                            ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                            ps.setString(1, Client.name.trim());
                            rs = ps.executeQuery();
                            int count = 0;
                            while (rs.next()) {
                                count++;
                            }
                            rs = ps.executeQuery();
                            String[] str1 = new String[4];
                           // str1[0] = new String("");
                            count = 0;
                            while (rs.next()) {
                                str1[0] = rs.getString("teaName") ;
                                str1[1] = rs.getString("date") ;
                                str1[2] = rs.getString("stuName") ;
                                str1[3] = rs.getString("留言") ;
                            }
                            if (jComboBox.getSelectedItem().toString() == "学生") {
                                jTextArea.append(str1[0] + "老师在" + str1[1] + "回复你的留言：" + str1[3] + "\n");
                            } else {
                                JOptionPane.showMessageDialog(null, "请选择学生身份，才能查看留言", "失败", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            });
        }
        return jButton4;
    }

    public static void main(String args[]){
        new 留言板();
    }

}
