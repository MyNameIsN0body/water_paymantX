package model.service;

import model.DBConnectFactory;
import model.dao.DBConnect;
import model.entity.TableItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableItemService {
    DBConnect connection = DBConnectFactory.getConnect();

    public TableItem addItem(TableItem tableItem) throws SQLException, ClassNotFoundException {
        TableItem newtableItem = tableItem;
        String currentFio = newtableItem.getFio();
        Double currentBalance = newtableItem.getBalance();
        PreparedStatement statement = null;

        String sql = "INSERT INTO users_water (fio,balance) VALUES (?,?);";
        statement.setString(1, currentFio);
        statement.setDouble(2, currentBalance);
//        statement.executeUpdate();
        statement = connection.getPreparedStatement(sql);
        long id = connection.insertPerson(statement);
        newtableItem.setUserId(id);
        return newtableItem;
    }

    public int removeItem(TableItem tableItem) throws SQLException, ClassNotFoundException {

        PreparedStatement statement = null;
        long item = tableItem.getId();
        String sql = "DELETE FROM users_water WHERE user_id = ?;";
        statement = connection.getPreparedStatement(sql);
        statement.setLong(1, item);
        int countDeleted = connection.deletePerson(statement);
        return countDeleted;
    }

    public TableItem updateItem(TableItem tableItem) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = null;
        double balance = tableItem.getBalance();
        long ID = tableItem.getId();

        String sql = "UPDATE users_water SET  balance = balance + ? WHERE user_id = ?;";
        statement = connection.getPreparedStatement(sql);
        statement.setDouble(1,balance);
        statement.setLong(2,ID);
        connection.updatePerson(statement);

        PreparedStatement statementSelect = null;
        String sqlSelect = "SELECT * FROM users_water WHERE user_id = ?;";
        statementSelect = connection.getPreparedStatement(sqlSelect);
        statementSelect.setLong(1,ID);

        ResultSet resultSet= connection.selectPerson(statementSelect);
        TableItem updatedItem = new TableItem();
        if(resultSet != null) {
            updatedItem.setUserId(resultSet.getLong("user_id"));
            updatedItem.setFio(resultSet.getString("fio"));
            updatedItem.setBalance(resultSet.getDouble("balance"));
        }
        return updatedItem;
    }
    public List<TableItem> getAllItem() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = null;
        List<TableItem> allItem = new ArrayList<>();

        String sql = "SELECT * FROM users_water";
        statement = connection.getPreparedStatement(sql);
        ResultSet resultSet= connection.selectPerson(statement);
        while (resultSet.next()) {
            TableItem item = new TableItem();
            item.setUserId(resultSet.getLong("user_id"));
            item.setFio(resultSet.getString("fio"));
            item.setBalance(resultSet.getDouble("balance"));
            allItem.add(item);
        }
        return allItem;
    }
}
