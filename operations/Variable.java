package expression.operations;

import java.util.Objects;

public class Variable<T extends Number> implements TripleExpression<T> {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public T evaluate(T x, T y, T z) {
        return name.equals("x") ? x : name.equals("y") ? y : name.equals("z") ? z : null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Variable<?> x = (Variable<?>) object;
        return name.equals(x.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);

    }
}
