package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    public static String getFXMLPath() {
        return "voda_00.fxml";
    }

    public void  initUI(){
        DBConnectFactory dbConnectFactory = new DBConnectFactory();
        dbConnectFactory.setHostName(hostTextField.getText());
        dbConnectFactory.setPort(Integer.parseInt(portTextField.getText()));
        //////// нужно dbName ?
        dbConnectFactory.setUserName(userNameTextField.getText());
        dbConnectFactory.setPassword(passwordTextField.getText());
        dbConnectFactory.getConnect();
    }

    @FXML
    private void tryСonnectButtonAction() {
        initUI();
    }
}
