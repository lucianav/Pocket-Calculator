public interface Visitor {
	
	public double visit(Operator o);

	public double visit(Parantheses p);

	public double visit(Value v);
}
