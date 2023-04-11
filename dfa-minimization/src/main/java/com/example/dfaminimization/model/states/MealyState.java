package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.MealyTransition;
import com.example.dfaminimization.model.transitions.MooreTransition;
import com.example.dfaminimization.utils.Pair;

import java.util.ArrayList;

public class MealyState extends State{

    public MealyState(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addTransition(String input, MealyState stateTo, String output) {
        transitions.put(input, new MealyTransition(stateTo,output));
    }
}
