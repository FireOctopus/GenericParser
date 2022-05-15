package expression.modes;

import expression.exceptions.EvaluatingException;

import java.math.BigInteger;

public class BigIntegerMode implements Mode<BigInteger> {

    public BigInteger add(BigInteger op1, BigInteger op2) throws EvaluatingException {
        return op1.add(op2);
    }

    public BigInteger sub(BigInteger op1, BigInteger op2) throws EvaluatingException {
        return op1.subtract(op2);
    }

    public BigInteger mul(BigInteger op1, BigInteger op2) throws EvaluatingException {
        return op1.multiply(op2);
    }

    public BigInteger div(BigInteger op1, BigInteger op2) throws EvaluatingException {
        if (!op2.equals(BigInteger.ZERO)) {
            return op1.divide(op2);
        }
        throw new EvaluatingException("Division by zero:" + op1 + " / " + op2);
    }

    public BigInteger neg(BigInteger op) throws EvaluatingException {
        return op.negate();
    }

    public BigInteger abs(BigInteger op) throws EvaluatingException {
        return op.abs();
    }

    public BigInteger sqrt(BigInteger op) throws EvaluatingException {
        return op.sqrt();
    }

    public BigInteger parseNum(String number) {
        return new BigInteger(number);
    }
}
