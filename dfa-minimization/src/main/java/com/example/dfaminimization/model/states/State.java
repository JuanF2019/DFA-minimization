package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.Transition;

import java.util.HashMap;

/**
 * Abstract class that holds the information of a DFA state
 */
public abstract class State {
    /**
     * State id
     */
    private final String id;

    /**
     * State name. Examples: "A", "q1"
     */
    private String name;

    /**
     * State transitions. Maps input values to a transition object depending on the machine type.
     */
    public HashMap<String,Transition> transitions;

    /**
     * Updates the state name
     * @param name state name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Base constructor for the state
     * @param id state id
     * @param name state name
     */
    public State(String id, String name){
        this.id = id;
        this.name = name;
        this.transitions = new HashMap<>();
    }

    /**
     * Returns the state id
     * @return this state id
     */
    public String getId(){
        return this.id;
    }

    /**
     * Helper fields that helps with minimization algorithm. Keeps track of the group in partitioning
     */
    public String groupId;

    /**
     * Helper fields that helps with minimization algorithm. Keeps track of the group in partitioning
     */
    public String groupIdKPlusOne;

    /**
     * Equals method, comparison based on id attribute
     * @param anotherState Another state
     * @return True if states are equal, False if another state is null or different from this.
     */
    @Override
    public boolean equals(Object anotherState){

        if(this == anotherState)
            return true;

        if(anotherState == null)
            return false;

        if(anotherState instanceof State)
            return this.id.equals(((State) anotherState).id);
        else
            return false;

    }

    /**
     *
     * @return hash code
     */
    @Override
    public int hashCode(){
        return id.hashCode();
    }

    /**
     *
     * @return This state transitions
     */
    public HashMap<String,Transition> getTransitions(){
        return transitions;
    }

    /**
     *
     * @return This state name
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return This object represented as a string.
     */
    public String toString(){
        return "{id="+ this.id + " , groupId = " + groupId + " , name =" + this.name + " , transitions=" + transitions.toString() + "}";
    }

    /**
     * Updates helper variables on minimization when updating Pk and Pk+1
     */
    public void setupNextPartition(){
        this.groupId = groupIdKPlusOne;
        this.groupIdKPlusOne = "-1";
    }
}
