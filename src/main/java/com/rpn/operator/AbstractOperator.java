package com.rpn.operator;

import com.rpn.input.UserInput;

public abstract class AbstractOperator implements Operator{

    protected UserInput userInput;

    public AbstractOperator(UserInput userInput) {
        this.userInput = userInput;
    }

}
