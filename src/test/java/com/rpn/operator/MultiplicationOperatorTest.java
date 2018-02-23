package com.rpn.operator;

import com.rpn.input.UserInput;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Stack;
import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class MultiplicationOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private MultiplicationOperator multiplicationOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        multiplicationOperator = new MultiplicationOperator(userInput);
        when(userInput.getValue()).thenReturn("*");
        when(userInput.getPosition()).thenReturn(1);
    }

    @AfterMethod
    public void tearDown() {
        multiplicationOperator.valuesStack.getValuesStack().clear();
        multiplicationOperator.valuesStack.getInstructionsStack().clear();
        multiplicationOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldMultiplyLastTwoOperandsAndPushToStack() {
        Stack<BigDecimal> valuesStack = multiplicationOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(5));
        valuesStack.push(BigDecimal.valueOf(4));
        valuesStack.push(BigDecimal.valueOf(3));
        valuesStack.push(BigDecimal.valueOf(8));

        multiplicationOperator.execute();

        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("24"));
    }

    public void shouldNotDoAnythingToStackIfNotEnoughArguments() {
        Stack<BigDecimal> valuesStack = multiplicationOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(8));

        multiplicationOperator.execute();

        assertThat(valuesStack.size(), is(1));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("8"));

    }

    public void shouldNotDoAnythingToStackIfFurtherProcessingIsNotAllowed() {
        Stack<BigDecimal> valuesStack = multiplicationOperator.valuesStack.getValuesStack();
        multiplicationOperator.valuesStack.setFurtherProcessingAllowed(false);

        valuesStack.push(BigDecimal.valueOf(5));
        valuesStack.push(BigDecimal.valueOf(6));

        multiplicationOperator.execute();

        assertThat(valuesStack.size(), is(2));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("6"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("5"));
    }

    public void shouldPushToInstructionStackAfterExecute() {
        Stack<BigDecimal> valuesStack = multiplicationOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(10));
        valuesStack.push(BigDecimal.valueOf(5));

        multiplicationOperator.execute();

        assertThat(multiplicationOperator.valuesStack.getInstructionsStack().pop(), is(userInput));
    }

}