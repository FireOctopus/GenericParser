package expression.operations;

public interface TripleExpression<T extends Number> extends ToMiniString {
    T evaluate(T x, T y, T z);
}
