package expression.operations;

import expression.modes.Mode;

public class CheckedAdd<T extends Number> extends BinaryOperations<T> {
    public CheckedAdd(TripleExpression<T> operand1, TripleExpression<T> operand2, Mode<T> mode) {
        super(operand1, operand2, mode);
        operator = "+";
    }

    public T evaluate(T a, T b) {
        return mode.add(a, b);
    }
}
