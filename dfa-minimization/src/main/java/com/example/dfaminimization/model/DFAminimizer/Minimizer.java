package com.example.dfaminimization.model.DFAminimizer;

import com.example.dfaminimization.model.DFA.Machine;
import com.example.dfaminimization.model.states.State;
import com.example.dfaminimization.model.transitions.Transition;

import java.util.*;

public class Minimizer {

    public static Machine minimize (Machine machine){

        removeInaccessibleStates(machine);


        return reconstructMachine();
    }

    static void removeInaccessibleStates(Machine machine) {
        Collection<State> visitedStates = getVisitedStates(machine);

        //Remover del listado de estados aquellos no visitados
        //Remover las transiciones salientes\
        //No hay transiciones entrantes que no se remuevan con los dos pasos anteriores puesto
        //que los estados no visitados están aislados

    }

    static Collection<State> getVisitedStates(Machine machine){
        ArrayList<State> states = machine.states;
        State startState = machine.startState;

        Queue<State> pendingStatesQueue = new ArrayDeque<>();
        HashMap<String,State> visitedStates = new HashMap<String,State>();

        pendingStatesQueue.add(startState);
        visitedStates.put(startState.getId(),startState);

        while(!pendingStatesQueue.isEmpty()){
            State currentState = pendingStatesQueue.poll();

            ArrayList<Transition> transitions = currentState.transitions;

            for (Transition t:transitions){

                State stateTo = t.nextState;

                if(!visitedStates.containsKey(stateTo.getId())){
                    pendingStatesQueue.add(startState);
                    visitedStates.put(currentState.getId(),currentState);
                }
            }
        }

        return visitedStates.values();
    }



    static void mergeRedundantStates(){

    }

    static Machine reconstructMachine(){


        return null;
    }

}