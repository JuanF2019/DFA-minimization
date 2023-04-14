package com.example.dfaminimization.ui;

import javafx.scene.control.ComboBox;
import java.util.HashMap;

/**
 * A container used to save and display state information on the user interface table of a Mealy State
 */
public class RawMealyState extends RawState{

    /**
     * Hashmap object that maps input values to the corresponding ComboBox holding the output values
     */
    private final HashMap<String,ComboBox<String>> outputs;

    /**
     * State constructor
     * @param name State name
     */
    public RawMealyState(String name){
        super(name);
        transitions = new HashMap<>();
        outputs = new HashMap<>();
    }

    /**
     * Intializes the combobox for the outputs given an input symbol
     * @param inputSymbol Input symbol
     * @param outputAlphabet Output alphabet of the machine
     */
    public void addOutputOptionsForSymbol(String inputSymbol, String[] outputAlphabet){

        ComboBox<String> selector = new ComboBox<>();
        selector.getItems().addAll(outputAlphabet);
        this.outputs.put(inputSymbol, selector);

    }

    /**
     *
     * @return The map holding the outputs for each input symbol
     */
    public HashMap<String,ComboBox<String>> getOutputs() {
        return outputs;
    }
}
