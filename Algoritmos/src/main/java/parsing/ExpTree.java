package parsing;

import java.util.Scanner;

public class ExpTree {
    private Node root;

    public ExpTree(String infija) {
        // token analyzer
        Scanner inputScanner = new Scanner(infija).useDelimiter("\\n");
        String line = inputScanner.nextLine();
        inputScanner.close();

        buildTree(line);
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

    public double evaluate(){
        return evaluateRec(root);
    }

    private double evaluateRec(Node node){
        if (node.left == null && node.right == null){
            return Double.valueOf(node.data);
        } else {
            double left = evaluateRec(node.left);
            double right = evaluateRec(node.right);
            return operate(left, right, node.data);
        }
    }

    public String inOrder(){
        StringBuilder builder = new StringBuilder();
        return inOrderRec(root, builder).toString();
    }

    private StringBuilder inOrderRec(Node node, StringBuilder builder){
        if (node.left == null && node.right == null){
            builder.append(node.data).append(" ");
            return builder;
        } else {
            builder.append("(").append(" ");
            builder = inOrderRec(node.left, builder);
            builder.append(node.data).append(" ");
            builder = inOrderRec(node.right, builder);
            builder.append(")").append(" ");
            return builder;
        }
    }

    public String postOrder(){
        StringBuilder builder = new StringBuilder();
        return postOrderRec(root, builder).toString();
    }

    private StringBuilder postOrderRec(Node node, StringBuilder builder){
        if (node.left == null && node.right == null){
            builder.append(node.data).append(" ");
            return builder;
        } else {
            builder = postOrderRec(node.left, builder);
            builder = postOrderRec(node.right, builder);
            builder.append(node.data).append(" ");
            return builder;
        }
    }

    public String preOrder(){
        StringBuilder builder = new StringBuilder();
        return preOrderRec(root, builder).toString();
    }

    private StringBuilder preOrderRec(Node node, StringBuilder builder){
        if (node.left == null && node.right == null){
            builder.append(node.data).append(" ");
            return builder;
        } else {
            builder = preOrderRec(node.left, builder.append(node.data).append(" "));
            return preOrderRec(node.right, builder);
        }
    }

    public ExpTree() {
        System.out.print("Introduzca la expresión en notación infija con todos los parentesis y blancos: ");

        // token analyzer
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        String line= inputScanner.nextLine();
        inputScanner.close();

        buildTree(line);
    }

    private void buildTree(String line)
    {
        // space separator among tokens
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
        root= new Node(lineScanner);
        lineScanner.close();
    }



    static final class Node {
        private String data;
        private Node left, right;

        private Scanner lineScanner;

        public Node(Scanner theLineScanner) {
            lineScanner= theLineScanner;

            Node auxi = buildExpression();
            data= auxi.data;
            left= auxi.left;
            right= auxi.right;

            if (lineScanner.hasNext() )
                throw new RuntimeException("Bad expression");
        }

        private Node() 	{
        }

        private Node buildExpression() {
            Node node = new Node();
            // ( ( ( 4 - 4 ) * 3 + 5 ) * 2 )
            if (lineScanner.hasNext("\\(")) {
                lineScanner.next();
                node.left = buildExpression();

                if (!lineScanner.hasNext())
                    throw new RuntimeException("Bad expression");
                node.data = lineScanner.next();
                if (!node.data.matches("[-*/+^]"))
                    throw new RuntimeException("Bad expression");

                node.right = buildExpression();

                if (!lineScanner.hasNext("\\)"))
                    throw new RuntimeException("Bad expression");
                else
                    lineScanner.next();
                return node;
            }
            if (!lineScanner.hasNext())
                throw new RuntimeException("Bad expression");
            node.data = lineScanner.next();
            if (!node.data.matches("^-?[0-9]*(\\.[0-9]*)?"))
                throw new RuntimeException("Bad expression");
            return node;
        }


    }  // end Node class
}
