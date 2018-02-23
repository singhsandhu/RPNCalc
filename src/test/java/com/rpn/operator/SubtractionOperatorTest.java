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
public class SubtractionOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private SubtractionOperator subtractionOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        subtractionOperator = new SubtractionOperator(userInput);
        when(userInput.getValue()).thenReturn("-");
        when(userInput.getPosition()).thenReturn(1);
    }

    @AfterMethod
    public void tearDown() {
        subtractionOperator.valuesStack.getValuesStack().clear();
        subtractionOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldSubtractLastTwoOperandsAndPushToStack() {
        Stack<BigDecimal> valuesStack = subtractionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(312));
        valuesStack.push(BigDecimal.valueOf(123));
        valuesStack.push(BigDecimal.valueOf(23));
        valuesStack.push(BigDecimal.valueOf(14.21));

        subtractionOperator.execute();

        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("8.79"));
    }

    public void shouldNotDoAnythingToStackIfNotEnoughArguments() {
        Stack<BigDecimal> valuesStack = subtractionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(98011.11));

        subtractionOperator.execute();

        assertThat(valuesStack.size(), is(1));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("98011.11"));

    }

    public void shouldNotDoAnythingToStackIfFurtherProcessingIsNotAllowed() {
        Stack<BigDecimal> valuesStack = subtractionOperator.valuesStack.getValuesStack();
        subtractionOperator.valuesStack.setFurtherProcessingAllowed(false);

        valuesStack.push(BigDecimal.valueOf(11091));
        valuesStack.push(BigDecimal.valueOf(512));
        valuesStack.push(BigDecimal.valueOf(1900.1));

        subtractionOperator.execute();

        assertThat(valuesStack.size(), is(3));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("1900.1"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("512"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("11091"));

    }

    public void shouldPushToInstructionStackAfterExecute() {
        Stack<BigDecimal> valuesStack = subtractionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(12));
        valuesStack.push(BigDecimal.valueOf(2));

        subtractionOperator.execute();

        assertThat(subtractionOperator.valuesStack.getInstructionsStack().pop(), is(userInput));
    }

}