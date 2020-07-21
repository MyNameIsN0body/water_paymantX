package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private Button tryСonnectButton;

    public static String getFXMLPath() {
        return "voda_00.fxml";
    }

    public void  initUI(String hostTextField){

    }
}
