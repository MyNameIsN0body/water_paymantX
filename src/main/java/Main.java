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
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("uiLocale", Locale.getDefault());

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("График оплаты");
        this.primaryStage.setResizable(false);


        // Parent root = FXMLLoader.load(getClass().getResource("voda_01.fxml"));
        // primaryStage.setTitle("График оплаты");
        //  primaryStage.setResizable(false);
        // primaryStage.setScene(new Scene(root));

        launchView();
    }
    private void launchView() {
//        try{
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("voda_00.fxml"));
//            rootLayout = (AnchorPane) loader.load();
//
//            Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
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
