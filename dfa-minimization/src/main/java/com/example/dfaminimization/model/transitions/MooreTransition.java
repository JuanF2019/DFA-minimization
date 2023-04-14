package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.MooreState;

/**
 * Class that holds the information of a transition from a Moore machine
 */
public class MooreTransition extends Transition {

    /**
     * Constructor for a moore transition
     * @param stateTo State associated with a transition
     */
    public MooreTransition (MooreState stateTo){
        this.nextState = stateTo;
    }

    /**
     *
     * @return Moore transition as a String;
     */
    public String toString(){
        return "{nextState=" + "{ id = " + this.nextState.getId() + ", name = " + this.nextState.getName() + " , output = " + ((MooreState)this.nextState).getOutput() + "} }";
    }
}
