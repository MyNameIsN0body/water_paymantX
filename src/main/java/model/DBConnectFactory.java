package model;

import java.util.Properties;

public class DBConnectFactory {
    private String hostName;
    private Integer port;
    private String dbName;

    private DBConnect connect = null;
    private Properties properties = new Properties();

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setUserName(String userName) {
        properties.setProperty("user", userName);
    }

    public void setPassword(String password) {
        properties.setProperty("password", password);
    }

    public boolean isConnect(){
        return connect.isConnected();
    }

    public DBConnect getConnect() {
        if (connect == null) {
            String urlFromat = "jdbc:postgresql://%s:%d/%s";
            String connectionString = String.format(urlFromat,hostName,port,dbName);
            DBConnect connect = new DBConnect(connectionString,properties);
            this.connect = connect;
            return connect;
        }
        return connect;
    }

}
