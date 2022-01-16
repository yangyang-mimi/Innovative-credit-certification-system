package 大二上学期实训;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

//测试类
public class DateChooser extends JPanel{
   private JFrame frame;
   private JPanel panel;
   private JTextField textField1;
   private JTextField textField2;
   private JTextField textField3;
   private JTextField textField4;
   private JTextField textField5;
   private JLabel label;
   private JLabel label3;
   private JLabel label4;
   private JLabel label5;
   private JLabel l;
   private JLabel label6;
   private JButton button1;
   private JButton button2;
   private JButton button3;
   private JComboBox ComboBox;
   static String path,c;
    JTextField textField;
    String s1,s2,s3,s4,s5,s6,s7,getAll;
    static PrintWriter pw = null;
    static BufferedReader br = null;
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

    public DateChooser(){
        frame = new JFrame();
        frame.setLayout(null);
        panel = new JPanel();
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

        label1 = new JLabel("导师姓名：");
        label1.setBounds(10,20,400,50);
        label1.setFont(new Font("楷体",Font.BOLD,50));
        textField2 = new JTextField();
        textField2.setBounds(300,20,400,60);
        textField2.setFont(new Font("楷体",Font.BOLD,50));

        label5 = new JLabel("请选择认定类型：");
        label5.setFont(new Font("楷体",Font.BOLD,50));
        label5.setBounds(10,100,500,50);
        String[] shiti = {"类型如下","科学技术类", "学术论文类", "创业实践类","暑期调研类"};
        ComboBox = new JComboBox(shiti);
        ComboBox.setFont(new Font("楷体",Font.BOLD,40));
        ComboBox.setBounds(400,100,300,60);
        for (int i = 0; i < shiti.length; i++) {
            ComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    c = (String) ComboBox.getSelectedItem();
                }
            });
        }

        label6 = new JLabel("项目名称：");
        label6.setBounds(10,200,400,50);
        label6.setFont(new Font("楷体",Font.BOLD,50));
        textField3 = new JTextField();
        textField3.setBounds(300,200,400,60);
        textField3.setFont(new Font("楷体",Font.BOLD,50));

        label4 = new JLabel("请上传认证文件：");
        label4.setBounds(10,300,500,30);
        label4.setFont(new Font("楷体",Font.BOLD,36));
        button1 = new JButton("点击上传");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("上传文件");
                JPanel panel = new JPanel();
                JFileChooser fileChooser = new JFileChooser();
                panel.setLayout(null);
                frame.add(panel);

                textField = new JTextField();
                textField.setFont(new Font("楷体",Font.BOLD,15));
                textField.setBounds(100,10,200,40);
                panel.add(textField);

                JButton button1 = new JButton("选择");
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
                panel.add(button1);

                JButton button2 = new JButton("上传");
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        s5 = button2.getText();
                        if(e.getSource() == button2){
                            if(textField.getText().trim()==null||"".equals(textField.getText().trim())){
                                JOptionPane.showMessageDialog(null,"请选择文件后在上传！");
                            }
                            // TODO Auto-generated method stub
                            BufferedInputStream bis = null;
                            path = textField.getText().trim();
                            String getAll = s5 +","+path+","+ Client.name;
                            pw.println(getAll);
                            JOptionPane.showMessageDialog(null,"上传成功！");
                            frame.dispose();
                        }
                    }
                });
                button2.setBounds(200,80,100,30);
                button2.setFont(new Font("楷体",Font.BOLD,25));
                button2.setBackground(Color.RED);
                panel.add(button2);

                frame.setSize(500,300);
                frame.setLocation(800,500);
                frame.setVisible(true);
            }
        });

        button1.setBackground(Color.RED);
        button1.setBounds(300,300,400,60);
        button1.setBorderPainted(false);

        label3 = new JLabel("申请学分：");
        label3.setBounds(10,400,400,40);
        label3.setFont(new Font("楷体",Font.BOLD,50));
        textField4 = new JTextField();
        textField4.setBounds(300,400,400,60);
        textField4.setFont(new Font("楷体",Font.BOLD,50));

        label = new JLabel("请在此选择日期：");
        label.setBounds(10,500,500,30);
        label.setFont(new Font("楷体",Font.BOLD,36));
        textField1 = new JTextField();
        textField1.setBounds(300,500,400,60);

        button2 = new JButton("确定");
        button2.setBounds(150,700,200,40);
        button2.setBackground(Color.RED);
        button2.setFont(new Font("楷体",Font.BOLD,40));
        button2.setBorderPainted(false);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s5 = button2.getText();
                s1 = textField2.getText();
                s2 = textField3.getText();
                s3 = textField4.getText();
                s4 = textField1.getText();
                getAll = s5+","+ Client.name +","+s1 + ","+c+","+ s2+","+s3+","+s4;
                System.out.println(getAll);
                pw.println(getAll);
                JOptionPane.showMessageDialog(null,"成功");
            }
        });

        button3 = new JButton("返回");
        button3.setBounds(400,700,200,40);
        button3.setBackground(Color.RED);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new 学生操作页面();
            }
        });
        button3.setFont(new Font("楷体",Font.BOLD,40));
        button3.setBorderPainted(false);

        // 定义日历控件面板类
        CalendarPanel p = new CalendarPanel(textField1, "yyyy/MM/dd");
        p.initCalendarPanel();

        JLabel l = new JLabel("日历面板");
        l.setBounds(40,40,80,40);
        p.add(l);
        frame.add(p);

        frame.getContentPane().add(label1);
        frame.getContentPane().add(textField2);
        frame.getContentPane().add(label);
        frame.getContentPane().add(label3);
        frame.getContentPane().add(label5);
        frame.getContentPane().add(ComboBox);
        frame.getContentPane().add(label6);
        frame.getContentPane().add(textField3);
        frame.getContentPane().add(textField4);
        frame.getContentPane().add(label4);
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.getContentPane().add(button3);
        frame.getContentPane().add(textField1);
        frame.setLocation(800,500);
        frame.setSize(800,1000);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new DateChooser();
    }
}
