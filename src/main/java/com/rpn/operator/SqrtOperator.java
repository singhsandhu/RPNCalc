package com.rpn.operator;

import com.rpn.input.UserInput;

import static com.rpn.validation.SufficientParametersValidator.validateParametersForOperator;

public class SqrtOperator extends AbstractOperator {

    private static final int OPERANDS_REQUIRED = 1;

    public SqrtOperator(UserInput userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        if(!valuesStack.isFurtherProcessingAllowed() ||
                !validateParametersForOperator(valuesStack, userInput.getValue(), OPERANDS_REQUIRED,
                        userInput.getPosition())) {
            return;
        }

        Double operand = valuesStack.getValuesStack().pop().doubleValue();

        if(!validateValidSqrtOperation(operand)) {
            pushValueToStack(operand);
            return;
        }

        pushValueToStack(Math.sqrt(operand));

        valuesStack.getInstructionsStack().push(userInput);
    }

    private boolean validateValidSqrtOperation(Double operand) {
        boolean isValid = true;
        if(operand < 0) {
            System.out.println(
                    String.format("Sqrt operation is not valid for negative numbers (position: %d)",
                            userInput.getPosition() + 1));
            valuesStack.setFurtherProcessingAllowed(false);
            isValid = false;
        }
        return isValid;
    }

}
