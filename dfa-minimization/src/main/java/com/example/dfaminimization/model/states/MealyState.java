package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.MealyTransition;

/**
 * Class that holds the information of a state from a Moore machine
 */
public class MealyState extends State{

    /**
     *
     * @param id State id
     * @param name State name
     */
    public MealyState(String id, String name) {
        super(id,name);
    }

    /**
     *
     * @param input Input symbol
     * @param stateTo State that corresponds to the transition
     * @param output Output symbol associated with this transition
     */
    public void addTransition(String input, MealyState stateTo, String output) {
        transitions.put(input, new MealyTransition(stateTo,output));
    }

    /**
     *
     * @return State as String
     */
    @Override
    public String toString(){
        return "{id="+ this.getId() + " , groupId = " + groupId + " , name =" + this.getName() + " , transitions=" + transitions.toString() + "}";
    }

    /**
     * Comparison made on id attribute
     * @param anotherState Another state
     * @return True if states are equal, False if anotherState is null or states are differente
     */
    @Override
    public boolean equals(Object anotherState){

        if(this == anotherState)
            return true;

        if(anotherState == null)
            return false;

        if(anotherState instanceof MealyState)
            return this.getId().equals(((MealyState) anotherState).getId());
        else
            return false;

    }
}
