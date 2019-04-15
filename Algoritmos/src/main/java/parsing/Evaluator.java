package parsing;

import DataStructures.Stack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Evaluator {

    /**
     * Stack for holding the postfix expression variables
     */
    private Stack<Double> stack = new Stack<>();

    /**
     * String to hold the infix expression for parsing
     */
    private String expression;

    /**
     * Evaluates whether the first operator has grater precedence than the second one
     * Instead of a precedence table, uses regexp to determine precedence
     * @param op1 First operator, it has to be the one in the stack
     * @param op2 Second operator, the one recovered from the expression
     * @return boolean representing if the first operator has greater precedence than the second
     */
    private boolean operatorHasGreaterPrecedence(String op1, String op2){
        return (!(op1.matches("[+\\-]") && op2.matches("[/*]")) && !op2.matches("[\\^]")) && !op1.matches("[(]");
    }

    /**
     * Evaluates if the given string is an operator
     * All operators should be added here
     * @param str possible operator
     * @return boolean representing if the given string is an operator or not
     */
    private boolean isOperator(String str){
        return str.matches("[\\^+*\\-/]");
    }

    /**
     * Retrieves input from the standard input
     * If there is an error in the parsing
     * @return
     */
    public String infixToPostfix(){
        //Initialize the scanner with the sepparator tokens
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        //User instructions
        System.out.print("Write expression in infix notation: ");
        //Tell the scanner that there is going to be another line
        inputScanner.hasNextLine();
        //Read the written line
        String line = inputScanner.nextLine();
        //Tell the scanner to separate using the given delimiter
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
        //Final expression
        StringBuilder finalExpression = new StringBuilder();
        //Stack for operators and parenthesis
        Stack<String> stack = new Stack<>();
        //Holding the token
        String token;
        //Holding the value
        Double value;
        //Holding the error status
        boolean hasError = false;

        //Iterate all the token
        while (lineScanner.hasNext()) {
            //Get next line
            token = lineScanner.next();
            try {
                //Parse the number
                value = Double.parseDouble(token);
                //If the parsing is successful, it adds the number to the expression
                finalExpression.append(value).append(" ");
            }
            //If the string is not a number, check if it is operator or parenthesis
            catch (NumberFormatException exout) {
                try {
                    //Verify if the token is an operator
                    if (isOperator(token)) {
                        //If the stack is empty or the operator has less precedence, push it to the stack
                        if (stack.isEmpty() || !operatorHasGreaterPrecedence(stack.peek(), token))
                            stack.push(token);
                        else {
                            //Iterate until it finds an operator with less precedence
                            while(!stack.isEmpty() && operatorHasGreaterPrecedence(stack.peek(), token)){
                                finalExpression.append(stack.pop()).append(" ");
                            }
                            //Push the operator from the expression
                            stack.push(token);
                        }
                    }
                    //In case it finds a closing parenthesis
                    else if (token.matches("[)]")){
                        //Iterate until it finds its matching parenthesis
                        while (!stack.isEmpty() && !stack.peek().equals("(")){
                            finalExpression.append(stack.pop()).append(" ");
                        }
                        //Check if there is a opening parenthesis to match
                        //Throw error if no match
                        if (stack.peek() != null)
                            stack.pop();
                        else{
                            throw new RuntimeException("Missing (");
                        }
                    }
                    //In case it finds an opening parenthesis
                    //Push it in the stack
                    //Throw exception if no match
                    else if (token.matches("[(]")){
                        stack.push(token);
                    } else {
                        throw new RuntimeException("Invalid token: " + token);
                    }
                } catch (RuntimeException ex) {
                    System.out.println(ex.getMessage());
                    hasError = true;
                }
            }
        }
        try {
            //check if there was an error during the reading
            if (!hasError){
                while(!stack.isEmpty()){
                    //If there is an opening parenthesis, throw an error, its missing its closing one
                    if (stack.peek().equals("("))
                        throw new RuntimeException("Missing )");
                    else
                        finalExpression.append(stack.pop()).append(" ");
                }
                expression = finalExpression.toString();
                return expression;
            } else {
                expression = "";
                return "";
            }
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
            expression = "";
            return "";

        }
    }

    public Double evaluate() {
        if (expression == null || expression.equals(""))
            throw new RuntimeException("No expression to evaluate");

        String[] tokens = expression.split(" +");

        Double value;
        String token;
        boolean hasError = false;

        //Iterate all the token
        for (int i = 0; i < tokens.length; i++) {

            //Get the next token
            token = tokens[i];

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
                    if (isOperator(token)) {

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
            case "^":
                result =  Math.pow(left, right);
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
