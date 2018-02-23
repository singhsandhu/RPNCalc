package com.rpn.mapper;

import com.rpn.input.UserInput;
import com.rpn.operator.AdditionOperator;
import com.rpn.operator.ClearOperator;
import com.rpn.operator.DivisionOperator;
import com.rpn.operator.InvalidInputOperator;
import com.rpn.operator.MultiplicationOperator;
import com.rpn.operator.SqrtOperator;
import com.rpn.operator.SubtractionOperator;
import com.rpn.operator.Operator;
import com.rpn.operator.UndoOperator;
import com.rpn.operator.ValueOperator;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class OperatorMapperTest {

    @Mock
    private UserInput userInput;

    private OperatorMapper operatorMapper;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        operatorMapper = new OperatorMapper();
    }

    public void shouldReturnValueOperatorForNumericInput() {
        when(userInput.getValue()).thenReturn("123.11");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof ValueOperator, is(true));
    }

    public void shouldReturnInvalidInputOperatorForUnknownInput() {
        when(userInput.getValue()).thenReturn("abc");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof InvalidInputOperator, is(true));
    }

    public void shouldReturnAdditionOperatorForAddInput() {
        when(userInput.getValue()).thenReturn("+");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof AdditionOperator, is(true));
    }

    public void shouldReturnSubstractionOperatorForSubstractInput() {
        when(userInput.getValue()).thenReturn("-");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof SubtractionOperator, is(true));
    }

    public void shouldReturnMultiplicationOperatorForMultiplyInput() {
        when(userInput.getValue()).thenReturn("*");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof MultiplicationOperator, is(true));
    }

    public void shouldReturnDivisionOperatorForDivideInput() {
        when(userInput.getValue()).thenReturn("/");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof DivisionOperator, is(true));
    }

    public void shouldReturnSqrtOperatorForSqrtInput() {
        when(userInput.getValue()).thenReturn("sqrt");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof SqrtOperator, is(true));
    }

    public void shouldReturnClearOperatorForClearInput() {
        when(userInput.getValue()).thenReturn("clear");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof ClearOperator, is(true));
    }

    public void shouldReturnUndoOperatorForUndoInput() {
        when(userInput.getValue()).thenReturn("undo");

        Operator operator = operatorMapper.apply(userInput);
        assertThat(operator instanceof UndoOperator, is(true));
    }

}