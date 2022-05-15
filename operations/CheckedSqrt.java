package expression.operations;

import expression.modes.Mode;

public class CheckedSqrt<T extends Number> extends UnaryOperations<T> {
    public CheckedSqrt(TripleExpression<T> operand, Mode<T> mode) {
        super(operand, mode);
        operator = "sqrt";
    }

    protected T evaluate(T operand) {
        return mode.sqrt(operand);
    }
}
