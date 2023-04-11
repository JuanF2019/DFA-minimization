package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.MooreState;

public class MooreTransition extends Transition {
    public MooreTransition (MooreState stateTo){
        this.nextState = stateTo;
    }
}
