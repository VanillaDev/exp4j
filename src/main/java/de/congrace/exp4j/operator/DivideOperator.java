package de.congrace.exp4j.operator;

public class DivideOperator extends Operator {
	public DivideOperator() {
		super("/", 3);
	}

	@Override
	public double applyOperation(double[] values) {
		return values[0] / values[1];
	}
}
