package com.rpn.operator;

import com.rpn.input.UserInput;

public class ValueOperator extends AbstractOperator {

    public ValueOperator(UserInput input) {
        super(input);
    }

    @Override
    public void execute() {
        if(!valuesStack.isFurtherProcessingAllowed()) {
            return;
        }

        pushValueToStack(Double.valueOf(userInput.getValue()));

        valuesStack.getInstructionsStack().push(userInput);
    }

}
