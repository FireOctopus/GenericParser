package expression.operations;

import expression.modes.Mode;

public class CheckedSubtract<T extends Number> extends BinaryOperations<T> {
    public CheckedSubtract(TripleExpression<T> operand1, TripleExpression<T> operand2, Mode<T> mode) {
        super(operand1, operand2, mode);
        operator = "-";
    }

    public T evaluate(T operand1, T operand2) {
        return mode.sub(operand1, operand2);
    }
}
