package model;

import java.util.Properties;

/**
 * Меняем класс фабрики на статический, чтобы можно было с одним экземпляром из любого места программы
 */
public class DBConnectFactory {

    private static final DBConnectFactory connectFactory = new DBConnectFactory();

    private String hostName;
    private Integer port;
    private String dbName;

    private DBConnect connect = null;
    private Properties properties = new Properties();

    /**
     * запрет создавать экземпляры
     */
    private DBConnectFactory() {

    }

    public static String getHostName() {
        return connectFactory.hostName;
    }

    public static void setHostName(String hostName) {
        connectFactory.hostName = hostName;
    }

    public static Integer getPort() {
        return connectFactory.port;
    }

    public static void setPort(Integer port) {
        connectFactory.port = port;
    }

    public static String getDbName() {
        return connectFactory.dbName;
    }

    public static void setDbName(String dbName) {
        connectFactory.dbName = dbName;
    }

    public static void setUserName(String userName) {
        connectFactory.properties.setProperty("user", userName);
    }

    public void setPassword(String password) {
        connectFactory.properties.setProperty("password", password);
    }

    /**
     * Получаем DBConnect
     * @return DBConnect или null если соединение прошло неудачно
     */
    public static DBConnect getConnect(){
        if (connectFactory.connect == null) {
            String urlFromat = "jdbc:postgresql://%s:%d/%s";
            String connectionString = String.format(urlFromat,getHostName(),getPort(),getDbName());
            DBConnect connect = new DBConnect(connectionString,connectFactory.properties);
            if (!connect.isConnected()) return null;
            connectFactory.connect = connect;
            return connect;
        }
        return connectFactory.connect;
    }

    public static DBConnectFactory getFactory() {
        return connectFactory;
    }
}