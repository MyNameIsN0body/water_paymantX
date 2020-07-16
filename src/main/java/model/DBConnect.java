package model;
import java.sql.*;
import java.util.Properties;

/**
 * Класс работы с базой данных.
 *
 * Внесенные изменения:
 * 1. Все методы пробрасывают исключения, так как обработка исключений должна происходить в контроллере,
 * только там мы можем что-то изменить, спросить у пользователя или закрыть приложение
 * 2. Проведен рефрактинг - так, как на самом деле у нас методы insertPerson, deletePerson, updatePeople
 * делают одно и тоже, то мы создали один метод executeUpdate который выполняет SQL запрос.
 * 3. Добавлен метод получения данных executeSelect, с помощью которого мы будем получать информацию
 * из базы данных.
 *
 */
public class DBConnect {
    //  Database credentials
    private static final String POSTGRES = "org.postgresql.Driver";
    private final String connectionString;
    private final Properties properties;

    public DBConnect(String connectionString, Properties properties){
        this.connectionString = connectionString;
        this.properties = properties;
    }

    /**
     * Проверка подключения к базе данных
     *
     * @return true, если соединение подключено удачно, иначе false
     * @throws ClassNotFoundException ошибка регистрации драйвера
     */
    public boolean isConnected() throws ClassNotFoundException {
        try {
            Connection connection = openConnection();
            return connection.isValid(3000);
        } catch (SQLException throwables) {
            //Поясняю, неверно заполненные параметры, это нормально для данного метода (пользователь ошибся)
            //по этому, это исключение не пробрасывается выше.
            return false;
        }
    }


    /**
     * Создание соединения.
     *
     * @return Connection с AutoCommit = false
     * @throws SQLException ошибка подключения
     * @throws ClassNotFoundException ошибка регистрации драйвера
     */
    private Connection openConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Class.forName(POSTGRES);
        connection = DriverManager.getConnection(connectionString, properties);
        connection.setAutoCommit(false);
        return connection;
    }

    /**
     * Обработчик любых запросов Insert, Delete, Update
     *
     * @param sql sql запрос
     * @throws Exception on error
     */
    public void executeUpdate(String sql) throws Exception {
        Connection connection = openConnection();
        Statement statement= connection.createStatement();
        statement.executeUpdate(sql);
        connection.commit();

        statement.close();
        connection.close();
    }

    /**
     * Обработчик запросов Select
     *
     * @param sql  sql запрос
     * @return ResultSet
     * @throws Exception on error
     */
    public ResultSet executeSelect(String sql) throws Exception {
        Connection connection = openConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        connection.commit();

        statement.close();
        connection.close();
        return result;
    }


}
