package com.example.dfaminimization.model.DFAminimizer;

import com.example.dfaminimization.model.DFA.Machine;
import com.example.dfaminimization.model.states.State;
import com.example.dfaminimization.model.transitions.Transition;

import java.util.*;

public class Minimizer {

    public static Machine minimize (Machine machine){




        return reconstructMachine();
    }

    static void removeInaccessibleStates(Machine machine) {

        ArrayList<State> states = machine.states;
        State startState = machine.startState;

        Queue<State> pendingStatesQueue = new ArrayDeque<>();
        HashSet<String> visitedStates = new HashSet<>();

        pendingStatesQueue.add(startState);
        visitedStates.add(startState.getId());

        while(!pendingStatesQueue.isEmpty()){
            State currentState = pendingStatesQueue.poll();

            ArrayList<Transition> transitions = currentState.transitions;

            for (Transition t:transitions){

                State stateTo = t.nextState;

                if(!visitedStates.contains(stateTo.getId())){
                    pendingStatesQueue.add(startState);
                    visitedStates.add(currentState.getId());
                }
            }
        }
    }

    static void mergeRedundantStates(){

    }

    static Machine reconstructMachine(){


        return null;
    }

}