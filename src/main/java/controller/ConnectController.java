package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DBConnect;
import model.DBConnectFactory;

public class ConnectController {
    @FXML
    private TextField hostTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button cancelСonnectionButton;

    @FXML
    private Button tryСonnectButtonAction;

    private UIFactory factory;

    public static String getFXMLPath() {
        return "voda_00.fxml";
    }



    /**
     * неправильно смешивать названия функций, функция init - инициализирует форм,
     * эта функция инициализирует коннект,
     */
    public boolean connectDB(){
        DBConnectFactory dbConnectFactory = DBConnectFactory.getFactory();
        dbConnectFactory.setHostName(hostTextField.getText());
        /* вот так на самом деле неправльно - Integer.parseInt(portTextField.getText()),
        *  так как функция parseInt бросает NumberFormatException и если пользолватель ввел не цифры, то
        * у тебя из-за маленькой ошибки сломалась вся программа
        */
        dbConnectFactory.setPort(Integer.parseInt(portTextField.getText()));
        //////// нужно dbName ? - да, нужно. Имя базы обязательно
        dbConnectFactory.setUserName(userNameTextField.getText());
        dbConnectFactory.setPassword(passwordTextField.getText());
        DBConnect connect = DBConnectFactory.getConnect();
        return connect != null;
    }

    /**
     * И да, нам все еще нужна ссылка на фабрику форм, как без нее мы будем открывать новую форму
     * @param factory
     */
    public void init(UIFactory factory) {
        this.factory =factory;
    }

    @FXML
    private void tryСonnectButtonAction() {
        if (connectDB()) {
            ///Тут добавим переход на новую форму
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка подключения к базе данных");
            alert.setHeaderText("Неверные параметры подключения");
        }

    }
}
