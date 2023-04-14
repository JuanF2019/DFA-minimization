package com.example.dfaminimization.model.DFAminimizer;

import com.example.dfaminimization.model.DFA.Machine;
import com.example.dfaminimization.model.DFA.MealyMachine;
import com.example.dfaminimization.model.DFA.MooreMachine;
import com.example.dfaminimization.model.states.MooreState;
import com.example.dfaminimization.model.states.State;
import com.example.dfaminimization.model.transitions.MealyTransition;
import com.example.dfaminimization.model.transitions.Transition;

import java.util.*;

/**
 * Class that holds the method for the minimization algorithm
 */
public class Minimizer {

    /**
     * Minimizes a given mealy or moore machine.
     * Algorithm works on 3 steps:
     * <p>
     * 1) Removal of inaccesible states.
     * 2) Removal of redundant states.
     * 3) Reconstruction of the machine.
     *
     * @param machine The machine to be minimized
     * @return The minimized machine
     */
    public static Machine minimize (Machine machine) {
        removeInaccessibleStates(machine);
        Set<Group> partition = mergeRedundantStates(machine);
        return reconstructMachine(machine, partition);
    }

    /**
     * Removes the inaccessible states of a machine.
     * Checks for inaccessible states from the start state using the BFS algorithm to traverse the machine
     * @param machine The machine being minimized
     */
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
                    visitedStates.put(stateTo.getId(),stateTo);
                }
            }
        }

        //Sets the states of the machine as only the visited states during traversal

        machine.states = new HashSet<>(visitedStates.values());
    }

    /**
     * Returns the first partition required for the second step in the minimization algorithm.
     * Works by grouping states as follows.
     * Moore: Two states are in the same group if they have the same output
     * Mealy: Two states are in the same group if they have the same output for all transitions
     * @param machine machine being minimized
     * @return A set that contains groups, representing the first partition.
     */
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
                            //If both states contain the transition then they are equal if the transitions for symbol 'i' have the same output
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

    /**
     * Checks if two partitions are equal checking that a group in the first partition is also in the second one.
     * @param groupsK The first partition (Pk)
     * @param groupsKPlusOne The second partition (Pk+1)
     * @return True of both partitions are equal, False if they are different
     */
    private static boolean partitionsAreEqual(Set<Group> groupsK, Set<Group> groupsKPlusOne){

        boolean partitionsAreEqual = true;

        if(groupsK.isEmpty()) return false;
        if(groupsK.size() != groupsKPlusOne.size()) return false;

        for(Group groupK : groupsK){
            if(!groupIsInPartition(groupK,groupsKPlusOne)) {
                partitionsAreEqual = false;
                break;
            }

        }

        return partitionsAreEqual;
    }

    /**
     * Checks if a group is in a partition. Checks that each state from the group is in at least
     * one group of the same size in the partition being checked;
     * @param group Group
     * @param partition Partition
     * @return True if the group is on the partition. False if th group is not on the partition
     */
    private static boolean groupIsInPartition (Group group, Set<Group> partition){

        boolean foundGroup = true;

        for(Group partitionGroup : partition){

            foundGroup = true;

            if(partitionGroup.states.size() != group.states.size()){
                foundGroup = false;
                continue;
            }

            for (State state: group.states) {

                if (!partitionGroup.states.contains(state)) {
                    foundGroup = false;
                    break;
                }

            }

            if(foundGroup) break;
        }

        return foundGroup;
    }

    /**
     * Groups equivalent "redundant" states base on the following.
     * Starting from the initial partition P1:
     * Two states are on the same group of P2 if all of their transitions lead to the same
     * groups of P1, for each input symbol.
     * <p>
     * Is generalized to Pk and Pk+1
     * Two states are on the same group of Pk+1 if all of their transitions lead to the same
     * groups of Pk, for each input symbol.
     *
     * @param machine machine being minimized
     * @return Partition with the states grouped if they are equivalent
     */
    private static Set<Group> mergeRedundantStates(Machine machine) {

        Set<Group> groupsKPlusOne = initialPartition(machine);

        Set<Group> groupsK = new HashSet<>();

        while (!partitionsAreEqual(groupsK,groupsKPlusOne)) {

            groupsK = groupsKPlusOne;
            groupsKPlusOne = new HashSet<>();

            int idCounter = 0;

            for (Group currentGroupK : groupsK) {

                for (State currentStateK : currentGroupK.states) {

                    if (groupsKPlusOne.isEmpty()) {
                        String newGroupId = Integer.toString(idCounter++);
                        Group newGroup = new Group(newGroupId);
                        currentStateK.groupIdKPlusOne = newGroupId;

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
                                currentStateK.groupIdKPlusOne = currentGroupKPlus1.id;

                                stateGrouped = true;
                                break;
                            }

                        }

                        if (!stateGrouped) {
                            String newGroupId = Integer.toString(idCounter++);
                            Group newGroup = new Group(newGroupId);
                            currentStateK.groupIdKPlusOne = newGroupId;

                            newGroup.states.add(currentStateK);
                            groupsKPlusOne.add(newGroup);
                        }
                    }
                }
            }
            for (State state: machine.states) {
                state.setupNextPartition();
            }
        }
        return groupsKPlusOne;
    }

    /**
     * Creates a new machine, that is equivalent to the machine and is minimal.
     * Rebuilds the machine as follows:
     * 1) Each group in the partition is a new state
     * 2) The group with the start state is the new start state
     * 3) Transitions are taken from the original machine checking only one state in each group.
     * The next states are the groups that contain the original state the transition lead to.
     *
     * @param machine Machine being minimized
     * @param partition Final partition after grouping equal states
     * @return Minimized machine
     */
    static Machine reconstructMachine(Machine machine, Set<Group> partition){

        char idCounter = 65;

        HashMap<String,State> groupIdToNewState = new HashMap<>();

        Machine minimizedMachine;

        if (machine instanceof MealyMachine){
            minimizedMachine = new MealyMachine(machine.inputAlphabet,machine.outputAlphabet);

            for (Group group : partition){

                String newStateId = Character.toString(idCounter);
                String name = constructNewStateName(group);

                if(group.states.contains(machine.startState))
                    ((MealyMachine) minimizedMachine).addStartState(newStateId,name);
                else
                    ((MealyMachine) minimizedMachine).addState(newStateId,name);


                groupIdToNewState.put(group.states.iterator().next().groupId, minimizedMachine.findStateById(newStateId));

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
                String newStateId = Character.toString(idCounter);
                String name = constructNewStateName(group);

                MooreState sampleState = (MooreState) group.states.iterator().next();
                String output = sampleState.getOutput();

                if(group.states.contains(machine.startState))
                    ((MooreMachine) minimizedMachine).addStartState(newStateId, output, name);
                else
                    ((MooreMachine) minimizedMachine).addState(newStateId, output, name);

                groupIdToNewState.put(sampleState.groupId, minimizedMachine.findStateById(newStateId));

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

    /**
     * Constructs a new State name for the minimized machine, based on a group.
     * @param group Group in the final partition
     * @return The group name that contains a String representation of the group. Ex {A,D,E}
     */
    private static String constructNewStateName(Group group){

        StringBuilder builder = new StringBuilder("{");

        for(State state : group.states){
            builder.append(", ");
            builder.append(state.getId());
        }

        builder.append("}");

        return builder.toString().replaceFirst(",","");
    }
}