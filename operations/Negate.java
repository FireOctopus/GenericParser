package expression.operations;

import expression.modes.Mode;

public class Negate<T extends Number> extends UnaryOperations<T> {
    public Negate(TripleExpression<T> operand, Mode<T> mode) {
        super(operand, mode);
        operator = "-";
    }

    public T evaluate(T operand) {
        return mode.neg(operand);
    }
}
