package com.example.dfaminimization.model.transitions;

import com.example.dfaminimization.model.states.State;

public abstract class Transition {
    String input;
    public State nextState;
}
