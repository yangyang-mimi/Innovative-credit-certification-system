package 大二上学期实训.student;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class 查看学生信息页面 {
     JFrame frame;
     JScrollPane scpDemo;//滚动面板
     JPanel panel;
     JTableHeader TableHeader;//设置表头
     JTable label1;//JTable 是用来显示和编辑常规二维单元表。
    JButton button;
    public 查看学生信息页面() {
        frame = new JFrame("学生信息");
        frame.setLayout(null);
        scpDemo = new JScrollPane();
        scpDemo.setBackground(Color.CYAN);
        scpDemo.setBounds(10, 50, 900, 270);
        frame.add(scpDemo);

        button = new JButton("导成文件");
        button.setBounds(10,500,100,30);
        frame.add(button);

        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new 查看学生信息页面();
    }
}
