package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.MealyTransition;
import com.example.dfaminimization.model.transitions.MooreTransition;
import com.example.dfaminimization.utils.Pair;

import java.util.ArrayList;

public class MealyState extends State{

    public MealyState(String id, String name) {
        super(id,name);
    }

    public void addTransition(String input, MealyState stateTo, String output) {
        transitions.put(input, new MealyTransition(stateTo,output));
    }

    @Override
    public String toString(){
        return "{id="+ this.getId() + " , groupId = " + groupId + " , name =" + this.getName() + " , transitions=" + transitions.toString() + "}";
    }

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
