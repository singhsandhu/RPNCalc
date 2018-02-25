package com.rpn.operator;

import com.rpn.input.UserInput;
import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

public abstract class AbstractOperator implements Operator{

    protected UserInput userInput;

    public AbstractOperator(UserInput userInput) {
        this.userInput = userInput;
    }

    void pushValueToStack(Double value) {
        valuesStack.getValuesStack().push(BigDecimal.valueOf(value)
                .setScale(STORE_PRECISION_SCALE, ROUND_HALF_UP));
    }

}
