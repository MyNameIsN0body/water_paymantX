package model;

public class People {

    private int id;
    private String fio;
    private double balance;
    DBConnect dbConnect;

    public void insertPeople(String fullName, double balance) {
           String sql = "INSERT INTO users_water (fio,balance) " + "VALUES ('" + fullName + "'," + balance + ");";
           dbConnect.insertPerson(sql);
    }

    public void deletePeople(int id) {
        String sql = "DELETE FROM users_water WHERE user_id =" + id + ";";
        dbConnect.deletePerson(sql);
        System.out.println("Deletion done successfully with ID=" + id);
    }

    public void updatePeople(int ID, double balance) {
        String sql = "UPDATE users_water SET  balance = balance + '" + balance + "' WHERE user_id=" + ID + ";";
        dbConnect.updatePerson(sql);
        System.out.println("Updating of ID's=" + ID + " balance=" + balance + " done successfully");
    }

}
