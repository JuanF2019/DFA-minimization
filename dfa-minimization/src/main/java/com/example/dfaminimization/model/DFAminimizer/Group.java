package com.example.dfaminimization.model.DFAminimizer;

import com.example.dfaminimization.model.states.State;

import java.util.HashSet;
import java.util.Set;

public class Group {

    public String id;

    public Set<State> states;

    public Group(String id){
        this.id = id;
        states = new HashSet<>();
    }

    @Override
    public boolean equals(Object otherGroup){

        if(this == otherGroup)
            return true;

        if(otherGroup == null)
            return false;

        if(otherGroup instanceof Group) {

            return this.states.equals(((Group) otherGroup).states);
        }
        else{
            return false;
        }
    }

    public String toString(){
        StringBuilder string = new StringBuilder("{id=" + id + ", states={");

        for(State s: states){
            string.append(s.getName());
        }

        string.append("}}");

        return string.toString();
    }

}
