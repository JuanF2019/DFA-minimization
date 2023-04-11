package com.example.dfaminimization.ui;

import com.example.dfaminimization.model.DFA.Machine;
import com.example.dfaminimization.model.DFA.MealyMachine;
import com.example.dfaminimization.model.DFA.MooreMachine;
import com.example.dfaminimization.model.states.State;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class MainWindowController {

    Machine originalMachine;

    Machine minimizedMachine;

    public void initialize(){


    }

    @FXML
    private Button clearButton;

    @FXML
    private Button createMachineButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField inputAlphabetTextField;

    @FXML
    private ToggleGroup machineSelection;

    @FXML
    private RadioButton mealyRadioButton;


    @FXML
    private TableView<RawMooreState> minimizedMachineTable;

    @FXML
    private RadioButton mooreRadioButton;

    @FXML
    private TextField numberOfStatesTextField;

    @FXML
    private TableView<RawState> originalMachineTable;

    @FXML
    private TextField outputAlphabetTextField;


    @FXML
    void onClearButtonClick(ActionEvent event) {

    }

    @FXML
    void onMinimizeButtonClick(ActionEvent event) {
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {

        int numberOfStates = Integer.parseInt(numberOfStatesTextField.getText());
        String[] inputAlphabet = inputAlphabetTextField.getText().split(",");
        String[] outputAlphabet = outputAlphabetTextField.getText().split(",");

        ObservableList<RawState> rawStates = FXCollections.observableArrayList();
        char stateCounter = 65;

        boolean isMoore = mooreRadioButton.isSelected(); //To prevent bugs when changing selection mid-execution

        for(int i = 0; i < numberOfStates;i++){
            rawStates.add(isMoore?
                    new RawMooreState(Character.toString(stateCounter++)) :
                    new RawMealyState(Character.toString(stateCounter++))
            );
        }

        originalMachineTable.setItems(rawStates);

        //Initializes the combo boxes for each state and input value
        //And initializes the combo boxes for the outputs
        for(RawState rawState: rawStates){
            for(String inputSymbol : inputAlphabet){
                rawState.addEmptyTransitionWithOptions(inputSymbol, rawStates);

                if(!isMoore) ((RawMealyState) rawState).addOutputOptionsForSymbol(inputSymbol, outputAlphabet);
            }

            if(isMoore) ((RawMooreState) rawState).addOutputOptions(outputAlphabet);

        }

        //======================================================================================================

        ArrayList<TableColumn<RawMooreState,ComboBox<RawMooreState>>> transitionsSubColumns = new ArrayList<>();

        for(int i = 0; i < inputAlphabet.length; i++){

            TableColumn<RawMooreState,ComboBox<RawMooreState>> newTableColumn = new TableColumn<>(inputAlphabet[i]);
            int finalI = i;
            newTableColumn.setCellValueFactory(cellDataFeatures -> {


                ComboBox<RawMooreState> selector = cellDataFeatures.getValue().getTransitions().get(inputAlphabet[finalI]);

                return new SimpleObjectProperty<>(selector);


            } );

            transitionsSubColumns.add(newTableColumn);
        }








        TableColumn<RawMooreState, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<RawMooreState, String> outputColumn = new TableColumn<>("Output");
        outputColumn.setCellValueFactory(new PropertyValueFactory<>("output"));

        originalMachineTable.getColumns().addAll(stateColumn,outputColumn);
        originalMachineTable.getColumns().addAll(transitionsSubColumns);






    }

    @FXML
    void onCreateMachineButtonClick(ActionEvent event) {

    }

}
