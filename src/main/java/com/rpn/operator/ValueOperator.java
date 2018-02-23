package com.rpn.operator;

import com.rpn.input.UserInput;
import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class ValueOperator extends AbstractOperator {

    public ValueOperator(UserInput input) {
        super(input);
    }

    @Override
    public void execute() {
        if(!valuesStack.isFurtherProcessingAllowed()) {
            return;
        }

        valuesStack.getValuesStack().
                push(BigDecimal.valueOf(Double.valueOf(userInput.getValue()))
                        .setScale(STORE_PRECISION_SCALE, ROUND_HALF_UP));

        valuesStack.getInstructionsStack().push(userInput);
    }

}
