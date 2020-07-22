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

    private Set<FXMLForm> formList = new HashSet<>();

    public UIFactory(ResourceBundle resourceBundle, Stage primaryStage) {
        this.resourceBundle = resourceBundle;
        this.primaryStage = primaryStage;
    }
    public FXMLForm getForm(String nameForm) {
        Iterator it = formList.iterator();
        while (it.hasNext()){
           FXMLForm currentForm = (FXMLForm) it.next();
           if(currentForm.getNameForm().equals(nameForm)){
               return currentForm;
           }
        }
        return null;
    }

    public Object getController(String nameForm) {
        Iterator it = formList.iterator();
        while (it.hasNext()){
            FXMLForm currentForm = (FXMLForm)it.next();
            if(currentForm.getNameForm().equals(nameForm)){
                return currentForm.getController();
            }
        }
        return null;
    }

    public void initUI() throws Exception {

        //UIMain loading
        FXMLForm rootForm = loadNode(TableController.getFXMLPath(),resourceBundle,"mainForm");
        primaryStage.setScene(new Scene((Parent)rootForm.getNode()));
        TableController tableController = (TableController) rootForm.getController();

        /*
         * сюда добавляем загрузку второй формы
         */
        FXMLForm paymentForm = loadNode(PaymentController.getFXMLPath(),resourceBundle, "paymentForm");
        //PaymentController paymentController = (PaymentController) paymentForm.getController();

        FXMLForm propertiesForm = loadNode(ConnectController.getFXMLPath(),resourceBundle,"propertiesForm");

        formList.add(rootForm);
        formList.add(paymentForm);
        formList.add(propertiesForm);

        primaryStage.show();
        rootForm.getNode().setVisible(true);
        tableController.init(this);
    }

    /**
     * Загружает любую FXML формы
     *
     * @param resourcePath путь к форме
     * @param resourceBundle ResourceBundle с локализацией формы
     * @return FXMLForm
     * @throws IOException
     */
    private FXMLForm loadNode(String resourcePath, ResourceBundle resourceBundle,String nameForm) throws IOException {
        URL xmlUrl = TableController.class.getResource(resourcePath);
        FXMLLoader loader = new FXMLLoader(xmlUrl,resourceBundle);
        Parent root = loader.load();
        root.setVisible(false);
        return new FXMLForm(root,loader.getController(),nameForm);
    }


    private class FXMLForm {
        private final Node node;
        private final Object controller;
        private final String nameForm;

        public FXMLForm(final Node node,final Object controller, String nameForm) {
            this.node = node;
            this.controller = controller;
            this.nameForm = nameForm;
        }

        public Node getNode() {
            return node;
        }
        public String getNameForm(){
            return nameForm;
        }
        public Object getController() {
            return controller;
        }
    }

}