package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.MooreState;

import java.util.HashSet;
import java.util.Set;


public class MooreMachine extends Machine{

    public MooreMachine(Set<String> inputAlphabet, Set<String> outputAlphabet){
        states = new HashSet<>();
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
    }

    public void addStartState(String id, String output, String name) {
        this.startState = new MooreState(id,output,name);
        this.startState.setName(name);
        states.add(startState);
    }

    public void addState(String id, String output, String name) {
        states.add(new MooreState(id,output,name));
    }

    public void addTransition(String input, String idFrom, String idTo) {
        MooreState stateFrom = (MooreState) findStateById(idFrom);
        MooreState stateTo = (MooreState) findStateById(idTo);

        if(stateFrom == null || stateTo == null) return;

        stateFrom.addTransition(input, stateTo);
    }
}
