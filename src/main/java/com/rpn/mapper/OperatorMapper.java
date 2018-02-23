package com.rpn.mapper;

import com.rpn.input.UserInput;
import com.rpn.operator.ClearOperator;
import com.rpn.operator.DivisionOperator;
import com.rpn.operator.InvalidInputOperator;
import com.rpn.operator.SubtractionOperator;
import com.rpn.operator.MultiplicationOperator;
import com.rpn.operator.Operator;
import com.rpn.operator.AdditionOperator;
import com.rpn.operator.SqrtOperator;
import com.rpn.operator.UndoOperator;
import com.rpn.operator.ValueOperator;
import java.util.function.Function;

public class OperatorMapper implements Function<UserInput, Operator> {

    @Override
    public Operator apply(UserInput input) {
        if(isNumeric(input.getValue())) {
            return new ValueOperator(input);
        }

        switch (input.getValue()) {
            case "+":
                return new AdditionOperator(input);
            case "-":
                return new SubtractionOperator(input);
            case "*":
                return new MultiplicationOperator(input);
            case "/":
                return new DivisionOperator(input);
            case "sqrt":
                return new SqrtOperator(input);
            case "clear":
                return new ClearOperator(input);
            case "undo":
                return new UndoOperator(input);
            default:
                return new InvalidInputOperator(input);
        }
    }

    private boolean isNumeric(String inputData) {
        return inputData.matches("[-+]?\\d+(\\.\\d+)?");
    }
}
