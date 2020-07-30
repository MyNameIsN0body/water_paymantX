package model.service;

import model.DBConnectFactory;
import model.dao.DBConnect;
import model.entity.TableItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableItemService {
    DBConnect connection =  DBConnectFactory.getConnect();

    public TableItem addItem(TableItem tableItem) throws SQLException {
        Integer currentID = tableItem.getId();
        String currentFio = tableItem.getFio();
        Double currentBalance = tableItem.getBalance();
        PreparedStatement statement= null;

        TableItem newTableItem = new TableItem();
        newTableItem.setUserId(currentID);
        newTableItem.setFio(currentFio);
        newTableItem.setBalance(currentBalance);

        String sql = "INSERT INTO users_water (fio,balance) VALUES (?,?);";

        connection.insertPerson(sql);
        statement.setString(1, currentFio);
        statement.setDouble(2,currentBalance);
        statement.executeUpdate();

        return newTableItem;
    }

    public int removeItem(TableItem tableItem) throws SQLException {
        PreparedStatement statement= null;

        String sql = "DELETE FROM users_water WHERE user_id = ?;";

        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1,tableItem.getId());
           return statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return 0;
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
