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
public class AdditionOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private AdditionOperator additionOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        additionOperator = new AdditionOperator(userInput);
        when(userInput.getValue()).thenReturn("+");
        when(userInput.getPosition()).thenReturn(1);
    }

    @AfterMethod
    public void tearDown() {
        additionOperator.valuesStack.getValuesStack().clear();
        additionOperator.valuesStack.getInstructionsStack().clear();
        additionOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldAddLastTwoOperandsAndPushToStack() {
        Stack<BigDecimal> valuesStack = additionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(312));
        valuesStack.push(BigDecimal.valueOf(123));
        valuesStack.push(BigDecimal.valueOf(23));
        valuesStack.push(BigDecimal.valueOf(14.21));

        additionOperator.execute();

        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("37.21"));
    }

    public void shouldNotDoAnythingToStackIfNotEnoughArguments() {
        Stack<BigDecimal> valuesStack = additionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(312));

        additionOperator.execute();

        assertThat(valuesStack.size(), is(1));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("312"));

    }

    public void shouldNotDoAnythingToStackIfFurtherProcessingIsNotAllowed() {
        Stack<BigDecimal> valuesStack = additionOperator.valuesStack.getValuesStack();
        additionOperator.valuesStack.setFurtherProcessingAllowed(false);

        valuesStack.push(BigDecimal.valueOf(312));
        valuesStack.push(BigDecimal.valueOf(3));
        valuesStack.push(BigDecimal.valueOf(123));

        additionOperator.execute();

        assertThat(valuesStack.size(), is(3));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("123"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("3"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("312"));

    }

    public void shouldPushToInstructionStackAfterExecute() {
        Stack<BigDecimal> valuesStack = additionOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(31));
        valuesStack.push(BigDecimal.valueOf(14));

        additionOperator.execute();

        assertThat(additionOperator.valuesStack.getInstructionsStack().pop(), is(userInput));
    }

}