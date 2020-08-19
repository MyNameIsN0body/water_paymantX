import controller.UIFactory;
import model.UIForms;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    private AnchorPane rootLayout;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("uiLocale", Locale.getDefault());

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("График оплаты");
        primaryStage.setResizable(false);


        // Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        // primaryStage.setTitle("График оплаты");
        //  primaryStage.setResizable(false);
        // primaryStage.setScene(new Scene(root));

        launchView(primaryStage);
    }

    /**
     * Загружает интерфейс. Лучше использовать локальные переменные, в случае последовательной работы с ними.
     *
     * @param primaryStage Stage
     */
    private void launchView(Stage primaryStage) {
//        try{
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("ConnectionForm.fxml"));
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
           factory.getForm(UIForms.CONNECTION_FORM)
                   .setVisible(true);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}
