package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Версия класса с ленивой загрузкой форм, используется в связи с тем, что нам требуется передача информации
 * для инициализации форм. Можно сделать реализацию и другими способами.
 * Класс сохраняет формы в Set или, если ее нет - загрузит новую форму, и сохранит ее в Set.
 * Данная веросия класса не предназначена для многопоточной работы.
 *
 */
public class UIFactory {
    private final ResourceBundle resourceBundle;
    protected Stage primaryStage;
    private final Set<FXMLForm> formList = new HashSet<>();

    public UIFactory(ResourceBundle resourceBundle, Stage primaryStage) {
        this.resourceBundle = resourceBundle;
        this.primaryStage = primaryStage;
    }

    /**
     * Загружает новую форму если ее нет в Set или создает новую.
     *
     * @param form {@link UIForms}
     * @return {@link FXMLForm}
     * @exception IOException при ошибке загрузки
     */
    public FXMLForm getFXMLForm(UIForms form) throws IOException {

        /*
         * Ищем форму в хранилище
         */
        FXMLForm resultFXMLForm = formList.stream()
                .filter(x->x.getUiForm().equals(form))
                .findFirst()
                .orElse(null);

        return  resultFXMLForm != null ? resultFXMLForm: loadNode(form);
    }


    /**
     * Получаем объект Node для выбраной формы
     * @param form {@link UIForms}
     * @return {@link Node}
     * @exception IOException при ошибке загрузки
     */
    public Node getForm(UIForms form) throws IOException {
        return getFXMLForm(form).getNode();
    }

    /**
     * Получаем объект Controller для выбраной формы
     *
     * @param form {@link UIForms}
     * @return Controller
     * @exception IOException при ошибке загрузки
     */
    public Object getController(UIForms form) throws IOException {
        return getFXMLForm(form).getController();
    }

    /**
     * Загружает любую FXML форму
     *
     * @param forms   {@link UIForms}
     * @return FXMLForm
     * @throws IOException
     */
    private FXMLForm loadNode(UIForms forms) throws IOException {

        URL xmlUrl = TableController.class.getResource(forms.getXmlPath());
        FXMLLoader loader = new FXMLLoader(xmlUrl, resourceBundle);
        Parent root = loader.load();
        root.setVisible(false);
        FXMLForm form = new FXMLForm(root, loader.getController(), forms);

        if (form.getController() instanceof IController) {
            ((IController) form.getController()).init(this);
        }

        primaryStage.setScene(new Scene(root));
        formList.add(form);
        return form;
    }

    /**
     * Класс, из которого можно достать controller для любой формы
     */
    private class FXMLForm {
        private final Node node;
        private final Object controller;
        private final UIForms uiForm;

        public FXMLForm(Node node, Object controller, UIForms uiForm) {
            this.node = node;
            this.controller = controller;
            this.uiForm = uiForm;
        }

        public Node getNode() {
            return node;
        }

        public Object getController() {
            return controller;
        }

        /**
         * Возвращает значение Enum для формы, которое используется вместо имени
         * @return {@link UIForms}
         */
        public UIForms getUiForm() {
            return uiForm;
        }


    }
}