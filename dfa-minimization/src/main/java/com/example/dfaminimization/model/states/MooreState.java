package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.MooreTransition;

public class MooreState extends State{

    String output;

    public MooreState(String id, String output) {
        this.id = id;
        this.output = output;
    }

    public void addTransition(String input, MooreState stateTo) {
        transitions.add(new MooreTransition(input,stateTo));
    }
}
