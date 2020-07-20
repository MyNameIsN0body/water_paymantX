import controller.UIFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    //private Stage primaryStage;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("uiLocale",
            Locale.getDefault());

    @Override
    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("График оплаты");
//        this.primaryStage.setResizable(false);

        UIFactory factory = new UIFactory(resourceBundle, primaryStage);

        try {
            factory.initUI();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}
