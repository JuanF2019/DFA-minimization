package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.MealyTransition;
import com.example.dfaminimization.model.transitions.MooreTransition;
import com.example.dfaminimization.utils.Pair;

import java.util.ArrayList;

public class MealyState extends State{

    public MealyState(String id) {
        this.id = id;
    }

    public void addTransition(String input, MealyState stateTo, String output) {
        transitions.add(new MealyTransition(input,stateTo,output));
    }
}
