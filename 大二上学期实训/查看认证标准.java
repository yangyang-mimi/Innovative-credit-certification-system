package 大二上学期实训;

import javax.swing.*;
import java.awt.*;

public class 查看认证标准 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JButton button1;
    private JButton button2;

    public 查看认证标准(){
        frame = new JFrame("认证标准");
        panel = new JPanel();
        frame.add(panel);
        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\认定范围.png");
        //panelTop，顶层容器
        JPanel panelTop= new JPanel();
        panelTop=(JPanel)frame.getContentPane();
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        panel.setOpaque(false);

        JLabel label1=new JLabel(tt);
        frame.getLayeredPane().add(label1,new Integer(Integer.MIN_VALUE));
        label1.setSize(tt.getIconWidth(),tt.getIconHeight());

        frame.setVisible(true);
        frame.setSize(840,1000);
        frame.setLocation(600,400);
    }

    public static void main(String[] args) {
        new 查看认证标准();
    }
}
