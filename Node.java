/**
 * Clasa care implmenteaza interfata Visitable,
 * clasa a nodului din arbore
 * @author luciana
 *
 */
public abstract class Node implements Visitable {

	private String token;	//stringul operatorului

	private Node left = null;	//fiul stang
	private Node right = null;	//fiul drept

	/**
	 * Constructor cu parametru
	 * @param node
	 * @throws SyntacticException
	 */
	public Node(String node) throws SyntacticException {
		token = node;
	}
	
	/**
	 * setter fiu drept
	 * @param right
	 */
	public void setRightSon(Node right) {
		this.right = right;
	}

	/**
	 * setter fiu stang
	 * @param right
	 */
	public void setLeftSon(Node left) {
		this.left = left;
	}

	/**
	 * getter fiu stang
	 * @return
	 */
	public Node getLeftSon() {
		return left;
	}

	/**
	 * getter fiu drept
	 * @return
	 */
	public Node getRightSon() {
		return right;
	}

	/**
	 * getter string operator
	 * @return
	 */
	public String getToken() {
		return token;
	}

}
