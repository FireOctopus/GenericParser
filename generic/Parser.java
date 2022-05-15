package expression.generic;

import expression.operations.TripleExpression;

public interface Parser<T extends Number> {
    TripleExpression<T> parse(String expression);
}
