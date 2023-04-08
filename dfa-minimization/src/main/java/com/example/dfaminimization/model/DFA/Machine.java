package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.State;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Machine {

    HashSet<String> inputAlphabet;
    HashSet<String> outputAlphabet;
    public ArrayList<State> states;
    public State startState;

    State findStateById(String id){
        for (State s: states){
           if (s.getId().equals(id)) return s;
        }
        return null;
    }


}
