package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.Transition;

import java.util.HashMap;

public abstract class State {

    String id;

    public String name;

    public HashMap<String,Transition> transitions;

    public String getId(){
        return this.id;
    }

    public String groupId;

    @Override
    public boolean equals(Object anotherState){
        return this.id.equals(((State) anotherState).id);
    }

}
