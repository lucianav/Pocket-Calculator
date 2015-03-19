public class EvaluatorException extends RuntimeException {

	private static final long serialVersionUID = 5729607299984502799L;

	public static String NEG_SQRT = "negative value passed to square root";
	public static String NEG_LOG = "negative value passed to logarithm";
	public static String ZERO_LOG = "expression under logarithm evaluates to zero";
	public static String ZERO_DIV = "division by zero";

	/**
	 * Constructor implicit
	 */
	public EvaluatorException() {	
	}
	
	/**
	 * Constructor cu parametru care indica tipul erorii
	 * @param token
	 */
	public EvaluatorException(String token) {
		switch (token) {
		case "/":
			System.out.println(ZERO_DIV);
			break;
		case "sqrt":
			System.out.println(NEG_SQRT);
			break;
		case "log-":
			System.out.println(NEG_LOG);
			break;
		case "log0":
			System.out.println(ZERO_LOG);
			break;
		}
	}
}
