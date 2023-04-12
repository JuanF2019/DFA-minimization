package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.State;

public abstract class Transition {
    public State nextState;

    public String toString(){
        return "{nextState=" + "{ id = " + this.nextState.getId() + ", name = " + this.nextState.getName() + "} }";
    }
}
