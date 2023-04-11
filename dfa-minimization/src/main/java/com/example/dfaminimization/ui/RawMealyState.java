package com.example.dfaminimization.ui;

import javafx.scene.control.ComboBox;
import java.util.HashMap;

public class RawMealyState extends RawState{

    private final HashMap<String,ComboBox<String>> outputs;

    public RawMealyState(String name){
        super(name);
        transitions = new HashMap<>();
        outputs = new HashMap<>();
    }

    public void addOutputOptionsForSymbol(String inputSymbol, String[] outputAlphabet){

        ComboBox<String> selector = new ComboBox<>();
        selector.getItems().addAll(outputAlphabet);
        this.outputs.put(inputSymbol, selector);

    }

    public HashMap<String,ComboBox<String>> getOutputs() {
        return outputs;
    }
}
