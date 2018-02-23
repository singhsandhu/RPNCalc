package com.rpn.operator;

import com.rpn.input.UserInput;

public class ClearOperator extends AbstractOperator {

    public ClearOperator(UserInput userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
       if(!valuesStack.isFurtherProcessingAllowed()) {
           return;
       }

       if(valuesStack.getValuesStack().empty()) {
           System.out.println("Stack is already empty, so clear operator not executed");
           return;
       }

       valuesStack.getValuesStack().clear();
       valuesStack.getInstructionsStack().push(userInput);
    }

}
