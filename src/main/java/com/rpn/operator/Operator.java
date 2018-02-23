package com.rpn.operator;

import com.rpn.stack.RPNStack;

public interface Operator {

    RPNStack valuesStack = RPNStack.getInstance();
    int STORE_PRECISION_SCALE = 15;

    void execute();
}
