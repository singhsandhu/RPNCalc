package com.rpn.validation;

import com.rpn.stack.RPNStack;

public final class SufficientParametersValidator {

    public static boolean validateParametersForOperator(RPNStack stack, String operator, int requiredArguments, int position) {
        boolean isValid = true;
        if(!isSufficientParametersForOperator(stack, requiredArguments)) {
            isValid = false;
            stack.setFurtherProcessingAllowed(false);
            System.out.println(String.format("operator %s (position: %d) : insufficient parameters", operator, position + 1));
        }
        return isValid;
    }

    private static boolean isSufficientParametersForOperator(RPNStack stack, int requiredArguments) {
        boolean isValid = true;
        if(stack.getValuesStack().size() < requiredArguments) {
            isValid = false;
        }
        return isValid;
    }

}
