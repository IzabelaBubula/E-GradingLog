package com.demo3.fxml_3;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Marks {
    private SimpleStringProperty groupName;
    private SimpleDoubleProperty markValue;

    public Marks(String groupName, double markValue) {
        this.groupName = new SimpleStringProperty(groupName);
        this.markValue = new SimpleDoubleProperty(markValue);
    }

    //getters and setters
    public String getGroupName() {
        return groupName.get();
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }

    public double getMarkValue() {
        return markValue.get();
    }

    public void setMarkValue(int markValue) {
        this.markValue.set(markValue);
    }

}
