package com.rpn.operator;

import com.rpn.input.UserInput;
import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

import static com.rpn.validation.SufficientParametersValidator.validateParametersForOperator;

public class SqrtOperator extends AbstractOperator {

    private static final int OPERANDS_REQUIRED = 1;

    public SqrtOperator(UserInput userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        if(!valuesStack.isFurtherProcessingAllowed() ||
                !validateParametersForOperator(valuesStack, userInput.getValue(), OPERANDS_REQUIRED, userInput.getPosition())) {
            return;
        }

        Double operand = valuesStack.getValuesStack().pop().doubleValue();
        valuesStack.getValuesStack().push
                (BigDecimal.valueOf(Math.sqrt(operand))
                .setScale(STORE_PRECISION_SCALE, ROUND_HALF_UP));

        valuesStack.getInstructionsStack().push(userInput);
    }


}
