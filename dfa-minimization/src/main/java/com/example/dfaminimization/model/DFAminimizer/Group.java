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
        return this.states.equals(((Group) otherGroup).states);
    }

}
