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
public class UndoOperatorTest {

    @Mock
    private UserInput userInput;

    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    private UndoOperator undoOperator;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        undoOperator = new UndoOperator(userInput);
        when(userInput.getValue()).thenReturn("undo");
        when(userInput.getPosition()).thenReturn(3);
    }

    @AfterMethod
    public void tearDown() {
        undoOperator.valuesStack.getValuesStack().clear();
        undoOperator.valuesStack.getInstructionsStack().clear();
        undoOperator.valuesStack.setFurtherProcessingAllowed(true);
    }

    public void shouldAddLastTwoOperandsAndPushToStack() {
        Stack<BigDecimal> valuesStack = undoOperator.valuesStack.getValuesStack();

        valuesStack.push(BigDecimal.valueOf(312));
        valuesStack.push(BigDecimal.valueOf(123));

        undoOperator.valuesStack.getInstructionsStack().push(new UserInput("312", 0));
        undoOperator.valuesStack.getInstructionsStack().push(new UserInput("123", 1));

        new AdditionOperator(new UserInput("+", 2)).execute();

        undoOperator.execute();

        assertThat(undoOperator.valuesStack.getValuesStack().size(), is(2));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("123"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("312"));
    }

    public void shouldNotDoAnythingIfStackIsEmpty() {
        Stack<BigDecimal> valuesStack = undoOperator.valuesStack.getValuesStack();

        undoOperator.execute();

        assertThat(valuesStack.size(), is(0));
    }

    public void shouldNotDoAnythingToStackIfFurtherProcessingIsNotAllowed() {
        Stack<BigDecimal> valuesStack = undoOperator.valuesStack.getValuesStack();
        undoOperator.valuesStack.setFurtherProcessingAllowed(false);

        valuesStack.push(BigDecimal.valueOf(1));
        valuesStack.push(BigDecimal.valueOf(3));
        valuesStack.push(BigDecimal.valueOf(4));

        undoOperator.execute();

        assertThat(valuesStack.size(), is(3));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("4"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("3"));
        assertThat(DISPLAY_FMT.format(valuesStack.pop().doubleValue()), is("1"));

    }


}