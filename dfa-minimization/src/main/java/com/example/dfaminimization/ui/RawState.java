package com.example.dfaminimization.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.HashMap;

public abstract class RawState {

    protected String name;

    protected HashMap<String, ComboBox<RawState>> transitions;

    public RawState (String name){
        this.name = name;
    }

    public HashMap<String, ComboBox<RawState>> getTransitions() { return transitions; }

    public void addEmptyTransitionWithOptions(String inputSymbol, ObservableList<RawState> states){
        transitions.put(inputSymbol, new ComboBox<>(states));
    }

    public String getName(){ return name; }

    public SimpleStringProperty nameProperty() { return new SimpleStringProperty(this.name);}

    public String toString(){ return this.name; }

}
