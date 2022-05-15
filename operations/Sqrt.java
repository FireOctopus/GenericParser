package expression.operations;

import expression.modes.Mode;

public class Sqrt<T extends Number> extends UnaryOperations<T> {
    public Sqrt(TripleExpression<T> operand, Mode<T> mode) {
        super(operand, mode);
        operator = "sqrt";
    }

    protected T evaluate(T operand) {
        return mode.sqrt(operand);
    }
}
