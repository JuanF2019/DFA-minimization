package com.example.dfaminimization.model.DFAminimizer;

import com.example.dfaminimization.model.DFA.Machine;
import com.example.dfaminimization.model.DFA.MealyMachine;
import com.example.dfaminimization.model.DFA.MooreMachine;
import com.example.dfaminimization.model.states.MooreState;
import com.example.dfaminimization.model.states.State;
import com.example.dfaminimization.model.transitions.MealyTransition;
import com.example.dfaminimization.model.transitions.Transition;

import java.util.*;

public class Minimizer {

    public static Machine minimize (Machine machine){

        removeInaccessibleStates(machine);

        Set<Group> partition = mergeRedundantStates(machine);

        return reconstructMachine(machine, partition);
    }

    //Checks for inaccessible states from the start state using the BFS algorithm to traverse the machine
    private static void removeInaccessibleStates(Machine machine){

        State startState = machine.startState;

        Queue<State> pendingStatesQueue = new ArrayDeque<>();
        HashMap<String,State> visitedStates = new HashMap<>();

        pendingStatesQueue.add(startState);
        visitedStates.put(startState.getId(),startState);

        while(!pendingStatesQueue.isEmpty()){
            State currentState = pendingStatesQueue.poll();

            Collection<Transition> transitions = currentState.transitions.values();

            for (Transition t:transitions){

                State stateTo = t.nextState;

                if(!visitedStates.containsKey(stateTo.getId())){
                    pendingStatesQueue.add(stateTo);
                    visitedStates.put(currentState.getId(),currentState);
                }
            }
        }

        //Sets the states of the machine as only the visited states during traversal
        machine.states = new HashSet<>(visitedStates.values());
    }

    private static Set<Group> initialPartition(Machine machine){

        Set<State> machineStates = machine.states;
        Set<Group> groupsK = new HashSet<>();
        int idCounter = 0;

        if(machine instanceof MooreMachine) {
            for(State s : machineStates){

                MooreState ms = (MooreState) s;

                if(groupsK.isEmpty()){
                    String groupId = ms.getOutput(); //Id is output value
                    ms.groupId = groupId;

                    Group firstGroup = new Group(groupId);
                    firstGroup.states.add(ms);
                    groupsK.add(firstGroup);
                }
                else{
                    boolean stateGrouped = false;
                    for(Group mg : groupsK){
                        if(mg.id.equals(ms.getOutput())){

                            ms.groupId = mg.id;
                            mg.states.add(ms);

                            stateGrouped = true;
                            break;
                        }
                    }

                    if(!stateGrouped){
                        String groupId = ms.getOutput(); //Id is output value
                        ms.groupId = groupId;

                        Group newGroup = new Group(groupId);
                        newGroup.states.add(ms);
                        groupsK.add(newGroup);
                    }
                }
            }
        }
        else{//Mealy machine
            for(State state : machineStates){

                boolean stateAssignedToGroup = false;

                for(Group group : groupsK){
                    Set<String> inputAlphabet = machine.inputAlphabet;
                    State testState = group.states.iterator().next();

                    boolean statesAreEqual = true;

                    for(String inputSymbol : inputAlphabet){
                        //Both states contain or not a transition for an input symbol
                        if(state.transitions.containsKey(inputSymbol) == testState.transitions.containsKey(inputSymbol)){
                            //If both states contain the transition then they are equal if the transitions for symbol i have the same output
                            if(state.transitions.containsKey(inputSymbol)){

                                String stateTransitionOutput = ((MealyTransition) state.transitions.get(inputSymbol)).output;
                                String testStateTransitionOutput = ((MealyTransition) testState.transitions.get(inputSymbol)).output;

                                //If for one transition with symbol i they have a different output, the states are different
                                if(!stateTransitionOutput.equals(testStateTransitionOutput)){
                                    statesAreEqual = false;
                                    break;
                                }
                            }
                            //If both states do not contain a transition for a symbol i, there's no reason to believe they are different

                        }
                        else{//If one state contains a transition for a symbol i and the other does not, they are different;
                            statesAreEqual = false;
                            break;
                        }
                    }

                    if(statesAreEqual){
                        group.states.add(state);
                        state.groupId = group.id;

                        stateAssignedToGroup = true;
                        break;
                    }

                }

                if(!stateAssignedToGroup){
                    String groupId = Integer.toString(idCounter++);
                    state.groupId = groupId;

                    Group newGroup = new Group(groupId);
                    newGroup.states.add(state);
                    groupsK.add(newGroup);
                }
            }
        }
        return groupsK;
    }


    private static Set<Group> mergeRedundantStates(Machine machine) {

        Set<Group> groupsK = initialPartition(machine);
        Set<Group> groupsKPlusOne = new HashSet<>();


        while (!groupsK.equals(groupsKPlusOne)) {


            int idCounter = 0;

            for (Group currentGroupK : groupsK) {
                for (State currentStateK : currentGroupK.states) {
                    if (groupsKPlusOne.isEmpty()) {

                        String newGroupId = Integer.toString(idCounter++);
                        Group newGroup = new Group(newGroupId);
                        currentStateK.groupId = newGroupId;

                        newGroup.states.add(currentStateK);
                        groupsKPlusOne.add(newGroup);
                    } else {
                        boolean stateGrouped = false;

                        for (Group currentGroupKPlus1 : groupsKPlusOne) {

                            boolean statesAreEqual = true; //Assume they are equivalent

                            State testState = currentGroupKPlus1.states.iterator().next();

                            Set<String> inputAlphabet = machine.inputAlphabet;

                            for (String inputSymbol : inputAlphabet) {

                                //Both states contain or not a transition for an input symbol
                                if (currentStateK.transitions.containsKey(inputSymbol) == testState.transitions.containsKey(inputSymbol)) {
                                    //If both states contain the transition then they are equal if they lead to the same group on Pk
                                    if (currentStateK.transitions.containsKey(inputSymbol)) {
                                        State msNextState = currentStateK.transitions.get(inputSymbol).nextState;
                                        State testStateNextState = testState.transitions.get(inputSymbol).nextState;

                                        //If the next states are different and are from different groups then the base states are different.
                                        //Else they are equal, since the next states ar the same or are in the same group.
                                        if (msNextState != testStateNextState && !msNextState.groupId.equals(testStateNextState.groupId)) {
                                            statesAreEqual = false;
                                            break;
                                        }
                                    }
                                    //If both states do not contain a transition for a symbol i, there's no reason to believe they are different

                                } else {//If one state contains a transition for a symbol i and the other does not, they are different;
                                    statesAreEqual = false;
                                    break;
                                }
                            }

                            if (statesAreEqual) {

                                currentGroupKPlus1.states.add(currentStateK);
                                currentStateK.groupId = currentGroupKPlus1.id;

                                stateGrouped = true;
                                break;
                            }

                        }

                        if (!stateGrouped) {
                            String newGroupId = Integer.toString(idCounter++);
                            Group newGroup = new Group(newGroupId);
                            currentStateK.groupId = newGroupId;

                            newGroup.states.add(currentStateK);
                            groupsKPlusOne.add(newGroup);
                        }


                    }
                }
            }

        }
        return groupsK;
    }

    static Machine reconstructMachine(Machine machine, Set<Group> partition){

        char idCounter = 65;

        HashMap<String,State> groupIdToNewState = new HashMap<>();

        Machine minimizedMachine;

        if (machine instanceof MealyMachine){
            minimizedMachine = new MealyMachine(machine.inputAlphabet,machine.outputAlphabet);

            for (Group group : partition){

                String id = Character.toString(idCounter);
                String name = constructNewStateName(group);


                if(group.states.contains(machine.startState)){

                    ((MealyMachine) minimizedMachine).addStartState(id,name);
                }
                else{

                    ((MealyMachine) minimizedMachine).addState(id,name);
                }

                groupIdToNewState.put(group.states.iterator().next().groupId, minimizedMachine.findStateById(id));

                idCounter++;
            }

            Set<String> inputAlphabet = machine.inputAlphabet;

            for (Group group : partition){

                State sampleState = group.states.iterator().next();

                for(String inputSymbol : inputAlphabet){

                    Transition transition = sampleState.transitions.get(inputSymbol);

                    if( transition != null ){

                        State nextState = transition.nextState;

                        State newFromState = groupIdToNewState.get(sampleState.groupId);
                        State newToState = groupIdToNewState.get(nextState.groupId);

                        ((MealyMachine) minimizedMachine).addTransition(inputSymbol,newFromState.getId(),newToState.getId(), ((MealyTransition)transition).output);
                    }
                }
            }

        }
        else{
            minimizedMachine = new MooreMachine(machine.inputAlphabet,machine.outputAlphabet);

            for (Group group : partition){
                String id = Character.toString(idCounter);

                String name = constructNewStateName(group);
                MooreState sampleState = (MooreState) group.states.iterator().next();
                String output = sampleState.getOutput();

                if(group.states.contains(machine.startState))
                    ((MooreMachine) minimizedMachine).addStartState(id, output, name);
                else
                    ((MooreMachine) minimizedMachine).addState(id, output, name);

                groupIdToNewState.put(sampleState.groupId, minimizedMachine.findStateById(id));

                idCounter++;
            }

            Set<String> inputAlphabet = machine.inputAlphabet;

            for (Group group : partition){

                State sampleState = group.states.iterator().next();

                for(String inputSymbol : inputAlphabet){

                    Transition transition = sampleState.transitions.get(inputSymbol);

                    if( transition != null ){

                        State nextState = transition.nextState;

                        State newFromState = groupIdToNewState.get(sampleState.groupId);
                        State newToState = groupIdToNewState.get(nextState.groupId);

                        ((MooreMachine) minimizedMachine).addTransition(inputSymbol,newFromState.getId(),newToState.getId());
                    }
                }
            }
        }

        return minimizedMachine;
    }

    private static String constructNewStateName(Group group){

        StringBuilder builder = new StringBuilder("{ ");

        for(State state : group.states){
            builder.append(", ");
            builder.append(state.getId());
        }

        builder.append(" }");
        builder.replace(1,2, "");

        return builder.toString();
    }

}