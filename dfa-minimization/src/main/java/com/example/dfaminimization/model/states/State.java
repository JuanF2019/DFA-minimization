package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.Transition;

import java.util.ArrayList;

public abstract class State {

    String id;

    public ArrayList<Transition> transitions;

    public String getId(){
        return this.id;
    }

}
