package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.MooreTransition;

/**
 * Class that holds the information of a state from a Moore machine
 */
public class MooreState extends State{

    /**
     * This state output
     */
    String output;

    /**
     * This state constructur
     * @param id state id
     * @param output this state output
     * @param name this state name
     */
    public MooreState(String id, String output, String name) {
        super(id,name);
        this.output = output;
    }

    /**
     * Adds a transition
     * @param input Input symbol
     * @param stateTo State that corresponds to this transition
     */
    public void addTransition(String input, MooreState stateTo) {
        transitions.put(input, new MooreTransition(stateTo));
    }

    /**
     *
     * @return This state output
     */
    public String getOutput() {
        return output;
    }

    /**
     *
     * @return This state as a String
     */
    public String toString(){
        return "{id="+ this.getId() + " , groupId = " + groupId + " , name =" + this.getName() + " , output=" + output + " , transitions=" + transitions.toString() + "}";
    }

    /**
     * Comparison made on id attribute
     * @param anotherState Another state
     * @return True if states are equal, False if they are different or anotherState is null
     */
    @Override
    public boolean equals(Object anotherState){

        if(this == anotherState)
            return true;

        if(anotherState == null)
            return false;

        if(anotherState instanceof MooreState)
            return this.getId().equals(((MooreState) anotherState).getId());
        else
            return false;

    }
}
