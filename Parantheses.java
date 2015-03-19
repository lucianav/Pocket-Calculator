/**
 * Clasa nodului paranteza, folosita
 * in constructia arborelui
 * extinde clasa de baza Node
 * @author luciana
 *
 */
public class Parantheses extends Node {
	
	private ParanthesesType type;	//tipul parantezei
	
	/**
	 * Constructor cu parametru stringul parantezei
	 * @param node
	 * @throws SyntacticException
	 */
	public Parantheses(String node) throws SyntacticException {
		super(node);
		if (node.equals("(")) {
			type = ParanthesesType.LEFT;
		}
		else{
			type = ParanthesesType.RIGHT;
		}
	}
	
	public ParanthesesType getType() {
		return type;
	}

	public void setType(ParanthesesType type) {
		this.type = type;
	}

	/**
	 * metoda de vizitare, pentru aplicarea patternului Visitor
	 */
	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}
