package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.Transition;

import java.util.HashMap;

public abstract class State {

    private final String id;

    private String name;

    public HashMap<String,Transition> transitions;

    public void setName(String name) {
        this.name = name;
    }

    public State(String id, String name){
        this.id = id;
        this.name = name;
        this.transitions = new HashMap<>();
    }

    public String getId(){
        return this.id;
    }

    public String groupId;

    public String groupIdKPlusOne;

    @Override
    public boolean equals(Object anotherState){

        if(this == anotherState)
            return true;

        if(anotherState == null)
            return false;

        if(anotherState instanceof State)
            return this.id.equals(((State) anotherState).id);
        else
            return false;

    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    public HashMap<String,Transition> getTransitions(){
        return transitions;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "{id="+ this.id + " , groupId = " + groupId + " , name =" + this.name + " , transitions=" + transitions.toString() + "}";
    }

    public void setupNextPartition(){
        this.groupId = groupIdKPlusOne;
        this.groupIdKPlusOne = "-1";
    }
}
