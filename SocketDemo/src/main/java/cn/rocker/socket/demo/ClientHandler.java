package cn.rocker.socket.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author rocker
 * @version V1.0
 * @Description:
 * @date 2018/11/19 22:31
 */
public class ClientHandler {
    Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    private static final int MAX_DATA_LEN = 1024;
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start(){
        logger.info("新客户端接入");
        new Thread(new Runnable() {
            @Override
            public void run() {
                doStart();
            }
        }).start();
    }

    private void doStart(){
        try {
            InputStream inputStream = socket.getInputStream();//获取消息
            while(true){
                byte[] data = new byte[MAX_DATA_LEN];
                int len;
                while((len = inputStream.read(data)) != -1){
                    String message = new String(data, 0, len);
                    logger.info("客户端传来的消息:{}",message);
                    socket.getOutputStream().write(data);//把消息写回去
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
