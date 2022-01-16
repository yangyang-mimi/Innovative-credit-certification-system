package 大二上学期实训;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class ChatServer extends ChatPanel implements Runnable {
    InetAddress address;
    int port;
    static String s,c;
    DatagramSocket socket;  // 1.创建服务器端DatagramSocket，指定端口

    static BufferedReader br = null;
    static PrintWriter pw = null;
    Socket client = null;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",1100);
            System.out.println("连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //普通代码块
    {
        try {
            socket = new DatagramSocket(8800);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    //构造方法
    public ChatServer() {
        super();
        jf.setTitle("聊天页面");
        Thread t = new Thread(this);
        t.start();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button1) {
                    send();
                    s = Input1.getText();
                    String s1 = button1.getText();
                    pw.println(s1+","+Client.name);
                    Input1.setText(null);
                }
            }
        });
        JLabel fastLabel = new JLabel("快捷方式");
        p1.add(fastLabel);
        String[] college = {"同学你好，我现在不方便和你聊天，你可以对我进行留言。","很高兴和你聊天！"};
        JComboBox fastJComboBox = new JComboBox(college);
        p1.add(fastJComboBox);
        for(int i=0; i < college.length; i++) {
            fastJComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    c = (String) fastJComboBox.getSelectedItem();
                    Input1.setText(c);
                }
            });
        }

    }


    //发送按钮  发送信息

    private void send() {
        String send = Input1.getText();           //获取输入框中文字
        byte[] data2 = send.getBytes();        //将接收到的数据转换为字节数组
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);// 2.创建数据报，包含响应的数据信息
        try {
            socket.send(packet2);                  // 3.响应客户端    发送给客户端

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (port == 7740) {
            Text.append(Client.name+"老师回复：" + send + "\n");             //在聊天面板上显示条田信息
        }
        if (port == 7750) {
            Text.append("服务端回复客户端2：" + send + "\n");             //在聊天面板上显示条田信息
        }
    }

    //接收信息

    @Override
    public void run() {
        while (true) {
            // 2.创建数据报，用于接收客户端发送的数据
            byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);                 // 此方法在接收到数据报之前会一直阻塞
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 4.读取数据
            String info = new String(data, 0, packet.getLength());//创建字符串对象

            address = packet.getAddress();//获取发送端的地址
            port = packet.getPort();//获取 发送端进程所绑定的端口
            if (port == 7740) {
                Text.append("学生:" + info + "\n"); //在聊天面板显示信息
            }
            if (port == 7750) {
                Text.append("客户端2:" + info + "\n"); //在聊天面板显示信息
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}