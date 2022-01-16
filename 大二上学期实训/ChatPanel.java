package 大二上学期实训;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel {
    TextArea Text;
    JTextField Input1;
    JPanel p1;
    JPanel p2;
    JButton button1;
    JButton button2;
    JFrame jf;

    public ChatPanel(){
        jf = new JFrame("UPD基础界面");
        p1 = new JPanel();
        p1.setBackground(Color.CYAN);

        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\学生操作页面1.jpeg");
        //panelTop，顶层容器
        JPanel panelTop= new JPanel();
        panelTop=(JPanel)jf.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        p1.setOpaque(false);

        Text = new TextArea();
        Input1 = new JTextField(20);
        button1 = new JButton("发送");

        jf.setLayout(new BorderLayout());
        jf.add(Text, BorderLayout.CENTER);
        jf.add(p1, BorderLayout.SOUTH);
        p1.add(Input1);
        p1.add(button1);

        jf.setSize(900, 700);
        jf.setLocation(600, 300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }
}
