package expression.operations;

import expression.modes.Mode;

public class CheckedMultiply<T extends Number> extends BinaryOperations<T> {
    public CheckedMultiply(TripleExpression<T> operand1, TripleExpression<T> operand2, Mode<T> mode) {
        super(operand1, operand2, mode);
        operator = "*";
    }

    public T evaluate(T a, T b) {
        return mode.mul(a, b);
    }
}
