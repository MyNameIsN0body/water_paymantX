package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PaymentController implements IController{

    @FXML
    private TextField IDUpdatePeopleTextField;

    @FXML
    private TextField balanceUpdatePeopleTextField;

    @FXML
    private Button balanceUpdatePeopleButton;

    @FXML
    private ListView<String> payment_list;

    private UIFactory factory;

    public void init(UIFactory factory) {
        this.factory = factory;
    }
    @FXML
    protected void editPressedAction(ActionEvent event) {
//        People people = new People(new DBConnect().openConnection("postgres", "IpMan"));
//        people.updatePeople(Integer.parseInt(IDUpdatePeopleTextField.getText()), Double.parseDouble(balanceUpdatePeopleTextField.getText()));
//
//        IDUpdatePeopleTextField.clear();
//        balanceUpdatePeopleTextField.clear();
//        Stage stage = (Stage) balanceUpdatePeopleButton.getScene().getWindow();
//        stage.close();
//
    }


    private ObservableList<String> items = FXCollections.observableArrayList();

    public static String getFXMLPath() {
        return "../voda_02.fxml";
    }


//    public void init(UIFactory factory) {
//
//        payment_list.setItems(items);
//        Connection connection = null;
//        Statement statement = null;
//
//        try {
////            connection = DBConnect.getConnect();
////            statement = connection.createStatement();
////            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_water WHERE balance =(SELECT MIN(balance) FROM users_water);");
////
////            while(resultSet.next()) {
////                String ListViewRow = "ID: " + resultSet.getString(1) + " \t"+resultSet.getString(2) + " \t" + resultSet.getString(3) + " руб";
////                items.add(ListViewRow);
////            }
//
////        } catch (SQLException | ClassNotFoundException e) {
////            e.printStackTrace();
////        }
////
////
////
////        payment_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
////            @Override
////            public void handle(MouseEvent event) {
////
////                System.out.println(payment_list.getSelectionModel().getSelectedItem());
////                String a = payment_list.getSelectionModel().getSelectedItem();
////
////                IDUpdatePeopleTextField.setText(payment_list.getSelectionModel().getSelectedItem());
////            }
////        });
////    }
////
////    private void refreshTableScene1() {
////        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.voda_01.fxml"));
////        TableController ctrl = (TableController)(loader.getController());
////        ctrl.refreshTable();
////    }
//
//
}