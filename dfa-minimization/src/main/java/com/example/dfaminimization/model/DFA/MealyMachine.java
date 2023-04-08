package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.MealyState;
import com.example.dfaminimization.model.states.State;

import java.util.ArrayList;
import java.util.HashSet;

public class MealyMachine extends Machine{

    public MealyMachine(HashSet<String> inputAlphabet, HashSet<String> outputAlphabet){
        states = new ArrayList<State>();
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
    }

    public void addStartState(String id) {
        this.startState = new MealyState(id);
        states.add(startState);
    }

    public void addState(String id, String output) {
        states.add(new MealyState(id));
    }

    public void addTransition(String input, String idFrom, String idTo, String output) {
        MealyState stateFrom = (MealyState) findStateById(idFrom);
        MealyState stateTo = (MealyState) findStateById(idTo);

        if(stateFrom == null || stateTo == null) return;

        stateFrom.addTransition(input, stateTo,output);
    }


}
