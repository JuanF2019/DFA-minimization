package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.State;

/**
 * Abstract class that holds the information of a DFA transition
 */
public abstract class Transition {

    /**
     * State associated with this transition
     */
    public State nextState;

    /**
     *
     * @return This transition as a String
     */
    public String toString(){
        return "{nextState=" + "{ id = " + this.nextState.getId() + ", name = " + this.nextState.getName() + "} }";
    }
}
