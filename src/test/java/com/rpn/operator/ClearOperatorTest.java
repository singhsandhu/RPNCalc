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
public class ClearOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private ClearOperator clearOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        clearOperator = new ClearOperator(userInput);
        when(userInput.getValue()).thenReturn("clear");
        when(userInput.getPosition()).thenReturn(3);
    }

    @AfterMethod
    public void tearDown() {
        clearOperator.valuesStack.getValuesStack().clear();
        clearOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldClearTheStack() {
        Stack<BigDecimal> valuesStack = clearOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(312));
        valuesStack.push(BigDecimal.valueOf(123));
        valuesStack.push(BigDecimal.valueOf(23));

        clearOperator.execute();

        assertThat(valuesStack.size(), is(0));
    }

    public void shouldNotDoAnythingToStackIfFurtherProcessingIsNotAllowed() {
        Stack<BigDecimal> valuesStack = clearOperator.valuesStack.getValuesStack();
        clearOperator.valuesStack.setFurtherProcessingAllowed(false);

        valuesStack.push(BigDecimal.valueOf(12));
        valuesStack.push(BigDecimal.valueOf(2));
        valuesStack.push(BigDecimal.valueOf(4));

        clearOperator.execute();

        assertThat(valuesStack.size(), is(3));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("4"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("2"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("12"));

    }

    public void shouldPushToInstructionStackAfterExecute() {
        Stack<BigDecimal> valuesStack = clearOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(0));

        clearOperator.execute();

        assertThat(clearOperator.valuesStack.getInstructionsStack().pop(), is(userInput));
    }

}