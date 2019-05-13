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

    public String preorder(){
        StringBuilder builder = new StringBuilder();
        builder = preorderRec(root, builder);
        return builder.toString();
    }

    private StringBuilder preorderRec(Node node, StringBuilder builder){
        if (node.left == null && node.right == null){
            builder.append(node.data).append(" ");
            return builder;
        } else {
            builder = preorderRec(node.left, builder.append(node.data).append(" "));
            return preorderRec(node.right, builder);
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
            if (!node.data.matches("^-\\?[0-9]\\*(\\.[0-9]\\*)\\?"))
                throw new RuntimeException("Bad expression");
            return node;
        }


    }  // end Node class
}
