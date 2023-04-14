package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.MooreState;

import java.util.HashSet;
import java.util.Set;

/**
 *  Class that represents a Moore type automata
 *  in which output symbols are associated with each state (Depend only on the states)
 */
public class MooreMachine extends Machine{

    /**
     * Moore machine constructor
     * @param inputAlphabet Input alphabet
     * @param outputAlphabet Output alphabet
     */
    public MooreMachine(Set<String> inputAlphabet, Set<String> outputAlphabet){
        states = new HashSet<>();
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
    }

    /**
     * Adds the start state
     * @param id Moore state id
     * @param output Output associated with the state
     * @param name State name
     */
    public void addStartState(String id, String output, String name) {
        this.startState = new MooreState(id,output,name);
        this.startState.setName(name);
        states.add(startState);
    }

    /**
     * Adds a state
     * @param id Moore state id
     * @param output Output associated with the state
     * @param name State name
     */
    public void addState(String id, String output, String name) {
        states.add(new MooreState(id,output,name));
    }

    /**
     * Adds a transition to the machine
     * @param input Input symbol that causes the transition
     * @param idFrom Source state id
     * @param idTo End state id
     */
    public void addTransition(String input, String idFrom, String idTo) {
        MooreState stateFrom = (MooreState) findStateById(idFrom);
        MooreState stateTo = (MooreState) findStateById(idTo);

        if(stateFrom == null || stateTo == null) return;

        stateFrom.addTransition(input, stateTo);
    }
}
