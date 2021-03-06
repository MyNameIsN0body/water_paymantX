package model.dao;

import java.sql.*;
import java.util.Properties;

public class DBConnect {
    //  Database credentials
    private static final String POSTGRES = "org.postgresql.Driver";
    private final String connectionString;
    private final Properties properties;

    public DBConnect(String connectionString, Properties properties){
        this.connectionString = connectionString;
        this.properties = properties;
    }

    public boolean isConnected(){
        try {
            Connection connection = openConnection();
            return connection.isValid(3000);
        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
            return false;
        }
    }
    private Connection openConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
            //login DB
            Class.forName(POSTGRES);
            connection = DriverManager.getConnection(connectionString, properties);
            connection.setAutoCommit(false);
        return connection;
    }
    public PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        return openConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    public long insertPerson(PreparedStatement statement) throws SQLException {
        int affectRow = statement.executeUpdate();
        if (affectRow == 0) {
            throw  new SQLException("Insert statement failed");
        }
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet!=null && resultSet.next()) {
            //resultSet.next() требуется или нет надо проверить на реальной базе
            return resultSet.getLong(1);
        } else {
            throw  new SQLException("Insert statement failed");
        }
    }

    public int deletePerson(PreparedStatement statement) throws SQLException{
        int affectRow = statement.executeUpdate();
        if (affectRow == 0) {
            throw  new SQLException("Delete statement failed");
        }
        return affectRow;
    }
    public int updatePerson(PreparedStatement statement) throws SQLException{
        int affectRow = statement.executeUpdate();
        if (affectRow == 0) {
            throw  new SQLException("Update statement failed");
        }
        return affectRow;
    }
    public ResultSet selectPerson(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
}