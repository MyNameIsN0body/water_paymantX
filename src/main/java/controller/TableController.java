package controller;

import com.sun.tools.javac.Main;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LongStringConverter;
import model.entity.TableItem;
import model.service.TableItemService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements IController {
    private TableItemService service = new TableItemService();
    // TABLE VIEW
    @FXML
    private TableView<TableItem> allTableView;

    @FXML
    private TableColumn<TableItem, Long> IDtableColumn;

    @FXML
    private TableColumn<TableItem, String> FIOtableColumn;

    @FXML
    private TableColumn<TableItem, Double> BalancetableColumn;

    ObservableList<TableItem> observableList = FXCollections.observableArrayList(new Callback<TableItem, Observable[]>() {
        @Override
        public Observable[] call(TableItem param) {
            return new Observable[]{param.getStringPropertyId(), param.getStringPropertyFIO(), param.getDoublePropertyBalance()};
        }
    });
    //  View Controller

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField middleNameTextField;

    @FXML
    private TextField balanceTextField;

    @FXML
    private Button addPersonButton;

    /**
     * Давай вот это поле вообще уберем, мы не будем вводить id, мы будем выбирать пользователя мышкой в списке
     */
    @FXML
    private TextField removePersonTextField;

    private UIFactory factory;

    @FXML
    private Button removePersonButton;

    /*
     *  передаем контроллер, он нам понадобится. Пока комментируем весь код, которы связан см б/д, чтобы не менять
     *  дальше не стал комментировать, хотя надо.
     */
    public void init(UIFactory factory) {
        this.factory = factory;
        /*
        try {
            Connection connection = DBConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from users_water");
            while(resultSet.next()) {
                observableList.add(new ModelTable(resultSet.getInt("user_id"),resultSet.getString("fio"),resultSet.getDouble("balance")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        */
// relativePath.setCellValueFactory(x->x.getValue().relativePathProperty()

        IDtableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        FIOtableColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        BalancetableColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        try {
            // добавляем все элементы в observableList
            observableList.setAll(service.getAllItem());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error " + e.getMessage());  // тут добавляется обработка ошибок
        }
        /*
           Установка отрисовки ячеек, как обычных текстовых
         */
        IDtableColumn.setCellFactory(col -> new TextFieldTableCell<>(new LongStringConverter()));
        FIOtableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        /*
           Для данной ячейки нужен еще и конвертор в значение Double
         */
        BalancetableColumn.setCellFactory(col -> new TextFieldTableCell<>(new DoubleStringConverter()));
        /*
         * осталось дописать сюда:
         * событие по двойному клику мышки
         * перевод ячеек в readonly         *
         */
        allTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {

                }
            }
        });

        allTableView.setItems(observableList);

        addPersonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        removePersonButton.setOnAction(e -> {
            TableItem selectedItem = allTableView.getSelectionModel().getSelectedItem();
            allTableView.getItems().remove(selectedItem);
            try {
                removePerson(selectedItem);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
    }
    private void removePerson(TableItem selectedItem) throws SQLException, ClassNotFoundException {
        long deleteID =selectedItem.getId();
        TableItem deleteItem = new TableItem();
        deleteItem.setUserId(deleteID);
        observableList.remove(service.removeItem(deleteItem));
    }
//    @FXML
//    private void removePersonButtonAction() throws SQLException, ClassNotFoundException {
//        long deleteID = Long.parseLong(removePersonTextField.getText());
//        TableItem deleteItem = new TableItem();
//        deleteItem.setUserId(deleteID);
//        observableList.remove(service.removeItem(deleteItem));
//        removePersonTextField.clear();
//    }
//    @FXML
//    private void addPersonButtonAction() throws SQLException {
//        String name = surnameTextField.getText() + " " + nameTextField.getText() + " " + middleNameTextField.getText();
//        double balance = Double.parseDouble(balanceTextField.getText());
//
//        TableItem newItem = new TableItem();
//        newItem.setFio(name);
//        newItem.setBalance(balance);
//        try {
//            observableList.add(service.addItem(newItem));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        surnameTextField.clear();
//        nameTextField.clear();
//        middleNameTextField.clear();
//        balanceTextField.clear();
//
////        refreshTable();
////        allTableView.getItems().clear();
////        initialize(this.location,this.resources);
//    }

    public static String getFXMLPath() {
        return "../MainForm.fxml";
    }

    public void openNewStage(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setTitle("График оплаты");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("PaymentForm.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();

        Scene scene = new Scene(rootLayout);
        newStage.setScene(scene);
        newStage.show();
    }

//
//    public void refreshTable() {
//        allTableView.getItems().clear();
//        initialize(this.location,this.resources);
//    }

}
