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
public class InvalidInputOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private InvalidInputOperator invalidInputOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        invalidInputOperator = new InvalidInputOperator(userInput);
        when(userInput.getValue()).thenReturn("abc");
        when(userInput.getPosition()).thenReturn(1);
    }

    @AfterMethod
    public void tearDown() {
        invalidInputOperator.valuesStack.getValuesStack().clear();
        invalidInputOperator.valuesStack.getInstructionsStack().clear();
        invalidInputOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldNotDoAnythingToStack() {
        Stack<BigDecimal> valuesStack = invalidInputOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(10));
        valuesStack.push(BigDecimal.valueOf(20));

        invalidInputOperator.execute();

        assertThat(valuesStack.size(), is(2));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("20"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("10"));
    }

    public void shouldSetFurtherProcessingToFalseAfterExecute() {
        Stack<BigDecimal> valuesStack = invalidInputOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(30));
        valuesStack.push(BigDecimal.valueOf(15));

        assertThat(invalidInputOperator.valuesStack.isFurtherProcessingAllowed(), is(true));

        invalidInputOperator.execute();

        assertThat(invalidInputOperator.valuesStack.isFurtherProcessingAllowed(), is(false));
    }

}