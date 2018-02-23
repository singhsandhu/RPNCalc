package com.rpn.operator;

import com.rpn.input.UserInput;
import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

import static com.rpn.validation.SufficientParametersValidator.validateParametersForOperator;

public class MultiplicationOperator extends AbstractOperator {

    private static final int OPERANDS_REQUIRED = 2;

    public MultiplicationOperator(UserInput userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        if(!valuesStack.isFurtherProcessingAllowed() ||
                !validateParametersForOperator(valuesStack, userInput.getValue(), OPERANDS_REQUIRED, userInput.getPosition())) {
            return;
        }

        Double firstOperand = valuesStack.getValuesStack().pop().doubleValue();
        Double secondOperand = valuesStack.getValuesStack().pop().doubleValue();
        valuesStack.getValuesStack().push
                (BigDecimal.valueOf(firstOperand * secondOperand)
                .setScale(STORE_PRECISION_SCALE, ROUND_HALF_UP));

        valuesStack.getInstructionsStack().push(userInput);
    }

}
