package cn.rocker.socket.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author rocker
 * @version V1.0
 * @Description:
 * @date 2018/11/19 22:17
 */
public class Server {
    Logger logger = LoggerFactory.getLogger(Server.class);

    private ServerSocket serverSocket;

    public Server(int port){
        try {
            this.serverSocket = new ServerSocket(port);
            logger.info("服务端启动成功，端口:{}",port);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("服务端启动失败，端口:{}",port);
        }
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                doStart();
            }
        }).start();
    }

    private void doStart(){
        while(true){
            try {
                Socket client = serverSocket.accept();
                new ClientHandler(client).start();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("服务端异常");
            }
        }
    }

}
