package com.example.dfaminimization.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.HashMap;

/**
 * A container used to save and display state information on the user interface table
 */
public abstract class RawState {

    /**
     * State name
     */
    protected String name;

    /**
     * State transitions, mapp input values to a ComboBox that it's selection represents the transition.
     */
    protected HashMap<String, ComboBox<RawState>> transitions;

    /**
     * RawState constructor
     * @param name State name
     */
    public RawState (String name){
        this.name = name;
    }

    /**
     *
     * @return Transitions of the state
     */
    public HashMap<String, ComboBox<RawState>> getTransitions() { return transitions; }

    /**
     * Initializes the transitions map and combo box for each possible transition
     * @param inputSymbol Input symbol
     * @param states List of states
     */
    public void addEmptyTransitionWithOptions(String inputSymbol, ObservableList<RawState> states){
        ComboBox<RawState> stateOptions = new ComboBox<>(states);
        stateOptions.setValue(null);
        transitions.put(inputSymbol, stateOptions);
    }

    /**
     *
     * @return The state's name
     */
    public String getName(){ return name; }

    /**
     *
     * @return This state's name wrapped in a SimpleStringProperty Object
     */
    public SimpleStringProperty nameProperty() { return new SimpleStringProperty(this.name);}

    /**
     * Returns the state represented as a String
     * @return State name, representing the state
     */
    public String toString(){ return this.name; }

}
