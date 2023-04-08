package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.MooreState;
import com.example.dfaminimization.model.states.State;

import java.util.ArrayList;
import java.util.HashSet;


public class MooreMachine extends Machine{

    public MooreMachine(HashSet<String> inputAlphabet, HashSet<String> outputAlphabet){
        states = new ArrayList<State>();
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
    }

    public void addStartState(String id, String output) {
        this.startState = new MooreState(id,output);
        states.add(startState);
    }

    public void addState(String id, String output) {
        states.add(new MooreState(id,output));
    }

    public void addTransition(String input, String idFrom, String idTo) {
        MooreState stateFrom = (MooreState) findStateById(idFrom);
        MooreState stateTo = (MooreState) findStateById(idTo);

        if(stateFrom == null || stateTo == null) return;

        stateFrom.addTransition(input, stateTo);
    }
}
