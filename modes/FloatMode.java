package expression.modes;

import expression.exceptions.EvaluatingException;

public class FloatMode implements Mode<Float>{

    public Float add(Float op1, Float op2) throws EvaluatingException {
        return op1 + op2;
    }

    public Float sub(Float op1, Float op2) throws EvaluatingException {
        return op1 - op2;
    }

    public Float mul(Float op1, Float op2) throws EvaluatingException {
        return op1 * op2;
    }

    public Float div(Float op1, Float op2) throws EvaluatingException {
        return op1 / op2;
    }

    public Float neg(Float op) throws EvaluatingException {
        return -op;
    }

    public Float abs(Float op) throws EvaluatingException {
        return op < 0 ? -op: op;
    }

    public Float sqrt(Float op) throws EvaluatingException {
        return (float) Math.sqrt(op);
    }

    public Float parseNum(String number) {
        return Float.parseFloat(number);
    }
}
