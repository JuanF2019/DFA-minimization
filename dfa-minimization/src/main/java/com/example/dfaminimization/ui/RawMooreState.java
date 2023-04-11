package com.example.dfaminimization.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.HashMap;


public class RawMooreState extends RawState{

    private final ComboBox<String> output;

    public RawMooreState(String name){
        super(name);
        transitions = new HashMap<>();
        output = new ComboBox<>();
    }

    public ComboBox<String> getOutput() {
        return output;
    }

    public void addOutputOptions(String[] outputAlphabet){ output.getItems().addAll(outputAlphabet); }

}
