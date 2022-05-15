package expression.modes;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingNumberException;

public interface Mode<T extends Number> {

    default T parseNumber(String number) throws ParsingNumberException {
        try {
            return parseNum(number);
        } catch (NumberFormatException e) {
            throw new ParsingNumberException("Illegal number: " + number);
        }
    }

    T add(T op1, T op2) throws EvaluatingException;

    T sub(T op1, T op2) throws EvaluatingException;

    T mul(T op1, T op2) throws EvaluatingException;

    T div(T op1, T op2) throws EvaluatingException;

    T neg(T op) throws EvaluatingException;

    T abs(T op) throws EvaluatingException;

    T sqrt(T op) throws EvaluatingException;

    T parseNum(String number);
}
