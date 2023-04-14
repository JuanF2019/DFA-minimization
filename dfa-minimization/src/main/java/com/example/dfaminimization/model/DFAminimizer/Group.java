package com.example.dfaminimization.model.DFAminimizer;

import com.example.dfaminimization.model.states.State;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a group formed during partitioning in the minimization algorithm.
 */
public class Group {

    /**
     * Group id
     */
    public String id;

    /**
     * States that are members of the group
     */
    public Set<State> states;

    /**
     * Group contructor
     * @param id Group id
     */
    public Group(String id){
        this.id = id;
        states = new HashSet<>();
    }

    /**
     * Compares if two groups are the same by checking they have the same states
     * @param otherGroup Another group
     * @return True if they are equal, False if otherGroup is null or does not have the same states
     */
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

    /**
     *
     * @return This group as a String
     */
    public String toString(){
        StringBuilder string = new StringBuilder("{id=" + id + ", states={");

        for(State s: states){
            string.append(s.getName());
        }

        string.append("}}");

        return string.toString();
    }

}
