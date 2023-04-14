package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.MealyState;

/**
 * Class that holds the information of a transition from a Mealy machine
 */
public class MealyTransition extends Transition{

    /**
     * Output symbol associated with a mealy transition
     */
    public String output;

    /**
     * Mealy transition constructor
     * @param stateTo State that results of a mealy transition
     * @param output Output associated with a mealy transition
     */
    public MealyTransition (MealyState stateTo, String output){
        this.nextState = stateTo;
        this.output = output;
    }

    /**
     *
     * @return Transition output
     */
    public String getOutput() {
        return output;
    }

    /**
     *
     * @return Mealy transition represented as a String
     */
    public String toString(){
        return "{nextState=" + "{ id = " + this.nextState.getId() + ", name = " + this.nextState.getName() + "} , output=" + this.output + "}";
    }
}
