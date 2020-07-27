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
    protected Stage primaryStage;
    protected static final String PROPERTIES_FORM = "propertiesForm";
    protected static final String MAIN_FORM = "mainForm";
    protected static final String PAYMENT_FORM = "paymentForm";
    private Set<FXMLForm> formList = new HashSet<>();

    public UIFactory(ResourceBundle resourceBundle, Stage primaryStage) {
        this.resourceBundle = resourceBundle;
        this.primaryStage = primaryStage;
    }

    private FXMLForm searchFXMLForm(String nameForm) {
        for (FXMLForm form : formList) {
            if (form.getNameForm().equals(nameForm)) {
                return form;
            }
        }
        return null;
    }

    public Node getForm(String nameForm) {
        FXMLForm fxmlForm = searchFXMLForm(nameForm);
        if (fxmlForm != null) {
            return fxmlForm.getNode();
        }
        return null;
    }

    public Object getController(String nameForm) {
        FXMLForm fxmlForm = searchFXMLForm(nameForm);
        if (fxmlForm != null) {
            return fxmlForm.getController();
        }
        return null;
    }

    public void initUI() throws Exception {
        //UIMain loading
        FXMLForm rootForm = loadNode(TableController.getFXMLPath(), resourceBundle, MAIN_FORM);
        primaryStage.setScene(new Scene((Parent) rootForm.getNode()));
        TableController tableController = (TableController) rootForm.getController();
        /*
         * сюда добавляем загрузку второй формы
         */
        FXMLForm paymentForm = loadNode(PaymentController.getFXMLPath(), resourceBundle, PAYMENT_FORM);
        FXMLForm propertiesForm = loadNode(ConnectController.getFXMLPath(), resourceBundle, PROPERTIES_FORM);
        propertiesForm.getNode().setVisible(true);
        primaryStage.setScene(new Scene((Parent) propertiesForm.getNode()));
        primaryStage.show();
        tableController.init(this);
    }

    /**
     * Загружает любую FXML формы
     *
     * @param resourcePath   путь к форме
     * @param resourceBundle ResourceBundle с локализацией формы
     * @return FXMLForm
     * @throws IOException
     */
    private FXMLForm loadNode(String resourcePath, ResourceBundle resourceBundle, String nameForm) throws IOException {
        URL xmlUrl = TableController.class.getResource(resourcePath);
        FXMLLoader loader = new FXMLLoader(xmlUrl, resourceBundle);
        Parent root = loader.load();
        root.setVisible(false);
        FXMLForm form = new FXMLForm(root, loader.getController(), nameForm);

        if (form.getController() instanceof IController) {
            ((IController) form.getController()).init(this);
        }

        formList.add(form);
        return form;
    }

    private class FXMLForm {
        private final Node node;
        private final Object controller;
        private final String nameForm;

        public FXMLForm(final Node node, final Object controller, String nameForm) {
            this.node = node;
            this.controller = controller;
            this.nameForm = nameForm;
        }

        public Node getNode() {
            return node;
        }

        public String getNameForm() {
            return nameForm;
        }

        public Object getController() {
            return controller;
        }
    }
}