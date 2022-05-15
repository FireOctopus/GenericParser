package expression.operations;

import expression.modes.Mode;

import java.util.Objects;

public abstract class UnaryOperations<T extends Number> implements TripleExpression<T> {
    protected final TripleExpression<T> operand;
    protected String operator = "";
    protected Mode<T> mode;

    public UnaryOperations(TripleExpression<T> operand, Mode<T> mode) {
        this.operand = operand;
        this.mode = mode;
    }

    protected abstract T evaluate(T operand);

    @Override
    public T evaluate(T x, T y, T z) {
        return evaluate(operand.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return operator + " " + operand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, operator, mode);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        UnaryOperations<?> x = (UnaryOperations<?>) object;
        return operand.equals(x.operand);
    }

}
