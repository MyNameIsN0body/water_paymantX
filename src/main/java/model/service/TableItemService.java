package model.service;

import model.DBConnectFactory;
import model.entity.TableItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableItemService {
    Connection connection = (Connection) DBConnectFactory.getConnect();

    public void addItem(TableItem tableItem) throws SQLException {
        PreparedStatement statement= null;

        String sql = "INSERT INTO users_water (fio,balance) VALUES (?,?);";

        try{
            statement = connection.prepareStatement(sql);
            statement.setString(1, tableItem.getFio());
            statement.setDouble(2,tableItem.getBalance());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }

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
