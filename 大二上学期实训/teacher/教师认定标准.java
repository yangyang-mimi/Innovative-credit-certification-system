package 大二上学期实训.teacher;

import javax.swing.*;

public class 教师认定标准 {
    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JButton button1;
    private JButton button2;

    public 教师认定标准(){
        frame = new JFrame("认证标准");
        panel = new JPanel();
        frame.add(panel);
        ImageIcon tt = new ImageIcon("D:\\lfy\\Pictures\\Camera Roll\\011.png");
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
        frame.setSize(710,880);
        frame.setLocation(600,400);
    }

    public static void main(String[] args) {
        new 教师认定标准();
    }
}
