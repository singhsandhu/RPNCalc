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
public class SqrtOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private SqrtOperator sqrtOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        sqrtOperator = new SqrtOperator(userInput);
        when(userInput.getValue()).thenReturn("sqrt");
        when(userInput.getPosition()).thenReturn(1);
    }

    @AfterMethod
    public void tearDown() {
        sqrtOperator.valuesStack.getValuesStack().clear();
        sqrtOperator.valuesStack.getInstructionsStack().clear();
        sqrtOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldGiveSquareRootOfLastOperandAndPushToStack() {
        Stack<BigDecimal> valuesStack = sqrtOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(15));
        valuesStack.push(BigDecimal.valueOf(25));

        sqrtOperator.execute();

        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("5"));
    }

    public void shouldNotDoAnythingToStackIfNotEnoughArguments() {
        Stack<BigDecimal> valuesStack = sqrtOperator.valuesStack.getValuesStack();

        sqrtOperator.execute();

        assertThat(valuesStack.size(), is(0));
    }

    public void shouldNotDoAnythingToStackIfFurtherProcessingIsNotAllowed() {
        Stack<BigDecimal> valuesStack = sqrtOperator.valuesStack.getValuesStack();
        sqrtOperator.valuesStack.setFurtherProcessingAllowed(false);

        valuesStack.push(BigDecimal.valueOf(15));
        valuesStack.push(BigDecimal.valueOf(25));

        sqrtOperator.execute();

        assertThat(valuesStack.size(), is(2));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("25"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("15"));
    }

    public void shouldPushToInstructionStackAfterExecute() {
        Stack<BigDecimal> valuesStack = sqrtOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(15));
        valuesStack.push(BigDecimal.valueOf(25));

        sqrtOperator.execute();

        assertThat(sqrtOperator.valuesStack.getInstructionsStack().pop(), is(userInput));
    }

}