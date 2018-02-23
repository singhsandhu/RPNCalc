import com.rpn.stack.RPNStack;
import java.text.DecimalFormat;
import org.hamcrest.core.Is;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class RPNCalculatorIntegrationTest {

    private RPNCalculator calculator;
    private static final DecimalFormat DISPLAY_FMT = new DecimalFormat("0.##########");

    @BeforeMethod
    public void setUp() {
        calculator = new RPNCalculator();
    }

    @AfterMethod
    public void tearDown() {
        calculator.getStackInstance().getValuesStack().clear();
        calculator.getStackInstance().getInstructionsStack().clear();
    }

    public void resultShouldMatchWithExpectedOutput_1() {
        calculator.evaulateAndPrintStack("15 6 - sqrt");
        RPNStack stack = calculator.getStackInstance();
        assertThat(stack.getValuesStack().size(), is(1));
        assertThat(DISPLAY_FMT.format(stack.getValuesStack().pop().doubleValue()), Is.is("3"));
    }

    public void resultShouldMatchWithExpectedOutput_2() {
        calculator.evaulateAndPrintStack("7 12 2 /");
        RPNStack stack = calculator.getStackInstance();
        assertThat(stack.getValuesStack().size(), is(2));
        assertThat(DISPLAY_FMT.format(stack.getValuesStack().pop().doubleValue()), Is.is("6"));
        assertThat(DISPLAY_FMT.format(stack.getValuesStack().pop().doubleValue()), Is.is("7"));
    }

    public void resultShouldMatchWithExpectedOutput_3() {
        calculator.evaulateAndPrintStack("1 2 3 4 5");
        calculator.evaulateAndPrintStack("*");
        calculator.evaulateAndPrintStack("clear 3 4 -");
        RPNStack stack = calculator.getStackInstance();
        assertThat(stack.getValuesStack().size(), is(1));
        assertThat(DISPLAY_FMT.format(stack.getValuesStack().pop().doubleValue()), Is.is("-1"));
    }

    public void resultShouldMatchWithExpectedOutput_4() {
        calculator.evaulateAndPrintStack("5 4 3 2");
        calculator.evaulateAndPrintStack("undo undo *");
        calculator.evaulateAndPrintStack("5 *");
        calculator.evaulateAndPrintStack("undo");
        RPNStack stack = calculator.getStackInstance();
        assertThat(stack.getValuesStack().size(), is(2));
        assertThat(DISPLAY_FMT.format(stack.getValuesStack().pop().doubleValue()), Is.is("5"));
        assertThat(DISPLAY_FMT.format(stack.getValuesStack().pop().doubleValue()), Is.is("20"));
    }

    public void resultShouldMatchWithExpectedOutput_5() {
        calculator.evaulateAndPrintStack("1 2 3 * 5 + * * 6 5");
        RPNStack stack = calculator.getStackInstance();
        assertThat(stack.getValuesStack().size(), is(1));
        assertThat(DISPLAY_FMT.format(stack.getValuesStack().pop().doubleValue()), Is.is("11"));
    }


}