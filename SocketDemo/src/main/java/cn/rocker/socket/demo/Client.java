package cn.rocker.socket.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

/**
 * @author rocker
 * @version V1.0
 * @Description:
 * @date 2018/11/19 22:45
 */
public class Client {
    static Logger logger = LoggerFactory.getLogger(Client.class);

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final int SLEEP_TIME = 5000;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORT);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    logger.info("客户端启动成功");
                    while(true){
                        try {
                            String message = "hello world";
                            logger.info("客户端发送数据:{}",message);
                            socket.getOutputStream().write(message.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                            logger.error("客户端发送数据出错");
                        }
                        sleep();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
