import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static DBmanage dBmanage = new DBmanage();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(25565), 0);
        server.createContext("/datas", new CustomHandler());
        server.start();
        Boolean connect = dBmanage.connect("37.59.90.35", 3306, "scramjetdb", "scramjet", "scramjet");
        if (connect == true){
            System.out.println("connected");
        }
    }
}
