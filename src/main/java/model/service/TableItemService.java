package model.service;

import model.DBConnectFactory;
import model.dao.DBConnect;
import model.entity.TableItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        return connection.updatePerson(statement);
    }
//    public void insertPeople(String fullName, double balance) {
//           String sql = "INSERT INTO users_water (fio,balance) " + "VALUES ('" + fullName + "'," + balance + ");";
//           dbConnect.insertPerson(sql);
//    }
//
//    public void deletePeople(int id) {
//        String sql = "DELETE FROM users_water WHERE user_id =" + id + ";";
//        dbConnect.deletePerson(sql);
//        System.out.println("Deletion done successfully with ID=" + id);
//    }
//
//    public void updatePeople(int ID, double balance) {
//        String sql = "UPDATE users_water SET  balance = balance + '" + balance + "' WHERE user_id=" + ID + ";";
//        dbConnect.updatePerson(sql);
//        System.out.println("Updating of ID's=" + ID + " balance=" + balance + " done successfully");
//    }

}
