import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
//http://176.186.93.104:25565/datas

public class CustomHandler implements HttpHandler {
    IpAnalyser ipAnalyser = new IpAnalyser();
    String userAgent;
    String ipAdress;
    String opSys;
    String browser;

    String country = null;
    @Override
    public void handle(HttpExchange exchange) {
        try {
            exchange.sendResponseHeaders(200, -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        exchange.close();
        userAgent = exchange.getRequestHeaders().getFirst("User-agent");
        ipAdress = exchange.getRequestHeaders().getFirst("X-FORWARDED-FOR");
        if (ipAdress == null){
            ipAdress = exchange.getRemoteAddress().getAddress().getHostAddress();
        }
        opSys = ipAnalyser.agentToOS(userAgent);
        browser = ipAnalyser.agentToBrowser(userAgent);
        country = ipAnalyser.analyse(ipAdress);

        System.out.println("==============================");
        System.out.println("Request by ↓");
        System.out.println(ipAdress);
        System.out.println(userAgent);
        System.out.println(opSys);
        System.out.println(browser);
        System.out.println(country);


        if (Main.dBmanage.isConnected()){
            System.out.println("Connected to database and going to send infos");
            Main.dBmanage.addInfos(ipAdress, country, System.currentTimeMillis(), browser, opSys);
        }

    }

}
