import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBmanage {
    private Connection connection;



    public boolean connect( String host, int port, String database, String user, String password){
        if (!isConnected()){
            try{
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database,  user , password);
                return true;
            } catch (SQLException exception){
                System.out.println("Failed to connect to database jdbc:mysql://" + host + ":" + port + "/" + database);
                exception.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public void disconnect(){
        if (isConnected()){
            try{
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public boolean isConnected(){
        try {
            return (connection != null) && (!connection.isClosed()) && (connection.isValid(5));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public void addInfos( String ip, String country, long time, String browser, String opSys){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `Infos`(`ip`, `country`, `time`, `Browser`, `OpSys`) VALUES ('" + ip + "','" + country + "','" + time + "','"+browser+"','"+opSys+"')");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
