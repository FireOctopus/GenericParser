package expression.modes;

import expression.exceptions.EvaluatingException;

public class DoubleMode implements Mode<Double> {

    public Double add(Double op1, Double op2) throws EvaluatingException {
        return op1 + op2;
    }

    public Double sub(Double op1, Double op2) throws EvaluatingException {
        return op1 - op2;
    }

    public Double mul(Double op1, Double op2) throws EvaluatingException {
        return op1 * op2;
    }

    public Double div(Double op1, Double op2) throws EvaluatingException {
        return op1 / op2;
    }

    public Double neg(Double op) throws EvaluatingException {
        return -op;
    }

    public Double abs(Double op) throws EvaluatingException {
        return op < 0 ? -op : op;
    }

    public Double sqrt(Double op) throws EvaluatingException {
        return Math.sqrt(op);
    }

    public Double parseNum(String number) {
        return Double.parseDouble(number);
    }

}
