package cn.rocker.socket.demo;

/**
 * @author rocker
 * @version V1.0
 * @Description:
 * @date 2018/11/19 22:29
 */
public class ServerBoot {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }

}
