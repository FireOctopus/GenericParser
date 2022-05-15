package expression.operations;

import expression.modes.Mode;

import java.util.Objects;

public abstract class BinaryOperations<T extends Number> implements TripleExpression<T> {
    private final TripleExpression<T> operand1, operand2;
    protected String operator = "";
    protected Mode<T> mode;

    public BinaryOperations(TripleExpression<T> operand1, TripleExpression<T> operand2, Mode<T> mode) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.mode = mode;
    }

    protected abstract T evaluate(T operand1, T operand2);

    @Override
    public T evaluate(T x, T y, T z) {
        return evaluate(operand1.evaluate(x, y, z), operand2.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + operand1 + " " + operator + " " + operand2 + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BinaryOperations<?> x = (BinaryOperations<?>) object;
        return operand1.equals(x.operand1) && operand2.equals(x.operand2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2, operator, mode);
    }
}
