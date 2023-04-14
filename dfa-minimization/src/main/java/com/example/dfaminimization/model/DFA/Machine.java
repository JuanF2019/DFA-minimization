package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.State;

import java.util.Set;

/**
 * Abstract class that holds the information of a
 * deterministic finite automata (machine) (DFA)
 */
public abstract class Machine {

    /**
     * Machine input alphabet
     */
    public Set<String> inputAlphabet;

    /**
     * Machine output alphabet
     */
    public Set<String> outputAlphabet;

    /**
     * Machine states
     */
    public Set<State> states;

    /**
     * Machine start state
     */
    public State startState;

    /**
     * Finds a state in the set of states given a state id
     * @param id State id to find
     * @return A reference to the state if its found, null if the state is not found
     */
    public State findStateById(String id){
        for (State s: states){
           if (s.getId().equals(id)) return s;
        }
        return null;
    }


}
