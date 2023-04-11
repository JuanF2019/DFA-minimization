package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.State;

import java.util.Set;

public abstract class Machine {

    public Set<String> inputAlphabet;
    public Set<String> outputAlphabet;

    public Set<State> states;
    public State startState;

    public State findStateById(String id){
        for (State s: states){
           if (s.getId().equals(id)) return s;
        }
        return null;
    }


}
