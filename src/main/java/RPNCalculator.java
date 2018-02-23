import com.rpn.input.UserInput;
import com.rpn.mapper.OperatorMapper;
import com.rpn.stack.RPNStack;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.IntStream;

import static com.rpn.stack.RPNStack.getInstance;

public class RPNCalculator {

    public static void main(String... args) {
        new RPNCalculator().process();
    }

    public void process() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());

        System.out.println("*********** You are using RPN calculator ***********");
        System.out.println("*********** \"Ctrl + D\" to Exit ***********");

        while (scanner.hasNext()) {
            evaulateAndPrintStack(scanner.nextLine());
        }
    }

    public void evaulateAndPrintStack(String userProvidedInput) {
        String[] tokenisedString = userProvidedInput.split("\\s");
        OperatorMapper operatorMapper = new OperatorMapper();

        IntStream.range(0, tokenisedString.length)
                .mapToObj(i -> new UserInput(tokenisedString[i], i))
                .map(operatorMapper)
                .forEach(operator -> operator.execute());

        getStackInstance().printStack();
    }

    RPNStack getStackInstance() {
        return getInstance();
    }

}
