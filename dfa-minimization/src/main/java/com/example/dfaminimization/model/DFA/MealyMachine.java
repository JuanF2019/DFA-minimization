package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.MealyState;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a Mealy type automata
 * in which output symbols are associated with transitions.
 * Output depends on the state and transition.
 */
public class MealyMachine extends Machine{

    /**
     * Constructor for a Mealy machine
     * @param inputAlphabet Input Alphabet
     * @param outputAlphabet Output Aphabet
     */
    public MealyMachine(Set<String> inputAlphabet, Set<String> outputAlphabet){
        states = new HashSet<>();
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
    }

    /**
     * Adds the start state of this machine
     * @param id Start state id
     * @param name Start state name
     */
    public void addStartState(String id, String name) {
        this.startState = new MealyState(id,name);
        states.add(startState);
    }

    /**
     * Adds a state to this mealy machine
     * @param id State id
     * @param name State name
     */
    public void addState(String id, String name) {
        states.add(new MealyState(id,name));
    }

    /**
     * Adds a transition to the machine
     * @param input Input symbol that causes the transition
     * @param idFrom Source state id
     * @param idTo End state id
     * @param output Output symbol associated with the transition
     */
    public void addTransition(String input, String idFrom, String idTo, String output) {
        MealyState stateFrom = (MealyState) findStateById(idFrom);
        MealyState stateTo = (MealyState) findStateById(idTo);

        if(stateFrom == null || stateTo == null) return;

        stateFrom.addTransition(input, stateTo, output);
    }


}
