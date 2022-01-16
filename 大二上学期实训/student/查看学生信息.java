package 大二上学期实训.student;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class 查看学生信息 extends 查看学生信息页面 implements Runnable {


    public 查看学生信息() {
        super();
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
            //预编译sql语句
            String sql = "select * from student";
            //连接数据库的对象
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            rs = stmt.executeQuery();
            Object[][] info = new Object[count][10];
            count = 0;
            while (rs.next()) {
                info[count][0] = rs.getString("name");
                info[count][1] = rs.getString("identification");
                info[count][2] = rs.getString("schoolID");
                info[count][3] = rs.getString("sex");
                info[count][4] = rs.getString("id");
                info[count][5] = rs.getString("password");
                info[count][6] = rs.getString("teacher");
                info[count][7] = rs.getString("grade");
                info[count][8] = rs.getString("level");
                info[count][9] = rs.getString("dept");
                count++;
            }
            // 定义表头
            String[] title = {"姓名", "身份证号", "学号", "性别", "账号", "密码", "导师", "成绩","等级", "学院"};
            // 创建JTable
            label1 = new JTable(info, title);
            // 显示表头
            TableHeader = this.label1.getTableHeader();
            // 将JTable加入到带滚动条的面板中
            this.scpDemo.getViewport().add(label1);//得到scpDemo的视图
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TableModel model = label1.getModel();
                    BufferedWriter bWriter = null;
                    try {
                        bWriter = new BufferedWriter(new FileWriter("D:\\Lfy\\Pictures\\Camera Roll\\lfy.txt"));
                        for(int i=0; i < model.getColumnCount(); i++) {
                            bWriter.write(model.getColumnName(i));
                            bWriter.write("\t");
                        }
                        bWriter.newLine();
                        for(int i=0; i< model.getRowCount(); i++) {
                            for(int j=0; j < model.getColumnCount(); j++) {
                                bWriter.write(model.getValueAt(i,j).toString());
                                bWriter.write("\t");
                            }
                            bWriter.newLine();
                        }
                        bWriter.close();
                        JOptionPane.showMessageDialog(null,"文件所在位置为：D:\\Lfy\\Pictures\\Camera Roll\\lfy.txt");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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
    }

    public static void main(String[] args) {
        new 查看学生信息();
    }
}
