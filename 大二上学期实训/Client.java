package 大二上学期实训;

import 大二上学期实训.admin.管理员操作页面;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.net.Socket;

/*
客户端 只实现页面
 */
public class Client {
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JLabel Label1;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JPasswordField passwordTextField;
    private JCheckBox checkBox;
    private JFrame frame;
    private JPanel panel;
    private JPanel panel1;
    private JRadioButton sexJRadioButton[] = new JRadioButton[3];
    private JButton button1;
    private JButton button2;
    private JButton button3;
    static String insertsex , name,id , password;
    String getAll;
    static BufferedReader br = null;
    static PrintWriter pw = null;
    Socket client = null;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",8899);
            System.out.println("客户端连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client() {
        frame = new JFrame("选择登录界面");
        panel = new JPanel();
        panel1 = new JPanel();
        panel1.setBackground(Color.RED);
        frame.add(panel);
        frame.add(panel1,BorderLayout.NORTH);
        panel.setLayout(new FlowLayout());

        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\天空.jpg");
        //panelTop，顶层容器
        JPanel panelTop= new JPanel();
        panelTop=(JPanel)frame.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        panel.setOpaque(false);

        JLabel label=new JLabel(tt);
        frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        label.setSize(tt.getIconWidth(),tt.getIconHeight());

        Label1 = new JLabel("<HTML>欢迎来到创新学分认证系统!<br>请在下方完成登录！！！");
        Label1.setFont(new Font("华文琥珀",Font.BOLD,20));
        Label1.setForeground(Color.GREEN);
        panel1.add(Label1);

        nameLabel = new JLabel("姓名：");
        panel.add(nameLabel);
        nameTextField = new JTextField(30);
        panel.add(nameTextField);

        JLabel label5 = new JLabel("                                       " +
                "                                                             "+"                                  "+
                "          ");
        panel.add(label5);

        idLabel = new JLabel("   账号：");
        panel.add(idLabel);
        idTextField = new JTextField(30);
        panel.add(idTextField,BorderLayout.NORTH);

        JLabel label3 = new JLabel("                                       " +
                "                                                             "+"                                  "+
                "          ");
        panel.add(label3);

        passwordLabel = new JLabel("密码：");
        panel.add(passwordLabel);
        passwordTextField = new JPasswordField(22);
        panel.add(passwordTextField);

        checkBox = new JCheckBox("显示密码");
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    passwordTextField.setEchoChar((char)0);
                }else{
                    passwordTextField.setEchoChar('*');
                }
            }
        });
        panel.add(checkBox);

        JLabel label4 = new JLabel("                                       " +
                "                                                             "+"                                  "+
                "          ");
        panel.add(label4);

        String[] login = {"学生登录","教师登录","管理员登录"};
        ButtonGroup a = new ButtonGroup();
        for(int i = 0 ; i  < login.length;i++){
            sexJRadioButton[i] = new JRadioButton(login[i]);
            a.add(sexJRadioButton[i]);
            panel.add(sexJRadioButton[i]);
        }
        for (int i = 0 ; i < login.length ; i++){
            int finalI = i;
            sexJRadioButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    insertsex = sexJRadioButton[finalI].getText();
                }
            });
        }

        JLabel label2 = new JLabel("                                       " +
                "                                                             "+"                                  "+
                "          ");
        panel.add(label2);

        button1 = new JButton("确定");
        button1.setBackground(Color.RED);
        button1.setBounds(600, 300, 450, 450);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();
                id =  idTextField.getText();
                password = new String(passwordTextField.getPassword());
                getAll = name+","+id + ","+password+","+insertsex;
                pw.println(getAll);
                try {
                    String msg = br.readLine();
                    if(msg.equals("1")){
                        JOptionPane.showMessageDialog(null,"登录成功");
                        if(insertsex.equals("学生登录")){
                            new 学生操作页面();
                            // frame.dispose();
                        }else if(insertsex.equals("教师登录")){
                            new 教师操作页面();
                            //frame.dispose();
                        }else {
                            new 管理员操作页面();
                            frame.dispose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"账号或密码错误，请重新登陆！！！");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        panel.add(button1);

        button2 =  new JButton("离开");
        button2.setBackground(Color.RED);
        button1.setBounds(200,200,200,200);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(button2);

       button3 = new JButton("系统指南");
       button3.setBackground(Color.RED);
       button3.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               String str;
               JFrame frame;
               JPanel panel;
               JLabel label1;
               JLabel label2;
               JLabel label3;
               frame = new JFrame("初次登录指南");
               panel = new JPanel();
               frame.add(panel);
               if(insertsex.equals("学生登录")){
                   label1 = new JLabel(nameTextField.getText() + "同学您好！欢迎来到创新学分查询系统.初次登录,您的账号为zzuli.student您的密码为123+自己的姓名首字拼音.后续您可自行更改密码。");
                   label1.setFont(new Font("楷体",Font.BOLD,35));
                   panel.add(label1);
               }else if(insertsex.equals("教师登录")){
                   label2 = new JLabel(nameTextField.getText() + "老师您好！欢迎来到创新学分查询系统.初次登录,您的账号为zzuli.student您的密码为123+自己的姓名首字拼音.后续您可自行更改密码。");
                   label2.setFont(new Font("楷体",Font.BOLD,35));
                   panel.add(label2);
               }else if(insertsex.equals("管理员登录")){
                   label3 = new JLabel(nameTextField.getText() + "管理员您好！欢迎来到创新学分查询系统.初次登录,您的账号为zzuli.student您的密码为123+自己的姓名首字拼音.后续您可自行更改密码。");
                   label3.setFont(new Font("楷体",Font.BOLD,35));
                   panel.add(label3);

               }
               frame.setVisible(true);
               frame.setLocation(800,400);
               frame.setSize(500,600);
           }
       });
       panel.add(button3);

       JLabel label6 = new JLabel("<html>    小提示：<br>初次登录，您需要选择登录方式并输入自己姓名<br>即可查阅系统指南！");
       label6.setFont(new Font("楷体",Font.BOLD,20));
       label6.setForeground(Color.CYAN);
       panel.add(label6);

        frame.setVisible(true);
        frame.setSize(470,500);
        frame.setLocation(400,400);
        //frame.setResizable(false);
    }

    public static void main(String[] args) {
        new Client();

    }
}
