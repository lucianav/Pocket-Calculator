public class ExpressionParser {

	public float eval(String expression) throws 
							SyntacticException, EvaluatorException {

		/**
		 * impartirea expresiei in token-uri, eliminand toate spatiile,
		 * indiferent de tipul sau numarul lor
		 */
		String[] tokens = expression.split("\\s+");

		/**
		 * creearea arborelui de parsare
		 */
		ParseTree mytree = new ParseTree(tokens);
		
		/**
		 *evaluarea expresiei cu ajutorul patternului Visitor
		 */
		TreeEvalVisitor v = new TreeEvalVisitor();

		return (float) mytree.getRoot().accept(v);
	}

}
