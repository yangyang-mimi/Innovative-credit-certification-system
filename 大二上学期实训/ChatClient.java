package 大二上学期实训;

/**
 * 客户端
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;

public class ChatClient extends ChatPanel implements ActionListener,Runnable {

    static PrintWriter pw = null;
    static BufferedReader br = null;
    Socket client = null;
    //普通代码块总比他们先执行
    {
        try {
            client = new Socket("127.0.0.1",1010);
            System.out.println("连接成功！");
            pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    InetAddress address; // 1.定义服务器的地址、端口号、数据
    int port = 8800;//定义端口类型   服务器端口名
    String s,s1;

    DatagramSocket socket;
    {
        try {
            //客户端口号
            socket = new DatagramSocket(7740);
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public ChatClient() {
        super();
        jf.setTitle("聊天页面");
        Thread t=new Thread(this);
        t.start();
        Input1.setText("老师你好!您现在有时间嘛？");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button1){
                    send();
                    s = Input1.getText();
                    s1 = button1.getText();
                    Input1.setText(null);
                    pw.println(s1+","+ Client.name);
                    System.out.println(s1+Client.name);
                }
            }
        });
        JLabel fastLabel = new JLabel("快捷方式");
        p1.add(fastLabel);
        String[] college = {"老师您好，现在有空嘛","很高兴和你聊天！"};
        JComboBox fastJComboBox = new JComboBox(college);
        p1.add(fastJComboBox);
        for(int i=0; i < college.length; i++) {
            fastJComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String c = (String) fastJComboBox.getSelectedItem();
                    Input1.setText(c);
                }
            });
        }

    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button1) {
            send();
        }

    }

    //发送信息
    public void send() {
        String send=Input1.getText(); //获取文本
        byte[] data = send.getBytes();//将接收到的数据变成字节数组
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);//2.创建数据报，包含发送的数据信息

        try {
            socket.send(packet);// 4.向服务器端发送数据报
        } catch (IOException e) {
            e.printStackTrace();
        }
        Text.append(Client.name+"学生说:"+send+"\n");    //在面板上显示信息
    }

    //接受服务端的信息
    @Override
    public void run() {
        while (true) {

            /*
             * 接收服务器端响应的数据
             */
            byte[] data2 = new byte[1024];//创建字节数组
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length);// 1.创建数据报，用于接收服务器端响应的数据
            try {
                socket.receive(packet2);// 2.接收服务器响应的数据
            } catch (IOException e) {
                e.printStackTrace();
            }
            //3.读取数据
            String reply = new String(data2, 0, packet2.getLength());//创建字符串对象
            Text.append("老师回复学生：" + reply + "\n");      //在面板上显示数据


        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }

}
