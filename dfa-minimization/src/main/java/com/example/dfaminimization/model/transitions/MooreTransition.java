package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.MooreState;

public class MooreTransition extends Transition {
    public MooreTransition (MooreState stateTo){
        this.nextState = stateTo;
    }

    public String toString(){
        return "{nextState=" + "{ id = " + this.nextState.getId() + ", name = " + this.nextState.getName() + " , output = " + ((MooreState)this.nextState).getOutput() + "} }";
    }
}
