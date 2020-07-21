package controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelTable;


import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class UIFactory {
    private final ResourceBundle resourceBundle;
    Stage primaryStage;

    public UIFactory(ResourceBundle resourceBundle, Stage primaryStage) {
        this.resourceBundle = resourceBundle;
        this.primaryStage = primaryStage;
    }

    public void initUI() throws Exception {

        //UIMain loading
        FXMLForm rootForm = loadNode(TableController.getFXMLPath(),resourceBundle);
        primaryStage.setScene(new Scene((Parent)rootForm.getNode()));
        TableController tableController = (TableController) rootForm.getController();

        primaryStage.show();
        rootForm.getNode().setVisible(true);
        tableController.init(primaryStage);

    }

    /**
     * Загружает любую FXML формы
     *
     * @param resourcePath путь к форме
     * @param resourceBundle ResourceBundle с локализацией формы
     * @return FXMLForm
     * @throws IOException
     */
    private FXMLForm loadNode(String resourcePath, ResourceBundle resourceBundle) throws IOException {
        URL xmlUrl = TableController.class.getResource(resourcePath);
        FXMLLoader loader = new FXMLLoader(xmlUrl,resourceBundle);
        Parent root = loader.load();
        root.setVisible(false);
        return new FXMLForm(root,loader.getController());
    }


    private class FXMLForm {
        private final Node node;
        private final Object controller;

        public FXMLForm(final Node node,final Object controller) {
            this.node = node;
            this.controller = controller;
        }

        public Node getNode() {
            return node;
        }

        public Object getController() {
            return controller;
        }
    }

}