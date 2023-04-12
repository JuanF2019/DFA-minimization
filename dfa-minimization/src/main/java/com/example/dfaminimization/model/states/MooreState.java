package com.example.dfaminimization.model.states;

import com.example.dfaminimization.model.transitions.MooreTransition;

public class MooreState extends State{

    String output;

    public MooreState(String id, String output, String name) {
        super(id,name);
        this.output = output;
    }

    public void addTransition(String input, MooreState stateTo) {
        transitions.put(input, new MooreTransition(stateTo));
    }

    public String getOutput() {
        return output;
    }

    public String toString(){
        return "{id="+ this.getId() + " , groupId = " + groupId + " , name =" + this.getName() + " , output=" + output + " , transitions=" + transitions.toString() + "}";
    }

    @Override
    public boolean equals(Object anotherState){

        if(this == anotherState)
            return true;

        if(anotherState == null)
            return false;

        if(anotherState instanceof MooreState)
            return this.getId().equals(((MooreState) anotherState).getId());
        else
            return false;

    }
}
