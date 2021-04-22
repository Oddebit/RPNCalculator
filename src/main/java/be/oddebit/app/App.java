package be.oddebit.app;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class App {

    private static final Map<String, BinaryOperator<Double>> binaryOperators = new HashMap<>();
    private static final Map<String, UnaryOperator<Double>> unaryOperators = new HashMap<>();

    public static void main(String[] args) {

        initOperatorsMaps();

        System.out.println("Enter a RPN expression :");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] inputArray = input.split(" ");
        List<String> inputList = new ArrayList<>(Arrays.asList(inputArray));

        System.out.println("Result :");
        System.out.println(calculate(inputList));
    }

    private static void initOperatorsMaps() {

        binaryOperators.put("+", Double::sum);
        binaryOperators.put("-", (n, m) -> n - m);
        binaryOperators.put("*", (n, m) -> n * m);
        binaryOperators.put("/", (n, m) -> n / m);

        unaryOperators.put("sqrt", Math::sqrt);
    }

    private static double calculate(List<String> list) {

        Stack<Double> pile = new Stack<>();

        for (String s : list) {

            if (binaryOperators.containsKey(s)) {
                double n1 = pile.pop();
                double n2 = pile.pop();
                double result = binaryOperators.get(s).apply(n1, n2);
                pile.push(result);

            } else if (unaryOperators.containsKey(s)) {
                double n = pile.pop();
                double result = unaryOperators.get(s).apply(n);
                pile.push(result);

            } else {
                pile.push(Double.parseDouble(s));
            }
        }

        return pile.pop();
    }
}
