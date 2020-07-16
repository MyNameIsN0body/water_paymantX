package controller;

import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * TODO см. комментарии
 */
public class UIFactory {

    private Stage primaryStage;

    /**
     * Конструктор для фабрики
     * @param primaryStage объект получаемы из метода start класса Application
     */
    public UIFactory(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /*
     * По форме voda_00 - надо добавить еще поля имя базы данных, пользователь и пароль
     * Добавить контроллер (пустой и дефолтный) для формы.
     * И я бы рекомендовал бы как-то назвать форму (и соответсвенно контроллер), чтобы было понятно, что она делает.
     * Например DatabaseLoginForm
     */



    /*
     * Начнем проектирование UIFactory. Даю общие указания, если не получится выполнить, все равно пиши свой вариант,
     * я потом подправлю.
     * Наша задача - получить следущее:
     * 1. Должны быть переменные/переменная, в которых мы храним FXMLForm (класс я предоставил ниже) для всех форм, которыми мы будем пользоваться
     * 2. Должен быть метод, который загружает формы в память, устанавливает их невидимыми и создает и сохраняет объект FXMLForm
     * 3. Доллжен быть геттер, который по названию формы вернет нам контроллер
     * 3. Доллжен быть геттер, который по названию формы вернет нам Node (форму)
     */

    /**
     * Класс описания формы
     */
    private class FXMLForm {

        private final String FormName;
        private final Node node;
        private final Object controller;

        /**
         * Контруктор для класса описания формы
         *
         * @param formName
         * @param node форма как объект Node (получается из FXMLLoader метода load() )
         * @param controller ссылка на класс контроллера (берется из  FXMLLoader метода getController())
         */
        public FXMLForm(String formName, final Node node, final Object controller) {
            FormName = formName;
            this.node = node;
            this.controller = controller;
        }

        public Node getNode() {
            return node;
        }

        public Object getController() {
            return controller;
        }

        public String getFormName() {
            return FormName;
        }
    }
}
