package model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class TableItem {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty fio = new SimpleStringProperty();
    private final SimpleDoubleProperty balance = new SimpleDoubleProperty();


    public void setUser_id(final Integer id) {
        this.id.set(id);
    }

    public void setFio(final String fio) {
        this.fio.set(fio);
    }

    public void setBalance(final double balance) {
        this.balance.set(balance);
    }

    public Integer getId() {
        return id.getValue();
    }

    public String getFio() {
        return fio.getValue();
    }

    public Double getBalance() {
        return balance.getValue();
    }

    public Observable getStringPropertyId() {
        return id;
    }
    public Observable getStringPropertyFIO() {
        return fio;
    }
    public Observable getDoublePropertyBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableItem that = (TableItem) o;
        return Objects.equals(id.getValue(), that.id.getValue()) &&
                Objects.equals(fio.getValue(), that.fio.getValue()) &&
                Objects.equals(balance.getValue(), that.balance.getValue());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id.getValue(), fio.getValue(), balance.getValue());
    }

}
