package com.rpn.operator;

import com.rpn.input.UserInput;

import static com.rpn.validation.SufficientParametersValidator.validateParametersForOperator;

public class DivisionOperator extends AbstractOperator {

    private static final int OPERANDS_REQUIRED = 2;

    public DivisionOperator(UserInput userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        if(!valuesStack.isFurtherProcessingAllowed() ||
                !validateParametersForOperator(valuesStack, userInput.getValue(),
                        OPERANDS_REQUIRED, userInput.getPosition())) {
            return;
        }

        Double firstOperand = valuesStack.getValuesStack().pop().doubleValue();
        if(!validateValidDivision(firstOperand)) {
            pushValueToStack(firstOperand);
            return;
        }
        Double secondOperand = valuesStack.getValuesStack().pop().doubleValue();

        pushValueToStack(secondOperand / firstOperand);

        valuesStack.getInstructionsStack().push(userInput);
    }

    private boolean validateValidDivision(Double firstOperand) {
        boolean isValid = true;
        if(firstOperand == 0) {
            System.out.println(
                    String.format("Division by zero is not permitted. (position: %d)",
                            userInput.getPosition() + 1));
            valuesStack.setFurtherProcessingAllowed(false);
            isValid = false;
        }
        return isValid;
    }

}
