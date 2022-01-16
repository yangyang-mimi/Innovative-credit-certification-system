package 大二上学期实训;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server extends Client {
    static int idPas = 0;
    static boolean find =true;
    public static void main(String[] args)  {
        Socket socket = null;
        try {
            ServerSocket ss = new ServerSocket(8899);
            System.out.println("等待客户端连接！");
            socket = ss.accept();
            System.out.println("连接成功！");
            //创建线程对象
            Thread a = new Thread(new Server.ThreadTest(socket));
            a.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class ThreadTest implements Runnable{

        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        public ThreadTest(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    String msg = br.readLine();
                    String[] str = msg.split(",");
                        try{
                            if(msg.isEmpty()){
                                System.out.println("传送数据出现错误！！！");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if(str[3].equals("学生登录")){
                            System.out.println(str[0]+"进行了操作是："+str[3]);
                            idPas = ThreadTest.idPassword(str[0],str[1],str[2]);
                            if(idPas == 1 ){
                                System.out.println("登录成功");
                                find = true;
                                String str1 = String.valueOf(idPas);
                                pw.println(str1);
                            }else if(idPas == 2 ){
                                System.out.println("登录失败");
                                find = false;
                                String str2 = String.valueOf(idPas);
                                pw.println(str2);
                            }
                        }
                        if(str[3].equals("教师登录")){
                            System.out.println(str[0]+"进行了操作是："+str[3]);
                            idPas = ThreadTest.idPassword2(str[0],str[1],str[2]);
                            if(idPas == 1 ){
                                System.out.println("登录成功");
                                find = true;
                                String str1 = String.valueOf(idPas);
                                pw.println(str1);
                            }else if(idPas == 2 ){
                                System.out.println("登录失败");
                                find = false;
                                String str2 = String.valueOf(idPas);
                                pw.println(str2);
                            }
                        }
                        if(str[3].equals("管理员登录")){
                            System.out.println(str[0]+"进行了操作是："+str[3]);
                            idPas = ThreadTest.idPassword1(str[0],str[1],str[2]);
                            if(idPas == 1 ){
                                System.out.println("登录成功");
                                find = true;
                                String str1 = String.valueOf(idPas);
                                pw.println(str1);
                            }else if(idPas == 2 ){
                                System.out.println("登录失败");
                                find = false;
                                String str2 = String.valueOf(idPas);
                                pw.println(str2);
                            }
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //学生登录
        public static int idPassword(String name, String id,String password){

            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                //注册驱动
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                //获取连接
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                        "3306/lfy","root","123456");
                //执行sql语句
                String sql = "select * from student where name = ? and id =? and password =?";
                //获取数据库操作对象
                stmt = (PreparedStatement) conn.prepareStatement(sql);
                stmt.setString(1,name.trim());
                stmt.setString(2,id.trim());
                stmt.setString(3,password.trim());
                rs = stmt.executeQuery();
                if (rs.next()){
                    return 1;
                }
            } catch (SQLException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }finally {
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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
            find=false;
            return 2;
        }

        //教师登录方法
        public static int idPassword2(String name, String id,String password){

            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                //注册驱动
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                //获取连接
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                        "3306/lfy","root","123456");
                //执行sql语句
                String sql = "select * from teacher where name = ? and id =? and password =?";
                //获取数据库操作对象
                stmt = (PreparedStatement) conn.prepareStatement(sql);
                stmt.setString(1,name.trim());
                stmt.setString(2,id.trim());
                stmt.setString(3,password.trim());
                rs = stmt.executeQuery();
                if (rs.next()){
                    return 1;
                }
            } catch (SQLException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }finally {
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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
            find=false;
            return 2;
        }

        //管理员登录方法
        public static int idPassword1(String name, String id,String password){

            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                //注册驱动
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                //获取连接
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                        "3306/lfy","root","123456");
                //执行sql语句
                String sql = "select * from 管理员 where name = ? and id =? and password =?";
                //获取数据库操作对象
                stmt = (PreparedStatement) conn.prepareStatement(sql);
                stmt.setString(1,name.trim());
                stmt.setString(2,id.trim());
                stmt.setString(3,password.trim());
                rs = stmt.executeQuery();
                if (rs.next()){
                    return 1;
                }
            } catch (SQLException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }finally {
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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
            find=false;
            return 2;
        }
    }
}
