package expression.operations;

import expression.modes.Mode;

public class Add<T extends Number> extends BinaryOperations<T> {
    public Add(TripleExpression<T> operand1, TripleExpression<T> operand2, Mode<T> mode) {
        super(operand1, operand2, mode);
        operator = "+";
    }

    public T evaluate(T a, T b) {
        return mode.add(a, b);
    }
}
