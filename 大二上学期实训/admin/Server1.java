package 大二上学期实训.admin;
/*
管理员服务器
 */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server1 {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            ServerSocket ss = new ServerSocket(9090);
            System.out.println("等待连接！");
            socket = ss.accept();
            System.out.println("连接成功！");
            //创建线程对象
            Thread t = new Thread(new Server1.MyThread(socket));
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class MyThread implements Runnable{
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        static int k = 0;
        public MyThread(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (true){
                    String msg = br.readLine();
                    String[] str = msg.split(",");
                    System.out.println(msg);

                    if(str[0].equals("添加教师信息")){
                        System.out.println("管理员进行了"+str[0]+"操作");
                        Connection conn = null;
                        Statement stmt = null;
                        try {
                            //注册驱动
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                    "3306/lfy","root","123456");
                            //获取数据库操作对象
                            stmt = conn.createStatement();
                            //执行SQL语句
                            String sql = "insert into teacher(name,sex,id,password)"+"values("+"'"+str[1]+"','"+str[2]+"', '"+str[3]+"','"+str[4]+"')";
                            //若成功更新，则返回值1
                            int row = stmt.executeUpdate(sql);
                            pw.println(row);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }finally {
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
                    if(str[0].equals("添加学生信息")){
                        System.out.println("管理员进行了"+str[0]+"的操作");
                        Connection conn = null;
                        Statement stmt = null;
                        try {
                            //注册驱动
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                    "3306/lfy","root","123456");
                            //获取数据库操作对象
                            stmt = conn.createStatement();
                            //执行SQL语句
                            String sql = "insert into student(name,sex,id,password,dept)"+"values("+"'"+str[1]+"','"+str[2]+"', '"+str[3]+"','"+str[4]+"','"+str[5]+"')";
                            //若成功更新，则返回值1
                            int row = stmt.executeUpdate(sql);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }finally {
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
                    if(str[0].equals("分配导师")){
                        System.out.println("管理员进行了"+str[0]+"的操作");
                        k = add(str[1],str[2]);
                        if(k == 0){
                            System.out.println("分配成功");
                        }else {
                            //操作失败
                            k =1;
                        }
                    }
                    if(str[0].equals("确认修改")){
                        Connection conn = null;
                        PreparedStatement stmt = null;
                        ResultSet rs = null;
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                    "3306/lfy","root","123456");
                            //执行sql语句
                            String sql = "select * from student where name= ? ";
                            //获取数据库操作对象
                            stmt = (PreparedStatement) conn.prepareStatement(sql);
                            stmt.setString(1,str[4]);
                            rs = stmt.executeQuery();
                            int count1 = 0;
                            while (rs.next()) {
                                count1++;
                            }
                            rs = stmt.executeQuery();
                            String[] str3 = new String[1];
                            count1 = 0;
                            while (rs.next()){
                                str3[0] = rs.getString("password");
                            }
                            System.out.println(str3[0]);
                            if(str3[0].equals(str[1])){
                                String sql1 = "update student set  `password` = ? where name = '"+str[4]+"'";
                                stmt = (PreparedStatement) conn.prepareStatement(sql1);
                                //手动给参数赋值
                                stmt.setString(1,str[2]);
                                int i = stmt.executeUpdate();
                                System.out.println("密码已修改成功!");
                                pw.println("修改成功");
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public int add(String teacher,String dept){
            Connection conn = null;
            java.sql.PreparedStatement stmt = null;
            try {
                //注册驱动
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                //获取连接
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy","root","123456");
                //预编译sql语句
                String sql = "update student set  `teacher` = ? where `dept` = ?";
                stmt = conn.prepareStatement(sql);
                //手动给参数赋值
                stmt.setString(1,teacher);
                stmt.setString(2,dept);
                int i = stmt.executeUpdate();
                System.out.println(i);
                if(i>0){
                    //插入成功
                    return 0;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                if(stmt!=null){
                    try {
                        stmt.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if(conn!=null){
                    try {
                        conn.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            return 1;
        }
    }
}
