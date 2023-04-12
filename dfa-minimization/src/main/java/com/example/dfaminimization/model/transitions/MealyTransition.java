package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.MealyState;

public class MealyTransition extends Transition{
    public String output;

    public MealyTransition (MealyState stateTo, String output){
        this.nextState = stateTo;
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public String toString(){
        return "{nextState=" + "{ id = " + this.nextState.getId() + ", name = " + this.nextState.getName() + "} , output=" + this.output + "}";
    }
}
