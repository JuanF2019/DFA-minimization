package com.example.dfaminimization.model.DFA;

import com.example.dfaminimization.model.states.MealyState;
import com.example.dfaminimization.model.states.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MealyMachine extends Machine{

    public MealyMachine(Set<String> inputAlphabet, Set<String> outputAlphabet){
        states = new HashSet<>();
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
    }

    public void addStartState(String id, String name) {
        this.startState = new MealyState(id,name);
        states.add(startState);
    }

    public void addState(String id, String name) {
        states.add(new MealyState(id,name));
    }

    public void addTransition(String input, String idFrom, String idTo, String output) {
        MealyState stateFrom = (MealyState) findStateById(idFrom);
        MealyState stateTo = (MealyState) findStateById(idTo);

        if(stateFrom == null || stateTo == null) return;

        stateFrom.addTransition(input, stateTo,output);
    }


}
