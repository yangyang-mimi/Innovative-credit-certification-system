package 大二上学期实训;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class StuServer {
    static int idPas = 0;
    static boolean find =true;
    static byte[] fileByte;
    public static void main(String[] args)  {
        Socket socket = null;
        try {
            ServerSocket ss = new ServerSocket(1010);
            System.out.println("等待客户端连接！");
            socket = ss.accept();
            System.out.println("连接成功！");
            //创建线程对象
            Thread a = new Thread(new StuServer.MyThread01(socket));
            a.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class MyThread01 implements Runnable{
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        Connection conn = null;
        Statement stmt = null;
        public MyThread01(Socket socket) {
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
                    if (str[0].equals("确定")) {
                        System.out.println(str[1]+"学生向" + str[2] + "老师申请创新学分。");
                        idPas = select(str[1], str[2]);
                        if (idPas == 1) {
                            System.out.println("经判断，该学生确实属于该老师！");
                            try {
                                //注册驱动
                                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                                //获取连接
                                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                        "3306/lfy", "root", "123456");
                                //获取数据库操作对象
                                stmt = conn.createStatement();
                                //执行SQL语句
                                String sql = "insert into 申请材料(stuName,teaName,type,projectName,fenshu,date)" + "values(" + "'" + str[1] + "','" + str[2] + "','" + str[3] + "', '" + str[4] + "','" + str[5] + "','"+str[6]+"')";
                                //若成功更新，则返回值1
                                int row = stmt.executeUpdate(sql);
                                if(row != 1){
                                    System.out.println("申请失败！");
                                }else {
                                    System.out.println("申请成功！");
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } finally {
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
                        } else {
                            System.out.println("该老师不存在！");
                        }
                    }
                    if(str[0].equals("上传")){
                        System.out.println(str[2]+"上传了认证资料");
                        Connection conn = null;
                        PreparedStatement ps = null;

                        //注册驱动
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                    "3306/lfy", "root", "123456");
                            //获取数据库操作对象
                            String sql = "update 申请材料 set  `file` = ? where stuName = '"+str[2]+"'";
                            ps = (PreparedStatement) conn.prepareStatement(sql);
                            ps.setClob(1,new FileReader(new File(str[1])));
                            ps.executeUpdate();
                            System.out.println("上传成功");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if(str[0].equals("下载")){
                        System.out.println(str[2]+"要下载认证资料");
                        Connection conn = null;
                        PreparedStatement ps = null;
                        ResultSet rs = null;
                        InputStream is = null;
                        OutputStream os = null;

                        //注册驱动
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                    "3306/lfy", "root", "123456");
                            //获取数据库操作对象
                            String sql = "select * from 申请材料 where stuName=?";
                            ps = (PreparedStatement) conn.prepareStatement(sql);
                            ps.setString(1,"刘飞阳");
                            rs = ps.executeQuery();
                            // 接收结果并存储到txt文件
                            while (rs.next()) {
                                Blob b = rs.getBlob("file");
                                is = b.getBinaryStream();
                                os = new FileOutputStream(str[1]);
                                //开始读，采用边读边写
                                int temp = 0;
                                while ((temp = is.read()) != -1) {
                                    os.write(temp);
                                }
                            }
                            System.out.println("下载成功！");
                            pw.println("下载成功");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if(str[0].equals("删帖")){
                        System.out.println(str[1]+"删除了自己的留言");
                        Connection conn =  null;
                        PreparedStatement ps = null;
                        //注册驱动
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                    "3306/lfy","root","123456");
                            //执行SQL语句
                            String sql = "delete from 留言板 where name = ?";
                            ps = (PreparedStatement) conn.prepareStatement(sql);
                            ps.setString(1, Client.name);
                            System.out.println("删除成功！");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if(str[0].equals("发帖")){
                        System.out.println(str[1]+"同学发起留言");
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
                            stmt = conn.createStatement();
                            String sql ="insert into 留言板(name,date,留言)" + "values(" + "'" + str[1]+ "','" + str[2] + "','"+str[3]+"')";
                            //若成功更新，则返回值1
                            int row = stmt.executeUpdate(sql);
                            if(row != 1){
                                System.out.println("留言失败！");
                            }else {
                                System.out.println("留言成功！");
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if(str[0].equals("回复")){
                        System.out.println(str[1]+"老师回复留言");
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
                            stmt = conn.createStatement();
                            String sql ="insert into 回复留言(teaName,date,stuName,留言)" + "values(" + "'" + str[1]+ "','" + str[2] + "','"+str[3]+"','"+str[4]+"')";
                            //若成功更新，则返回值1
                            int row = stmt.executeUpdate(sql);
                            if(row != 1){
                                System.out.println("回复失败！");
                            }else {
                                System.out.println("回复成功！");
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if(str[0].equals("查看留言")){
                        Connection conn = null;
                        PreparedStatement ps = null;
                        ResultSet rs = null;
                        if(Client.insertsex.equals("教师登录")) {
                            //注册驱动
                            try {
                                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                                //获取连接
                                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
                                //执行sql语句
                                String sql = "select * from student where teacher = ?";
                                //获取数据库操作对象
                                ps = (PreparedStatement) conn.prepareStatement(sql);
                                ps.setString(1, str[1].trim());
                                rs = ps.executeQuery();
                                int count = 0;
                                while (rs.next()) {
                                    count++;
                                }
                                rs = ps.executeQuery();
                                String[] str1 = new String[1];
                                str1[0] = new String("");
                                count = 0;
                                while (rs.next()) {
                                    str1[0] += rs.getString("name") + ",";
                                }
                                String[] str3 = str1[0].split(",");
                                for (int i = 0; i < str3.length; i++) {
                                    String sql1 = "select * from 留言板 where name = ?";
                                    //获取数据库操作对象
                                    ps = (PreparedStatement) conn.prepareStatement(sql1);
                                    ps.setString(1, str3[i].trim());
                                    rs = ps.executeQuery();
                                    int count1 = 0;
                                    while (rs.next()) {
                                        count1++;
                                    }
                                    rs = ps.executeQuery();
                                    String[] str2 = new String[3];
                                    count1 = 0;
                                    while (rs.next()) {
                                        str2[0] = rs.getString("name");
                                        str2[1] = rs.getString("date");
                                        str2[2] = rs.getString("留言");
                                    }
                                    System.out.println("111");
                                    pw.println("留言" + "," + str2[0] + "," + str2[1] + "," + str2[2]);
                                    System.out.println("留言" + "," + str2[0] + "," + str2[1] + "," + str2[2]);
                                    System.out.println("留言查询成功！");
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                        if(Client.insertsex.equals("学生登录")){
                            //注册驱动
                            try {
                                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                                //获取连接
                                conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lfy", "root", "123456");
                                //执行sql语句
                                String sql = "select * from 回复留言 where stuName = ?";
                                //获取数据库操作对象
                                ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                                ps.setString(1, Client.name.trim());
                                rs = ps.executeQuery();
                                int count = 0;
                                while (rs.next()) {
                                    count++;
                                }
                                rs = ps.executeQuery();
                                String[] str1 = new String[4];
                                // str1[0] = new String("");
                                count = 0;
                                while (rs.next()) {
                                    str1[0] = rs.getString("teaName") ;
                                    str1[1] = rs.getString("date") ;
                                    str1[2] = rs.getString("stuName") ;
                                    str1[3] = rs.getString("留言") ;
                                }
                                pw.println("查询成功"+","+str1[0]+","+str1[1]+","+str1[2]+","+str1[3]);
                                System.out.println("留言查询成功！");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                    if(str[0].equals("提交")){
                        Connection conn = null;
                        PreparedStatement ps = null;
                        ResultSet rs = null;
                        String s;
                        System.out.println(str[2]+"老师，提交了审阅");
                        //注册驱动
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                    "3306/lfy","root","123456");
                            //执行sql语句
                            String sql1 = "select * from student where name= ? ";
                            //获取数据库操作对象
                            ps = (PreparedStatement) conn.prepareStatement(sql1);
                            ps.setString(1,str[3]);
                            rs = ps.executeQuery();
                            int count1 = 0;
                            while (rs.next()) {
                                count1++;
                            }
                            rs = ps.executeQuery();
                            String[] str3 = new String[1];
                            count1 = 0;
                            while (rs.next()){
                                str3[0] = rs.getString("grade");
                            }
                            System.out.println(str3[0]);
                            System.out.println(String.valueOf(Integer.valueOf(str[4])));
                            //执行sql语句
                            String sql2 = "update student set grade = ?,level = ?where name = '"+str[3]+"'";
                            ps = (PreparedStatement) conn.prepareStatement(sql2);
                            String a = String.valueOf(Integer.valueOf(str3[0])+Integer.valueOf(str[4]));
                            System.out.println(a);
                            if(Integer.valueOf(a) >= 10){
                                s = "优秀";
                            }else  if(Integer.valueOf(a)>=6&&Integer.valueOf(a)<10){
                                s = "良好";
                            }else if(Integer.valueOf(a)>=3&&Integer.valueOf(a)<6){
                                s = "及格";
                            }else {
                                s= "不及格";
                            }
                            //手动给参数赋值
                            ps.setString(1,a);
                            ps.setString(2,s);

                            int p = ps.executeUpdate();

                            //执行sql语句
                            String sql = "update 申请材料 set `批阅情况` = ? where stuName = '"+str[3]+"'";
                            ps = (PreparedStatement) conn.prepareStatement(sql);
                            //手动给参数赋值
                            ps.setString(1,str[1]);
                            int i = ps.executeUpdate();
                            System.out.println("提交成功!");
                            pw.println("提交成功");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if(str[0].equals("提交信息")){
                        Connection conn = null;
                        PreparedStatement stmt = null;
                        ResultSet rs = null;
                        //注册驱动
                        try {
                            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                            //获取连接
                            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:" +
                                    "3306/lfy","root","123456");
                            //执行sql语句
                            String sql = "select * from student where name= ? and identification =? and id =?";
                            //获取数据库操作对象
                            stmt = (PreparedStatement) conn.prepareStatement(sql);
                            stmt.setString(1,str[2].trim());
                            stmt.setString(2,str[1].trim());
                            stmt.setString(3,str[3].trim());
                            rs = stmt.executeQuery();
                            System.out.println("查询成功，信息属实！");
                            pw.println("提交成功");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //申请学分
        public static int select(String name,String teacher){

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
                String sql = "select * from student where name = ? and teacher = ? ";
                //获取数据库操作对象
                stmt = (PreparedStatement) conn.prepareStatement(sql);
                stmt.setString(1,name.trim());
                stmt.setString(2,teacher.trim());
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
