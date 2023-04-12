package com.example.dfaminimization.ui;

import com.example.dfaminimization.model.DFA.Machine;
import com.example.dfaminimization.model.DFA.MealyMachine;
import com.example.dfaminimization.model.DFA.MooreMachine;
import com.example.dfaminimization.model.DFAminimizer.Minimizer;
import com.example.dfaminimization.model.states.MealyState;
import com.example.dfaminimization.model.states.MooreState;
import com.example.dfaminimization.model.states.State;
import com.example.dfaminimization.model.transitions.MealyTransition;
import com.example.dfaminimization.model.transitions.Transition;
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

import java.util.*;

public class MainWindowController {

    Machine originalMachine;

    String[] inputAlphabet;

    String[] outputAlphabet;

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
    private TableView<State> minimizedMachineTable;

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

        testMachine();



    }

    private Machine testMachine2(){
        Set<String> inputAlphabet = new HashSet<>();
        inputAlphabet.add("0");
        inputAlphabet.add("1");

        Set<String> outputAlphabet = new HashSet<>();
        inputAlphabet.add("0");
        inputAlphabet.add("1");

        MealyMachine machine = new MealyMachine(inputAlphabet,outputAlphabet);

        machine.addStartState("A","A");
        machine.addState("B","B");
        machine.addState("C","C");
        machine.addState("D","D");
        machine.addState("E","E");
        machine.addState("F","F");
        machine.addState("G","G");

        machine.addTransition("0","A","F","0");
        machine.addTransition("1","A","B","1");
        machine.addTransition("0","B","G","0");
        machine.addTransition("1","B","A","1");
        machine.addTransition("0","C","B","0");
        machine.addTransition("1","C","C","1");
        machine.addTransition("0","D","C","0");
        machine.addTransition("1","D","B","1");
        machine.addTransition("0","E","D","0");
        machine.addTransition("1","E","A","1");
        machine.addTransition("0","F","E","1");
        machine.addTransition("1","F","F","1");
        machine.addTransition("0","G","E","1");
        machine.addTransition("1","G","G","1");

        return (machine);
    }

    private Machine testMachine(){

        Set<String> inputAlphabet = new HashSet<>();
        inputAlphabet.add("0");
        inputAlphabet.add("1");

        Set<String> outputAlphabet = new HashSet<>();
        inputAlphabet.add("0");
        inputAlphabet.add("1");

        MooreMachine machine = new MooreMachine(inputAlphabet,outputAlphabet);

        machine.addStartState("q0","0","q0");
        machine.addState("q1","0","q1");
        machine.addState("q2","0","q2");
        machine.addState("q3","1","q3");
        machine.addState("q4","0","q4");
        machine.addState("q5","1","q5");

        machine.addTransition("0","q0","q1");
        machine.addTransition("1","q0","q3");
        machine.addTransition("0","q1","q0");
        machine.addTransition("1","q1","q3");
        machine.addTransition("0","q2","q1");
        machine.addTransition("1","q2","q4");
        machine.addTransition("0","q3","q5");
        machine.addTransition("1","q3","q5");
        machine.addTransition("0","q4","q3");
        machine.addTransition("1","q4","q3");
        machine.addTransition("0","q5","q5");
        machine.addTransition("1","q5","q5");



        return (machine);
    }

    @FXML
    void onMinimizeButtonClick(ActionEvent event) throws InterruptedException {

        minimizedMachineTable.getItems().clear();

        minimizedMachineTable.getColumns().clear();

        this.minimizedMachine = Minimizer.minimize(this.originalMachine);

        //Create list of states, that contain the information of each state in the machine
        ObservableList<State> states = FXCollections.observableArrayList(this.minimizedMachine.states);

        boolean isMoore = this.minimizedMachine instanceof MooreMachine;

        minimizedMachineTable.setItems(states);

        TableColumn<State, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(cellDataFeatures -> {
                return new SimpleStringProperty(cellDataFeatures.getValue().getName());
        } );
        minimizedMachineTable.getColumns().add(stateColumn);

        if(isMoore){

            //Adds the columns corresponding to the input symbols the machine can receive
            for(int i = 0; i < inputAlphabet.length; i++){

                TableColumn<State,String> newInputSymbolColumn = new TableColumn<>(inputAlphabet[i]);
                int finalI = i;

                newInputSymbolColumn.setCellValueFactory(cellDataFeatures -> {
                    Transition transition = cellDataFeatures.getValue().getTransitions().get(inputAlphabet[finalI]);

                    if(transition != null)
                        return new SimpleStringProperty(transition.nextState.getName());
                    else
                        return new SimpleStringProperty("");
                } );

                minimizedMachineTable.getColumns().add(newInputSymbolColumn);
            }

            //Adds moore machine output column
            TableColumn<State, String> outputColumn = new TableColumn<>("Output");

            outputColumn.setCellValueFactory(cellDataFeatures -> {
                MooreState state = (MooreState) cellDataFeatures.getValue();
                return new SimpleStringProperty(state.getOutput());
            });

            minimizedMachineTable.getColumns().add(outputColumn);

        }
        else{

            //Adds the columns corresponding to the input symbols the machine can receive and the outputs for a mealy machine
            for(int i = 0 ; i < inputAlphabet.length ; i++){

                TableColumn<State,String> newInputSymbolColumn = new TableColumn<>(inputAlphabet[i]);
                int finalI = i;

                newInputSymbolColumn.setCellValueFactory(cellDataFeatures -> {
                    Transition transition = cellDataFeatures.getValue().getTransitions().get(inputAlphabet[finalI]);

                    if(transition != null)
                        return new SimpleStringProperty(transition.nextState.getName());
                    else
                        return new SimpleStringProperty("");
                } );

                TableColumn<State,String> newInputSymbolOutputColumn = new TableColumn<>(inputAlphabet[i]+"-output");

                newInputSymbolOutputColumn.setCellValueFactory(cellDataFeatures -> {

                    MealyTransition transition = (MealyTransition) cellDataFeatures.getValue().getTransitions().get(inputAlphabet[finalI]);

                    if(transition != null)
                        return new SimpleStringProperty(transition.getOutput());
                    else
                        return new SimpleStringProperty("");


                } );

                minimizedMachineTable.getColumns().addAll(newInputSymbolColumn,newInputSymbolOutputColumn);

            }
        }



    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {

        originalMachineTable.getColumns().clear();
        originalMachineTable.getItems().clear();

        //Obtain machine information from user

        int numberOfStates = Integer.parseInt(numberOfStatesTextField.getText());
        inputAlphabet = inputAlphabetTextField.getText().split(",");
        outputAlphabet = outputAlphabetTextField.getText().split(",");

        //Create list of raw states, that contain the information of each state in the machine
        ObservableList<RawState> rawStates = FXCollections.observableArrayList();
        char stateCounter = 65;

        boolean isMoore = mooreRadioButton.isSelected(); //To prevent bugs when changing selection mid-execution

        for(int i = 0; i < numberOfStates ; i++){
            rawStates.add(isMoore?
                    new RawMooreState(Character.toString(stateCounter++)) :
                    new RawMealyState(Character.toString(stateCounter++))
            );
        }

        originalMachineTable.setItems(rawStates);

        //Initializes the combo boxes for each state and input value
        //And initializes the combo boxes for the outputs
        //Combo boxes serve as selectors for nextStates and output in transitions/states

        for(RawState rawState: rawStates){
            for(String inputSymbol : inputAlphabet){
                rawState.addEmptyTransitionWithOptions(inputSymbol, rawStates);

                if(!isMoore) ((RawMealyState) rawState).addOutputOptionsForSymbol(inputSymbol, outputAlphabet);
            }

            if(isMoore) ((RawMooreState) rawState).addOutputOptions(outputAlphabet);

        }

        //Creates and adds the state columns to the table
        //The column will show the state's name

        TableColumn<RawState, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        originalMachineTable.getColumns().add(stateColumn);

        if(isMoore){

            //Adds the columns corresponding to the input symbols the machine can receive
            for(int i = 0; i < inputAlphabet.length; i++){

                TableColumn<RawState,ComboBox<RawState>> newInputSymbolColumn = new TableColumn<>(inputAlphabet[i]);
                int finalI = i;

                newInputSymbolColumn.setCellValueFactory(cellDataFeatures -> {
                    ComboBox<RawState> selector = cellDataFeatures.getValue().getTransitions().get(inputAlphabet[finalI]);
                    return new SimpleObjectProperty<>(selector);
                } );

                originalMachineTable.getColumns().add(newInputSymbolColumn);
            }

            //Adds moore machine output column
            TableColumn<RawState, ComboBox<String>> outputColumn = new TableColumn<>("Output");

            outputColumn.setCellValueFactory(cellDataFeatures -> {
                RawMooreState state = (RawMooreState) cellDataFeatures.getValue();
                return new SimpleObjectProperty<>(state.getOutput());
            });

            originalMachineTable.getColumns().add(outputColumn);

        }
        else{

            //Adds the columns corresponding to the input symbols the machine can receive and the outputs for a mealy machine
            for(int i = 0 ; i < inputAlphabet.length ; i++){

                TableColumn<RawState,ComboBox<RawState>> newInputSymbolColumn = new TableColumn<>(inputAlphabet[i]);
                int finalI = i;

                newInputSymbolColumn.setCellValueFactory(cellDataFeatures -> {
                    ComboBox<RawState> selector = cellDataFeatures.getValue().getTransitions().get(inputAlphabet[finalI]);
                    return new SimpleObjectProperty<>(selector);
                } );

                TableColumn<RawState,ComboBox<String>> newInputSymbolOutputColumn = new TableColumn<>(inputAlphabet[i]+"-output");

                newInputSymbolOutputColumn.setCellValueFactory(cellDataFeatures -> {
                    RawMealyState mealyState = (RawMealyState) cellDataFeatures.getValue();
                    ComboBox<String> selector = mealyState.getOutputs().get(inputAlphabet[finalI]);
                    return new SimpleObjectProperty<>(selector);
                } );

                originalMachineTable.getColumns().addAll(newInputSymbolColumn,newInputSymbolOutputColumn);

            }
        }
    }

    //This method takes the value selected by the user for the states transitions and outputs
    //And converts it to an instance of the corresponding type of Machine
    @FXML
    void onCreateMachineButtonClick(ActionEvent event) {

        this.minimizeButton.setDisable(false);
        //Assumes the original table view items list is filled

        ObservableList<RawState> rawStates = originalMachineTable.getItems();

        Set<String> inputAlphabetSet = new HashSet<>(Arrays.asList(inputAlphabet));
        Set<String> outputAlphabetSet = new HashSet<>(Arrays.asList(outputAlphabet));

        if(rawStates.get(0) instanceof RawMooreState){
            this.originalMachine = new MooreMachine(inputAlphabetSet,outputAlphabetSet);

            MooreMachine mooreMachine = (MooreMachine) originalMachine;

            for(RawState state : rawStates){
                RawMooreState mooreState = (RawMooreState) state;
                if(state.getName().equals("A")){
                    mooreMachine.addStartState("A",mooreState.getOutput().getValue(),state.getName());
                    continue;
                }
                mooreMachine.addState(state.getName(),mooreState.getOutput().getValue(),state.getName());
            }

            for(RawState state : rawStates){
                for(String inputSymbol : inputAlphabet){

                    RawState nextRawState = state.getTransitions().get(inputSymbol).getValue();

                    if(nextRawState != null){
                        mooreMachine.addTransition(inputSymbol, state.getName(), nextRawState.getName());
                    }
                }
            }
        }
        else{

            this.originalMachine = new MealyMachine(inputAlphabetSet,outputAlphabetSet);

            MealyMachine mealyMachine = (MealyMachine) originalMachine;

            for(RawState state : rawStates){
                RawMealyState mealyState = (RawMealyState) state;
                if(state.getName().equals("A")){
                    mealyMachine.addStartState("A",state.getName());
                    continue;
                }
                mealyMachine.addState(state.getName(), state.getName());
            }

            for(RawState state : rawStates){
                for(String inputSymbol : inputAlphabet){

                    RawMealyState mealyState = (RawMealyState) state;

                    RawState nextRawState = state.getTransitions().get(inputSymbol).getValue();

                    String transitionOutput = mealyState.getOutputs().get(inputSymbol).getValue();

                    if(nextRawState != null){
                        mealyMachine.addTransition(inputSymbol, state.getName(), nextRawState.getName(),transitionOutput);
                    }
                }
            }
        }
    }
}
