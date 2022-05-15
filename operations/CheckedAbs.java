package expression.operations;

import expression.modes.Mode;

public class CheckedAbs<T extends Number> extends UnaryOperations<T> {
    public CheckedAbs(TripleExpression<T> operand, Mode<T> mode) {
        super(operand, mode);
        operator = "abs";
    }

    protected T evaluate(T operand) {
        return mode.abs(operand);
    }
}
