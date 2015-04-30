/**
 * 
 */
package org.app.ds.applications;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anandm
 * 
 */
public class ArithmeticExpression {

	private String expression;
	private List<String> tokens;

	/**
	 * @param expression
	 */
	public ArithmeticExpression(String expression) {
		super();
		validate(expression);
		this.expression = expression;
		this.tokens = tokens(expression);
	}

	private interface Component {
		enum Type {
			OPERATOR, LITERAL;
		}

		Type getType();

		String getValue();

		int getPrecedence();
	}

	private class Literal implements Component {

		private String value;

		/**
		 * @param value
		 */
		public Literal(String value) {
			super();
			this.value = value;
		}

		@Override
		public Type getType() {

			return Type.LITERAL;
		}

		@Override
		public String getValue() {

			return value;
		}

		@Override
		public int getPrecedence() {
			throw new UnsupportedOperationException();
		}

	}
	
	private class Operator implements Component {

		private String value;
		private int precedence;

		/**
		 * @param value
		 */
		public Literal(String value) {
			super();
			this.value = value;
			if(value == "(")
		}

		@Override
		public Type getType() {

			return Type.LITERAL;
		}

		@Override
		public String getValue() {

			return value;
		}

		@Override
		public int getPrecedence() {
			throw new UnsupportedOperationException();
		}
		
	}

	private private static boolean validate(String expression) {
		return true;
	}

	private static List<String> tokens(String expression) {

		List<String> tokens = new ArrayList<String>();

		int i = 0;
		int start = 0;

		while (i < expression.length()) {

			if (isOperator(expression.charAt(i))) {

				if (start == i) {
					tokens.add(expression.substring(i, i + 1));

				} else {
					tokens.add(expression.substring(start, i));
					tokens.add(expression.substring(i, i + 1));
				}

				i++;

				start = i;

			} else {
				i++;
			}

		}

		if (start != i) {
			tokens.add(expression.substring(start, expression.length()));
		}

		return tokens;
	}

	private static boolean isOperator(char token) {
		return (token == '*' || token == '/' || token == '(' || token == ')'
				|| token == '+' || token == '-');
	}

	public String postFixExpression() {

		for (String iterable_element : iterable) {

		}

		return null;
	}

	public String preFixExpression() {
		return null;
	}

	public String getExpression() {
		return expression;
	}

	public double evaluate() {
		return 0.0;
	}

	@Override
	public String toString() {
		return "ArithmeticExpression [expression=" + expression + ", tokens="
				+ tokens + "]";
	}

	public static void main(String[] args) {
		ArithmeticExpression arithmeticExpression = new ArithmeticExpression(
				"((3+5.43)*(4-2))/5");

		System.out.println(arithmeticExpression);
	}

}
