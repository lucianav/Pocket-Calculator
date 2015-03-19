/**
 * Clasa nodului operator din arborele de parsare,
 * extinde clasa de baza Node
 * 
 * @author luciana
 *
 */
public class Operator extends Node {

	private OperatorType operatorType;	//tip operator
	private int numberOfSons;	//numar operanzi
	private int priority;	//precedenta

	/**
	 * Constructor cu parametru stringul operatorului
	 * 
	 * In functie de operator, i se seteaza tipul,
	 * precedenta si numarul de operanzi
	 * @param node
	 * @throws SyntacticException
	 */
	public Operator(String node) throws SyntacticException {
		super(node);
		//in functie de tip, se seteaza informatia 
		switch (node) {
		case "+":
			operatorType = OperatorType.ADD;
			priority = 1;
			numberOfSons = 2;
			break;
		case "-":
			operatorType = OperatorType.SUBSTRACT;
			priority = 1;
			numberOfSons = 2;
			break;
		case "*":
			operatorType = OperatorType.MULTIPLY;
			priority = 2;
			numberOfSons = 2;
			break;
		case "/":
			operatorType = OperatorType.DIVIDE;
			priority = 2;
			numberOfSons = 2;
			break;
		case "^":
			operatorType = OperatorType.POWER;
			priority = 3;
			numberOfSons = 2;
			break;
		case "log":
			operatorType = OperatorType.LOG;
			priority = 4;
			numberOfSons = 1;
			break;
		case "sqrt":
			operatorType = OperatorType.SQRT;
			priority = 4;
			numberOfSons = 1;
			break;
		case "sin":
			operatorType = OperatorType.SIN;
			priority = 4;
			numberOfSons = 1;
			break;
		case "cos":
			operatorType = OperatorType.COS;
			priority = 4;
			numberOfSons = 1;
			break;
		}
	}

	/**
	 * metoda de vizitare, pentru aplicarea patternului Visitor
	 */
	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}

	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	public int getNumberOfSons() {
		return numberOfSons;
	}

	public void setNumberOfSons(int n) {
		numberOfSons = n;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
