package controller;

/**
 * Класс содержащий все формы, чтобы нам не приходилось помнить, что за какую форму отвечает
 */
public enum UIForms {
    CONNECTION_FORM(ConnectController.getFXMLPath()), MAIN_FORM(TableController.getFXMLPath()), PAYMENT_FORM(PaymentController.getFXMLPath());

    private final String xmlPath;

    UIForms(final String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getXmlPath() {
        return xmlPath;
    }
}
