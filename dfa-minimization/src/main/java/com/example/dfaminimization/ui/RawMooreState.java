package com.example.dfaminimization.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.HashMap;

/**
 * A container used to save and display state information on the user interface table for a Moore State
 */
public class RawMooreState extends RawState{

    /**
     * The state output stored on a combo box that holds all the possible values the output can be
     */
    private final ComboBox<String> output;

    /**
     * State constructor
     * @param name State name
     */
    public RawMooreState(String name){
        super(name);
        transitions = new HashMap<>();
        output = new ComboBox<>();
    }

    /**
     *
     * @return Reference to the ComboBox object holding the output information.
     */
    public ComboBox<String> getOutput() {
        return output;
    }

    /**
     * Initializes de output combobox
     * @param outputAlphabet Output alphabet of the machine, as specified by the user
     */
    public void addOutputOptions(String[] outputAlphabet){
        output.getItems().addAll(outputAlphabet);
        output.setValue(output.getItems().get(0));
    }

}
