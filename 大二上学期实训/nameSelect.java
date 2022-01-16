package 大二上学期实训;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import 大二上学期实训.name;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class nameSelect implements Runnable{
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    static JLabel label2;
    static JLabel label3;
    static JLabel label4;
    static JLabel label5;
    static JLabel label6;
    static JLabel label7;
    static JLabel label8;
    static JLabel label9;
    static JLabel label10;
    static JLabel label11;
    static JLabel label12;
    static JLabel label13;
    static JLabel label14;
    private JButton button1;
    private JButton button2;
    private JTextField textField7;
    JTextField textField;
    private JRadioButton stateJRadioButton[] = new JRadioButton[3];
    String s5,insertstate,path,s6;
    static PrintWriter pw = null;
    static BufferedReader br = null;
    Socket client = null;
    String msg,str[],s1;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",1010);
            System.out.println("批阅连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public nameSelect(){
        Thread t = new Thread(this);
        t.start();
        frame = new JFrame();
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

        label1 = new JLabel("申请人姓名：");
        label1.setBounds(10,20,400,50);
        label1.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label1);
        label8 = new JLabel();
        label8.setBounds(300,20,400,60);
        label8.setFont(new Font("楷体",Font.BOLD,50));
        label8.setForeground(Color.BLUE);
        panel.add(label8);

        label2 = new JLabel("  导师姓名：");
        label2.setBounds(10,100,400,50);
        label2.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label2);
        label9 = new JLabel();
        label9.setBounds(300,100,400,60);
        label9.setFont(new Font("楷体",Font.BOLD,50));
        label9.setForeground(Color.BLUE);
        panel.add(label9);

        label3 = new JLabel("  认证类型：");
        label3.setBounds(10,200,400,50);
        label3.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label3);
        label10 = new JLabel();
        label10.setBounds(300,200,400,60);
        label10.setFont(new Font("楷体",Font.BOLD,50));
        label10.setForeground(Color.BLUE);
        panel.add(label10);

        label4 = new JLabel("  项目名称：");
        label4.setBounds(10,300,400,50);
        label4.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label4);
        label11 = new JLabel();
        label11.setBounds(300,300,400,60);
        label11.setFont(new Font("楷体",Font.BOLD,30));
        label11.setForeground(Color.BLUE);
        panel.add(label11);

        label5 = new JLabel("  申请学分：");
        label5.setFont(new Font("楷体",Font.BOLD,50));
        label5.setBounds(10,400,400,50);
        panel.add(label5);
        label12 = new JLabel();
        label12.setBounds(300,400,400,60);
        label12.setFont(new Font("楷体",Font.BOLD,50));
        label12.setForeground(Color.BLUE);
        panel.add(label12);

        label6 = new JLabel("  申请日期");
        label6.setBounds(10,500,400,50);
        label6.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label6);
        label13 = new JLabel();
        label13.setFont(new Font("楷体",Font.BOLD,50));
        label13.setBounds(300,500,400,60);
        label13.setForeground(Color.BLUE);
        panel.add(label13);

        label7 = new JLabel("是否给予通过：");
        label7.setFont(new Font("楷体",Font.BOLD,50));
        label7.setBounds(10,630,400,50);
        panel.add(label7);
        String[] login = {"通过","不合格"};
        ButtonGroup a = new ButtonGroup();
        for(int i = 0 ; i  < login.length;i++){
            stateJRadioButton[i] = new JRadioButton(login[i]);
            stateJRadioButton[i].setFont(new Font("楷体",Font.BOLD,50));
            if(i == 0){
                stateJRadioButton[i].setBounds(350,600,300,100);
            }
            if(i == 1){
                stateJRadioButton[i].setBounds(500,600,300,100);
            }
            a.add(stateJRadioButton[i]);
            panel.add(stateJRadioButton[i]);
        }
        for (int i = 0 ; i < login.length ; i++){
            int finalI = i;
            stateJRadioButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    insertstate = stateJRadioButton[finalI].getText();
                }
            });
        }

        label14 = new JLabel("请下载认证文件：");
        label14.setBounds(10,750,500,50);
        label14.setFont(new Font("楷体",Font.BOLD,50));
        panel.add(label14);
        button1 = new JButton("点击下载");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("下载文件");
                JPanel panel1 = new JPanel();
                JFileChooser fileChooser = new JFileChooser();
                panel1.setLayout(null);
                frame.add(panel1);

                textField = new JTextField();
                textField.setFont(new Font("楷体",Font.BOLD,15));
                textField.setBounds(100,10,200,40);
                panel1.add(textField);

                JButton button1 = new JButton("存入路径");
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == button1) {
                            int state = fileChooser.showSaveDialog(null);
                            if (state == 0) {
                                String pathChoose = fileChooser.getSelectedFile().getPath();
                                textField.setText(pathChoose);
                            }
                        }
                    }
                });
                button1.setFont(new Font("楷体",Font.BOLD,25));
                button1.setBounds(80,80,100,30);
                button1.setBackground(Color.RED);
                panel1.add(button1);

                JButton button2 = new JButton("下载");
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        s5 = button2.getText();
                        if(e.getSource() == button2){
                            if(textField.getText().trim()==null||"".equals(textField.getText().trim())){
                                JOptionPane.showMessageDialog(null,"请选择文件后再下载！");
                            }
                            // TODO Auto-generated method stub
                            BufferedInputStream bis = null;
                            path = textField.getText().trim();
                            String getAll = s5 +","+path+","+ Client.name;
                            pw.println(getAll);
                            try {
                                String str = br.readLine();
                                if(str.equals("下载成功")){
                                    JOptionPane.showMessageDialog(null,"下载成功！");
                                    frame.dispose();
                                }
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                });
                button2.setBounds(200,80,100,30);
                button2.setFont(new Font("楷体",Font.BOLD,25));
                button2.setBackground(Color.RED);
                panel1.add(button2);

                frame.setSize(500,300);
                frame.setLocation(800,500);
                frame.setVisible(true);
            }
        });

        button1.setBackground(Color.RED);
        button1.setBounds(400,750,300,60);
        button1.setBorderPainted(false);
        panel.add(button1);

        button2 = new JButton("提交");
        button2.setBackground(Color.RED);
        button2.setBounds(180,820,400,40);
        button2.setFont(new Font("楷体",Font.BOLD,30));
        button2.setBorderPainted(false);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s1 = button2.getText();
                pw.println(s1+","+insertstate+","+Client.name+","+ name.s2+","+label12.getText());
                try {
                    msg = br.readLine();
                    if(msg.equals("提交成功")){
                        JOptionPane.showMessageDialog(null,"提交成功！");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        panel.add(button2);

        frame.setVisible(true);
        frame.setLocation(500,400);
        frame.setSize(750,1050);
    }

    @Override
    public void run() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //获取连接
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                    "3306/lfy","root","123456");
            //执行sql语句
            String sql = "select * from 申请材料 where stuName = '"+name.s2+"'";
            //获取数据库操作对象
            ps = (PreparedStatement) conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            rs = ps.executeQuery();
            String[] str1 = new String[6];
            count = 0;
            while (rs.next()) {
                str1[0] = rs.getString("stuName");
                str1[1] = rs.getString("teaName");
                str1[2] = rs.getString("type");
                str1[3] = rs.getString("projectName");
                str1[4] = rs.getString("fenshu");
                str1[5] = rs.getString("date");
            }
            label8.setText(str1[0]);
            label9.setText(str1[1]);
            label10.setText(str1[2]);
            label11.setText(str1[3]);
            label12.setText(str1[4]);
            label13.setText(str1[5]);
            System.out.println("查看成功");
        } catch (SQLException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
