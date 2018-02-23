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
public class DivisionOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private DivisionOperator divisionOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        divisionOperator = new DivisionOperator(userInput);
        when(userInput.getValue()).thenReturn("/");
        when(userInput.getPosition()).thenReturn(1);
    }

    @AfterMethod
    public void tearDown() {
        divisionOperator.valuesStack.getValuesStack().clear();
        divisionOperator.valuesStack.getInstructionsStack().clear();
        divisionOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldDivideLastTwoOperandsAndPushToStack() {
        Stack<BigDecimal> valuesStack = divisionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(15));
        valuesStack.push(BigDecimal.valueOf(50));
        valuesStack.push(BigDecimal.valueOf(30));
        valuesStack.push(BigDecimal.valueOf(15));

        divisionOperator.execute();

        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("2"));
    }

    public void shouldNotDoAnythingToStackIfNotEnoughArguments() {
        Stack<BigDecimal> valuesStack = divisionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(100));

        divisionOperator.execute();

        assertThat(valuesStack.size(), is(1));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("100"));

    }

    public void shouldNotDoAnythingToStackIfFurtherProcessingIsNotAllowed() {
        Stack<BigDecimal> valuesStack = divisionOperator.valuesStack.getValuesStack();
        divisionOperator.valuesStack.setFurtherProcessingAllowed(false);

        valuesStack.push(BigDecimal.valueOf(15));
        valuesStack.push(BigDecimal.valueOf(50));
        valuesStack.push(BigDecimal.valueOf(25));

        divisionOperator.execute();

        assertThat(valuesStack.size(), is(3));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("25"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("50"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("15"));

    }

    public void shouldPushToInstructionStackAfterExecute() {
        Stack<BigDecimal> valuesStack = divisionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(30));
        valuesStack.push(BigDecimal.valueOf(15));

        divisionOperator.execute();

        assertThat(divisionOperator.valuesStack.getInstructionsStack().pop(), is(userInput));
    }

}