package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.MealyState;
import com.example.dfaminimization.model.states.MooreState;

public class MealyTransition extends Transition{
    String output;

    public MealyTransition (String input, MealyState stateTo, String output){
        this.input = input;
        this.nextState = stateTo;
        this.output = output;
    }
}
