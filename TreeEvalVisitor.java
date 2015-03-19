/**
 * Clasa care implementeaza interfata Visitor
 * 
 * Evalueaza arborele expresiei, trecand recursiv
 * prin toate nodurile acestuia
 * @author luciana
 *
 */
public class TreeEvalVisitor implements Visitor {

	/**
	 * Metoda de vizitare a operatorului, aplica operatia
	 * specifica acestuia calculand expresia din fiul stang si drept,
	 * prin vizitarea lor si trateaza cazurile de aplicare incorecta
	 * a operatorilor, prin aruncarea de exceptii
	 * @param o
	 * @return
	 */
	public double visit(Operator o) {
		switch (o.getOperatorType()) {
		case ADD:
			return o.getLeftSon().accept(this) + o.getRightSon().accept(this);
		case SUBSTRACT:
			return o.getLeftSon().accept(this) - o.getRightSon().accept(this);

		case MULTIPLY:
			return o.getLeftSon().accept(this) * o.getRightSon().accept(this);

		case DIVIDE:
			double left = o.getLeftSon().accept(this);
			double right = o.getRightSon().accept(this);
			if (right == 0) {
				throw new EvaluatorException(o.getToken());
			}
			return left / right;

		case POWER:
			return Math.pow(((int) o.getLeftSon().accept(this)),
					((int) o.getRightSon().accept(this)));

		case LOG:
			right = o.getRightSon().accept(this);
			if (right < 0) {
				throw new EvaluatorException(o.getToken() + "-");
			}
			if (right == 0) {
				throw new EvaluatorException(o.getToken() + "0");
			}
			return Math.log10(right);

		case SQRT:
			right = o.getRightSon().accept(this);
			if (right < 0) {
				throw new EvaluatorException(o.getToken());
			}
			return Math.sqrt(right);

		case SIN:
			return Math.sin(o.getRightSon().accept(this));

		case COS:
			return Math.cos(o.getRightSon().accept(this));

		case NEGATIVE:
			return -o.getRightSon().accept(this);
		}
		return 0;
	}

	/**
	 * Metoda de vizitare a unui nod paranteza
	 * 
	 * Acest lucru nu este posibil, erorile de sitaxa
	 * sunt detectate la parsare si nu se ajunge la
	 * etapa de evaluare
	 * @param p
	 * @return
	 */
	@Override
	public double visit(Parantheses p) {
		return 0f;
	}

	/**
	 * Metoda de vizitare a unui nod frunza cu valoare numerica
	 * @param value
	 * @return  valoarea numerica
	 */
	@Override
	public double visit(Value value) {
		return value.getValue();
	}
}
