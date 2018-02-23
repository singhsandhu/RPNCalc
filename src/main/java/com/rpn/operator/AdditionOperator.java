package com.rpn.operator;

import com.rpn.input.UserInput;
import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

import static com.rpn.validation.SufficientParametersValidator.validateParametersForOperator;

public class AdditionOperator extends AbstractOperator {

    private static final int OPERANDS_REQUIRED = 2;

    public AdditionOperator(UserInput userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        if(!valuesStack.isFurtherProcessingAllowed() ||
                !validateParametersForOperator(valuesStack, userInput.getValue(), OPERANDS_REQUIRED, userInput.getPosition())) {
            return;
        }

        BigDecimal firstOperand = valuesStack.getValuesStack().pop();
        BigDecimal secondOperand = valuesStack.getValuesStack().pop();

        valuesStack.getValuesStack().push
                (BigDecimal.valueOf(firstOperand.doubleValue() + secondOperand.doubleValue())
                .setScale(STORE_PRECISION_SCALE, ROUND_HALF_UP));

        valuesStack.getInstructionsStack().push(userInput);
    }

}
