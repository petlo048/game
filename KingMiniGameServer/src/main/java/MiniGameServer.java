import com.sun.net.httpserver.HttpServer;
import handler.MiniGameHandler;
import util.Parameters;

import java.net.InetSocketAddress;

public class MiniGameServer {




    public static void main(String[] args) throws Exception {

        String hostname = getHostName(args);
        int port = getPort(args);

        final HttpServer server = HttpServer.create(new InetSocketAddress(hostname, port), Parameters.BACKLOG);
        String map = "/";
        server.createContext(map, new MiniGameHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    private static String getHostName(String[] args) {
        if (args.length > 0){
            return args[0];
        }
        return Parameters.LOCALHOST;
    }

    private static int getPort(String[] args) {
        if (args.length > 1) {
            return Integer.valueOf(args[1]);
        }
        return Parameters.DEFAULT_PORT;
    }


}
