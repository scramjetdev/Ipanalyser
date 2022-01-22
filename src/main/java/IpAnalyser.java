import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import eu.bitwalker.useragentutils.UserAgent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class IpAnalyser {
    String agent = null;
    UserAgent userAgent = null;


    public String analyse(String ip)  {
        System.out.println("Analysing " + ip);
        WebServiceClient client = new WebServiceClient.Builder(662612, "taMqPvRLKdrbWXVl").host("geolite.info").build();
        InetAddress ipAdress = null;
        try {
            ipAdress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        CityResponse response = null;
        try {
            response = client.city(ipAdress);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }

        Country countryans = response.getCountry();
        return countryans.getIsoCode();

    }

    public String agentToBrowser(String userAgentString) {return UserAgent.parseUserAgentString(userAgentString).getBrowser().getGroup().toString();}
    public String agentToOS(String userAgentString) {return UserAgent.parseUserAgentString(userAgentString).getOperatingSystem().getGroup().toString();}


}
