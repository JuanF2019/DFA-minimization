package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.MooreState;

public class MooreTransition extends Transition {
    public MooreTransition (String input, MooreState stateTo){
        this.input = input;
        this.nextState = stateTo;
    }
}
