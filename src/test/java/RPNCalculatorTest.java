import javafx.scene.Scene;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Test
public class RPNCalculatorTest {

    private RPNCalculator rpnCalculator;

    @BeforeMethod
    public void setUp() {
        rpnCalculator = new RPNCalculator();
    }

    public void shouldGetTokenisedStringFromUserInput() {
        String userInput = "5 6 7 / 12 3";
        String[] tokenisedString = rpnCalculator.getTokenisedStringFromUserInput(userInput);
        assertThat(tokenisedString.length, is(6));
        assertThat(tokenisedString[0], is("5"));
        assertThat(tokenisedString[1], is("6"));
        assertThat(tokenisedString[2], is("7"));
        assertThat(tokenisedString[3], is("/"));
        assertThat(tokenisedString[4], is("12"));
        assertThat(tokenisedString[5], is("3"));
    }

    public void shouldGetTokenisedStringFromUserInputWithConsecutiveSpaces() {
        String userInput = "5     3      *   12     3    /    ";
        String[] tokenisedString = rpnCalculator.getTokenisedStringFromUserInput(userInput);
        assertThat(tokenisedString.length, is(6));
        assertThat(tokenisedString[0], is("5"));
        assertThat(tokenisedString[1], is("3"));
        assertThat(tokenisedString[2], is("*"));
        assertThat(tokenisedString[3], is("12"));
        assertThat(tokenisedString[4], is("3"));
        assertThat(tokenisedString[5], is("/"));
    }

}