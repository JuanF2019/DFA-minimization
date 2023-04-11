package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.MooreTransition;

public class MooreState extends State{

    String output;

    public MooreState(String id, String output, String name) {
        this.id = id;
        this.output = output;
        this.name = name;
    }

    public void addTransition(String input, MooreState stateTo) {
        transitions.put(input, new MooreTransition(stateTo));
    }

    public String getOutput() {
        return output;
    }
}
