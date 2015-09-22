/**
 * 
 */
package org.app.ds.applications;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author anandm
 * 
 */
public class ArithmeticExpression {

    static Map<String, Integer> precedenceMap = new HashMap<String, Integer>();

    static {
        precedenceMap.put("(", 8);
        precedenceMap.put(")", 8);
        precedenceMap.put("/", 7);
        precedenceMap.put("*", 7);
        precedenceMap.put("+", 6);
        precedenceMap.put("-", 6);
    }

    private enum Type {
        OPERATOR, LITERAL;
    }

    private static boolean isOperator(char token) {
        return (token == '*' || token == '/' || token == '(' || token == ')'
                || token == '+' || token == '-');
    }

    private class Component {

        private Type type;

        private String value;

        private int precedence;

        private Component(String token) {
            this.value = token;
            if (ArithmeticExpression.isOperator(token.charAt(0))) {
                type = Type.OPERATOR;
                precedence = precedenceMap.get(token);
            }
            else {
                type = Type.LITERAL;
                precedence = -1;
            }

        }

        public boolean isOpeningParenthesis() {
            return isOperator() && value.equals("(");
        }

        public boolean isClosingParenthesis() {
            return isOperator() && value.equals(")");
        }

        public boolean isOperator() {
            return type == Type.OPERATOR;
        }

        public Component operation(Component a, Component b) {
            Double aValue = Double.parseDouble(a.value);
            Double bValue = Double.parseDouble(b.value);
            if (value.equals("*")) {
                return new Component(String.valueOf(aValue * bValue));
            }

            if (value.equals("*")) {
                return new Component(String.valueOf(aValue * bValue));
            }

            if (value.equals("/")) {
                return new Component(String.valueOf(aValue / bValue));
            }

            if (value.equals("+")) {
                return new Component(String.valueOf(aValue + bValue));
            }

            if (value.equals("-")) {
                return new Component(String.valueOf(aValue - bValue));
            }

            return null;
        }

    }

    private class ComponentScanner {

        private String expression;

        private int position;

        /**
         * @param expression
         */
        public ComponentScanner(String expression) {
            this.expression = expression;
            position = 0;
        }

        public Component nextComponent() {

            StringBuilder tokenBuilder = new StringBuilder();

            while (true) {

                if (position < expression.length()) {

                    char token = expression.charAt(position);

                    if (Character.isWhitespace(token)) {
                        if (tokenBuilder.length() > 0) {
                            return new Component(tokenBuilder.toString());
                        }

                        position++;
                    }
                    else {
                        if (isOperator(token)) {
                            if (tokenBuilder.length() > 0) {
                                return new Component(tokenBuilder.toString());
                            }
                            else {
                                position++;
                                return new Component(Character.toString(token));
                            }

                        }
                        else {
                            tokenBuilder.append(token);
                            position++;
                        }
                    }
                }
                else {
                    break;
                }

            }

            return null;

        }
    }

    private String expression;

    private List<Component> components;

    /**
     * @param expression
     */
    public ArithmeticExpression(String expression) {
        super();
        this.expression = expression;
        this.components = components(this.expression);
    }

    private List<Component> components(String expression) {

        List<Component> components = new ArrayList<Component>();

        ComponentScanner componentScanner = new ComponentScanner(expression);

        Component component = null;
        while ((component = componentScanner.nextComponent()) != null) {
            // System.out.println(component.value);
            components.add(component);
        }

        return components;
    }

    private List<Component> postFix(List<Component> components) {
        Stack<Component> operatorStack = new Stack<Component>();

        operatorStack.push(new Component("("));

        List<Component> postFixComponents = new ArrayList<Component>();

        List<Component> tempComponents = new ArrayList<Component>(components);
        tempComponents.add(new Component(")"));

        for (Component component : tempComponents) {
            if (component.isOperator()) {
                if (component.isOpeningParenthesis()) {
                    // opening parenthesis push on operator stack
                    operatorStack.push(component);
                }
                else {
                    if (component.isClosingParenthesis()) {

                        // closing parenthesis
                        // pop and add to postfix untill we get a closing
                        // parenthesis

                        while (!operatorStack.isEmpty()
                                && !operatorStack.peek().isOpeningParenthesis()) {
                            postFixComponents.add(operatorStack.pop());
                        }
                        // pop closing parenthesis
                        operatorStack.pop();
                    }
                    else {
                        // while there are low precedence operators on top of
                        // stack
                        // pop and add to post fix
                        while (!operatorStack.isEmpty()
                                && operatorStack.peek().precedence < component.precedence) {
                            postFixComponents.add(operatorStack.pop());
                        }
                        // push component on stack
                        operatorStack.push(component);
                    }
                }
            }
            else {
                postFixComponents.add(component);
            }

        }

        return postFixComponents;

    }

    private List<Component> preFix(List<Component> components) {

        List<Component> tempComponents = new ArrayList<Component>(components);

        Collections.reverse(tempComponents);

        for (Component component : tempComponents) {

            if (component.isOpeningParenthesis()) {
                component.value = ")";
            }
            else {
                if (component.isClosingParenthesis()) {
                    component.value = "(";
                }
            }
        }

        return postFix(tempComponents);

    }

    public String postFixExpression() {

        StringBuilder postFixExpressionBuilder = new StringBuilder();
        for (Component component : postFix(components)) {
            postFixExpressionBuilder.append(component.value).append(" ");
        }
        return postFixExpressionBuilder.toString().trim();
    }

    public String preFixExpression() {
        StringBuilder preFixExpressionBuilder = new StringBuilder();
        for (Component component : preFix(components)) {
            preFixExpressionBuilder.append(component.value).append(" ");
        }
        return preFixExpressionBuilder.toString().trim();
    }

    public String getExpression() {
        return expression;
    }

    public double evaluate() {
        Stack<Component> stack = new Stack<Component>();

        List<Component> postFix = postFix(components);

        for (Component component : postFix) {
            if (component.isOperator()) {
                Component b = stack.pop();

                Component a = stack.pop();

                stack.push(component.operation(a, b));
            }
            else {
                stack.push(component);
            }
        }

        return Double.parseDouble(stack.pop().value);
    }

    @Override
    public String toString() {
        return "ArithmeticExpression [expression=" + expression + "]";
    }

    public static void main(String[] args) {
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression(
                " ( (3+ 5.43) * (4-2))/5 ");

        System.out.println(arithmeticExpression.postFixExpression());

        System.out.println(arithmeticExpression.preFixExpression());

        System.out
                .println(arithmeticExpression.evaluate() == ((3 + 5.43) * (4 - 2)) / 5);
    }
}
