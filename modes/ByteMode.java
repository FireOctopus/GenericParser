package expression.modes;

import expression.exceptions.EvaluatingException;

public class ByteMode implements Mode<Byte> {

    public Byte add(Byte op1, Byte op2) throws EvaluatingException {
        return cast(op1 + op2);
    }

    public Byte sub(Byte op1, Byte op2) throws EvaluatingException {
        return cast(op1 - op2);
    }

    public Byte mul(Byte op1, Byte op2) throws EvaluatingException {
        return cast(op1 * op2);
    }

    public Byte div(Byte op1, Byte op2) throws EvaluatingException {
        if (op2 == 0) {
            throw new EvaluatingException("Division by zero");
        }
        return cast(op1 / op2);
    }

    public Byte neg(Byte op) throws EvaluatingException {
        return cast(-op);
    }

    public Byte abs(Byte op) throws EvaluatingException {
        return op < 0 ? cast(-op) : op;
    }

    public Byte sqrt(Byte op) throws EvaluatingException {
        if (op == 0) {
            return 0;
        }
        int result = 0;
        int copy = op;
        int oddNum = 1;
        while (copy > 0) {
            result++;
            copy -= oddNum;
            oddNum += 2;
        }
        return result * result == op ? cast(result) : cast(--result);
    }

    public Byte parseNum(String number) {
        return cast(Integer.parseInt(number));
    }

    private Byte cast(int expr) {
        return (byte) expr;
    }
}
