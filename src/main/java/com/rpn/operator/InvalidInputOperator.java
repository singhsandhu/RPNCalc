package com.rpn.operator;


import com.rpn.input.UserInput;

public class InvalidInputOperator extends AbstractOperator{

    public InvalidInputOperator(UserInput userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        if(!valuesStack.isFurtherProcessingAllowed()) {
            return;
        }
        System.out.println(String.format("Invalid input %s (position: %d)", userInput.getValue(),
                userInput.getPosition() + 1));
        valuesStack.setFurtherProcessingAllowed(false);
    }
}
