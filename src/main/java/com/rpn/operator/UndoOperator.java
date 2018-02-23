package com.rpn.operator;

import com.rpn.input.UserInput;
import com.rpn.mapper.OperatorMapper;
import java.util.List;
import java.util.stream.Collectors;

public class UndoOperator extends AbstractOperator {

    public UndoOperator(UserInput userInput) {
        super(userInput);
    }

    @Override
    public void execute() {

        if(!valuesStack.isFurtherProcessingAllowed()) {
            return;
        }

        if(valuesStack.getInstructionsStack().isEmpty()) {
            System.out.println("Undo operation is not valid on empty stack");
            return;
        }

        valuesStack.getInstructionsStack().pop();

        List<UserInput> instructionsList = valuesStack.getInstructionsStack()
                .stream()
                .collect(Collectors.toList());

        clearAllStacks();

        /* Kind of replay of all the previous events*/
        OperatorMapper mapper = new OperatorMapper();
        instructionsList.stream()
                .map(mapper)
                .forEach(operator -> operator.execute());
    }

    private void clearAllStacks() {
        valuesStack.getInstructionsStack().clear();
        valuesStack.getValuesStack().clear();
    }
}
