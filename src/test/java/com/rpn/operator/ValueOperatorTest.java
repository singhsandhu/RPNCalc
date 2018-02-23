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
public class ValueOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private ValueOperator valueOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        valueOperator = new ValueOperator(userInput);
        when(userInput.getPosition()).thenReturn(1);
    }

    @AfterMethod
    public void tearDown() {
        valueOperator.valuesStack.getValuesStack().clear();
        valueOperator.valuesStack.getInstructionsStack().clear();
        valueOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldPushToStack() {
        Stack<BigDecimal> valuesStack = valueOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(15));
        valuesStack.push(BigDecimal.valueOf(25));

        when(userInput.getValue()).thenReturn("67");

        valueOperator.execute();

        assertThat(valueOperator.valuesStack.getValuesStack().size(), is(3));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("67"));
    }

    public void shouldNotDoAnythingToStackIfFurtherProcessingIsNotAllowed() {
        Stack<BigDecimal> valuesStack = valueOperator.valuesStack.getValuesStack();
        valueOperator.valuesStack.setFurtherProcessingAllowed(false);

        valuesStack.push(BigDecimal.valueOf(15));
        valuesStack.push(BigDecimal.valueOf(25));

        when(userInput.getValue()).thenReturn("67");

        valueOperator.execute();

        assertThat(valuesStack.size(), is(2));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("25"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("15"));
    }

    public void shouldPushToInstructionStackAfterExecute() {
        Stack<BigDecimal> valuesStack = valueOperator.valuesStack.getValuesStack();

        when(userInput.getValue()).thenReturn("67");
        valueOperator.execute();

        assertThat(valueOperator.valuesStack.getInstructionsStack().pop(), is(userInput));
    }

}