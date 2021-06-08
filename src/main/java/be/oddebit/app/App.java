package be.oddebit.app;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

    private static final Map<String, BinaryOperator<Double>> binaryOperators = new HashMap<>();
    private static final Map<String, UnaryOperator<Double>> unaryOperators = new HashMap<>();

    public static void main(String[] args) {

        initOperatorsMaps();

        System.out.println("Enter a RPN expression :");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println("Result :");
        System.out.println(calculate(input));
    }

    private static void initOperatorsMaps() {

        binaryOperators.put("+", Double::sum);
        binaryOperators.put("-", (n, m) -> n - m);
        binaryOperators.put("*", (n, m) -> n * m);
        binaryOperators.put("/", (n, m) -> n / m);

        unaryOperators.put("sqrt", Math::sqrt);
    }

    private static double calculate(String expression) {

        String[] array = expression.split(" ");

        Stack<Double> pile = new Stack<>();

        for (String symbol : array) {

            if (binaryOperators.containsKey(symbol)) {
                double n = pile.pop();
                double m = pile.pop();
                double result = binaryOperators.get(symbol).apply(n, m);
                pile.push(result);

            } else if (unaryOperators.containsKey(symbol)) {
                double n = pile.pop();
                double result = unaryOperators.get(symbol).apply(n);
                pile.push(result);

            } else {
                pile.push(Double.parseDouble(symbol));
            }
        }

        return pile.pop();
    }
}
