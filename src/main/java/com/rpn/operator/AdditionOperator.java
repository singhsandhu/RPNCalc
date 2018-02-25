package com.rpn.operator;

import com.rpn.input.UserInput;

import static com.rpn.validation.SufficientParametersValidator.validateParametersForOperator;

public class AdditionOperator extends AbstractOperator {

    private static final int OPERANDS_REQUIRED = 2;

    public AdditionOperator(UserInput userInput) {
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
        Double secondOperand = valuesStack.getValuesStack().pop().doubleValue();

        pushValueToStack(firstOperand + secondOperand);

        valuesStack.getInstructionsStack().push(userInput);
    }

}
