package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.MealyState;
import com.example.dfaminimization.model.states.MooreState;

public class MealyTransition extends Transition{
    public String output;

    public MealyTransition (MealyState stateTo, String output){
        this.nextState = stateTo;
        this.output = output;
    }
}
