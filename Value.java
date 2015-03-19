/**
 * Clasa nodului valoare numerica din arborele de parsare
 * exitinde clasa de baza Node
 * @author luciana
 *
 */
public class Value extends Node {

	private double value;	//valoare numerica

	/**
	 * Constructor cu parametru stringul valorii numerice
	 * @param node
	 * @throws SyntacticException
	 */
	public Value(String node) throws SyntacticException {
		super(node);
		try {
			value = Double.parseDouble(node);
		} catch (NumberFormatException e) {
			throw new SyntacticException();
		}
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * metoda de vizitare, pentru aplicarea patternului Visitor
	 */
	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}

}
