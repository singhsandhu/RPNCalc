package com.rpn.stack;

import com.rpn.input.UserInput;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Stack;

public final class RPNStack {

    private final Stack<BigDecimal> valuesStack = new Stack<>();
    private final Stack<UserInput> instructionsStack = new Stack<>();

    private static final RPNStack instance = new RPNStack();
    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");
    private boolean isFurtherProcessingAllowed = true;

    private RPNStack() {}

    public static RPNStack getInstance() {
        return instance;
    }

    public Stack<BigDecimal> getValuesStack() {
        return valuesStack;
    }

    public Stack<UserInput> getInstructionsStack() {
        return instructionsStack;
    }

    public boolean isFurtherProcessingAllowed() {
        return isFurtherProcessingAllowed;
    }

    public void setFurtherProcessingAllowed(boolean furtherProcessingAllowed) {
        this.isFurtherProcessingAllowed = furtherProcessingAllowed;
    }

    public void printStack() {
        setFurtherProcessingAllowed(true);
        System.out.print("Stack: ");
        valuesStack.stream()
                .forEach(value -> {
                    System.out.print(DISPLAY_FMT.format(value.doubleValue()) + " ");
                });
        System.out.println();
    }

}
