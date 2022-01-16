package 大二上学期实训;

import com.mysql.jdbc.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Statement;

public class TeaServer extends Client {
    public static void main(String[] args)  {
        Socket socket = null;
        try {
            ServerSocket ss = new ServerSocket(1100);
            System.out.println("等待客户端连接！");
            socket = ss.accept();
            System.out.println("连接成功！");
            //创建线程对象
            Thread a = new Thread(new TeaServer.ThreadTest01(socket));
            a.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class ThreadTest01 implements Runnable{

        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        BufferedWriter bw = null;
        Connection conn = null;
        Statement stmt = null;

        public ThreadTest01(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (true){
                    String message = br.readLine();
                    String[] str = message.split(",");
                    if(str[0].equals("查看个人信息")){
                        System.out.println(str[1]+"查看了她的信息");
                    }
                    if(str[0].equals("修改个人信息")){
                        System.out.println(str[1]+"修改了她的个人信息");
                    }
                    if(str[0].equals("确定")){
                        System.out.println(str[3]+"已经修改信息完毕！修改的信息为："+str[1]+str[2]);
                    }
                    if(str[0].equals("发送")){
                        System.out.println(str[1]+"向学生发起聊天");
                    }
                    if(str[0].equals("姓名")){
                        System.out.println("老师选择"+str[0]+"的方式查看学生信息");
                    }
                    if (str[0].equals("学院")){
                        System.out.println("老师选择"+str[0]+"的方式查看学生信息");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
