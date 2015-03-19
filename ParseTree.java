import java.util.Stack;
/**
 * Clasa a arborelui de parsare
 * @author luciana
 *
 */
public class ParseTree {

	private Node root;	//radacina arborelui

	/**
	 * Constructor arbore cu parametru vectorul de token-uri ale expresiei
	 * @param tokens
	 * @throws SyntacticException
	 */
	public ParseTree(String[] tokens) throws SyntacticException {

		/**
		 * Fiecare element al expresiei este transformat intr-un
		 * nod, in functie de tipul sau 
		 */
		Node[] nodes = new Node[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			if ("+-*/^logsqrtsincos".indexOf(tokens[i]) >= 0) {
				nodes[i] = new Operator(tokens[i]); //operatori
			} else {
				if ("()".indexOf(tokens[i]) >= 0) {
					nodes[i] = new Parantheses(tokens[i]);	//paranteze
				} else {
					nodes[i] = new Value(tokens[i]);	//valori numerice
				}
			}
		}

		Stack<Node> operands = new Stack<Node>();
		Stack<Node> operators = new Stack<Node>();

		//se trateaza separat cazul de minus unar, 
		//acesta fiind interpretat initial ca minus binar
		if (tokens[0].equals("-")) {
			((Operator) nodes[0]).setNumberOfSons(1);
			((Operator) nodes[0]).setPriority(4);
			((Operator) nodes[0]).setOperatorType(OperatorType.NEGATIVE);
		}
		for (int i = 1; i < nodes.length; i++) {
			if (tokens[i].equals("-") && tokens[i - 1].equals("(")) {
				((Operator) nodes[i]).setNumberOfSons(1);
				((Operator) nodes[i]).setPriority(4);
				((Operator) nodes[i]).setOperatorType(OperatorType.NEGATIVE);
			}
		}

		//pentru fiecare nod al expresiei
		for (Node node : nodes) {
			//valorile se pun in stiva de operanzi
			if (node instanceof Value) {
				operands.push(node);
				continue;
			}
			//operatorii se compara si daca este necesar, se evalueaza
			if (node instanceof Operator) {
				Operator op = (Operator) node;
				
				//daca stiva operatorilor este goala,
				//se introduce operatorul curent
				if (operators.isEmpty()) {
					operators.push(node);
				} else {
					//cat timp exista un alt operator in varful stivei
					while (operators.peek() instanceof Operator) {
						Operator top = ((Operator) operators.peek());

						//daca precedenta varfului este mai mica,
						//se introduce operatorul curent in stiva
						if (op.getPriority() > top.getPriority()) {
							break;

						} else {

							//pentru operator curent cu precedenta egala
							//cu a varfului stivei si varf stiva asociativ
							//dreapta, se trece direct la inserare
							if (((op.getOperatorType() == OperatorType.POWER)
							&& (top.getOperatorType() == OperatorType.POWER))
							|| ((top.getOperatorType() == OperatorType.NEGATIVE)
							&& (top.getPriority() == op.getPriority()))) {

								break;

							}
							
							//pentru varful stivei cu precedenta mai mica sau un
							//varf cu precedenta egala cu a operatorului curent
							//si asociativ stanga, se efectueaza 
							//operatia varfului stivei
							if (top.getNumberOfSons() == 2) {

								top.setRightSon(operands.pop());
								top.setLeftSon(operands.pop());
								operands.push(operators.pop());

							} else {
								top.setRightSon(operands.pop());
								operands.push(operators.pop());

							}
							//daca nu mai exista operatori in stiva,
							//se trece la inserare
							if (operators.isEmpty()) {
								break;
							}
						}
					}
					operators.push(op);
				}
			}
			//parantezele stangi se introduc in stiva de operatori
			if ((node instanceof Parantheses) &&
					(((Parantheses) node).getType() == ParanthesesType.LEFT)) {
				operators.push(node);
				continue;
			}
			//parantezele drepte evalueaza tot ce se gaseste
			//pana la prima paranteza stanga
			if ((node instanceof Parantheses) &&
					(((Parantheses) node).getType() == ParanthesesType.RIGHT)) {
				Node top = operators.peek();
				//cat timp exista operatori intre cele doua paranteze,
				//se efectueaza operatiile specifice lor
				while ((top instanceof Operator)) {
					if (((Operator) top).getNumberOfSons() == 2) {
						((Operator) top).setRightSon(operands.pop());
						((Operator) top).setLeftSon(operands.pop());
						operands.push(operators.pop());
					} else {
						((Operator) top).setRightSon(operands.pop());
						operands.push(operators.pop());
					}
					if (operators.isEmpty()) {
						break;
					}
					top = operators.peek();
				}
				//daca nu exista nicio paranteza stanga in stiva
				//operatorilor, sintaxa este gresita 
				if (operators.isEmpty()) {
					throw new SyntacticException();
				}
				//in final, se scoate paranteza stanga din stiva operatorilor
				operators.pop();
			}
		}
		//cat timp exista operatori in stiva operatorilor,
		//se construieste arborele expresiei
		while (!operators.isEmpty() && operators.peek() instanceof Operator) {
			if (((Operator) operators.peek()).getNumberOfSons() == 2) {
				operators.peek().setRightSon(operands.pop());
				operators.peek().setLeftSon(operands.pop());
				operands.push(operators.pop());
			} else {
				operators.peek().setRightSon(operands.pop());
				operands.push(operators.pop());
			}
		}
		//daca au ramas paranteze in stiva operatorilor, sintaxa este gresita
		if (operators.isEmpty()) {
			this.root = operands.pop();
		} else {
			throw new SyntacticException();
		}
	}

	/**
	 * getter an radacinii arborelui
	 * @return radacina arbore
	 */
	public Node getRoot() {
		return root;
	}
}
