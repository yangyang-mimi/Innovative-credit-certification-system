package 大二上学期实训;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class 查看教师信息 {
    private JFrame frame;
    private JScrollPane scpDemo;//滚动面板
    private JTableHeader TableHeader;//设置表头
    private JTable label1;//JTable 是用来显示和编辑常规二维单元表。
    public 查看教师信息() {
        frame = new JFrame("教师信息");
        scpDemo = new JScrollPane();
        scpDemo.setBackground(Color.CYAN);
        scpDemo.setBounds(10, 50, 300, 270);
        frame.add(scpDemo);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
            //预编译sql语句
            String sql = "select * from teacher";
            //连接数据库的对象
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            rs = stmt.executeQuery();
            Object[][] info = new Object[count][4];
            count = 0;
            while (rs.next()) {
                info[count][0] = rs.getString("name");
                info[count][1] = rs.getString("sex");
                info[count][2] = rs.getString("id");
                info[count][3] = rs.getString("password");
                count++;
            }
            // 定义表头
            String[] title = {"姓名", "性别", "账号", "密码"};
            // 创建JTable
            label1 = new JTable(info, title);
            // 显示表头
            TableHeader = this.label1.getTableHeader();
            // 将JTable加入到带滚动条的面板中
            this.scpDemo.getViewport().add(label1);//得到scpDemo的视图
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

        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new 查看教师信息();
    }
}
