package parsing;

import DataStructures.Stack;

import java.util.List;
import java.util.Scanner;

public class Evaluator {

    private Stack<Double> stack = new Stack<>();

    public Double evaluate() {
        //Reads the first line
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresión en notación postfija: ");
        inputScanner.hasNextLine();

        String line = inputScanner.nextLine();

        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        Double value;
        boolean hasError = false;

        //Iterate all the token
        while (lineScanner.hasNext()) {

            //Get the next token
            String token = lineScanner.next();

            //Try to parse the number into a double
            //Verify if operator then
            try {

                //Parse the number
                value = Double.parseDouble(token);

                //If the number was parsed, push it into the stack
                stack.push(value);

            } catch (NumberFormatException exout) {
                try {
                    //Verify if the token is an operator
                    if (token.matches("[+\\-*/]")) {

                        //Make stack pops
                        Double right = stack.pop();
                        Double left = stack.pop();

                        //Make the operation
                        stack.push(operate(left, right, token));
                    } else {
                        System.out.println("Invalid token: " + token);
                        hasError = true;
                    }
                } catch (UnsupportedOperationException ex) {
                    System.out.println(ex.getMessage());
                    hasError = true;
                }
            }
        }

        //Check if there was an error in the operation and parsing
        if (hasError)
            throw new RuntimeException("Error in the operation");

        return stack.pop();
    }

    private double operate(Double left, Double right, String op){
        Double result = 0d;
        switch (op){
            case "+":
                result =  left + right;
                break;
            case "-":
                result =  left - right;
                break;
            case "*":
                result =  left * right;
                break;
            case "/":
                if (right == 0)
                    throw new UnsupportedOperationException("Cannot divide by 0");
                result =  left / right;
                break;
        }
        return result;
    }
}
