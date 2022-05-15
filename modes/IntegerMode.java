package expression.modes;

import expression.exceptions.EvaluatingException;

public class IntegerMode implements Mode<Integer> {
    private final boolean checkOF;

    public IntegerMode(boolean checkOF) {
        this.checkOF = checkOF;
    }

    public Integer add(Integer op1, Integer op2) throws EvaluatingException {
        if (checkOF) {
            if (op2 > 0 ? op1 > Integer.MAX_VALUE - op2 : op1 < Integer.MIN_VALUE - op2) {
                throw new EvaluatingException("Overflow:" + op1 + " + " + op2);
            }
        }
        return op1 + op2;
    }

    public Integer sub(Integer op1, Integer op2) throws EvaluatingException {
        if (checkOF) {
            if (op2 > 0 ? op1 < Integer.MIN_VALUE + op2 : op1 > Integer.MAX_VALUE + op2) {
                throw new EvaluatingException("Overflow:" + op1 + " - " + op2);
            }
        }
        return op1 - op2;
    }

    public Integer mul(Integer op1, Integer op2) throws EvaluatingException {
        if (checkOF) {
            if (op2 > 0 && (op1 > Integer.MAX_VALUE / op2 || op1 < Integer.MIN_VALUE / op2) ||
                    op2 < -1 && (op1 > Integer.MIN_VALUE / op2 || op1 < Integer.MAX_VALUE / op2)
                    || op2 == -1 && op1 == Integer.MIN_VALUE) {
                throw new EvaluatingException("Overflow:" + op1 + " * " + op2);
            }
        }
        return op1 * op2;
    }

    public Integer div(Integer op1, Integer op2) throws EvaluatingException {
        if (checkOF) {
            if (op1 == Integer.MIN_VALUE && op2 == -1) {
                throw new EvaluatingException("Overflow:" + op1 + " / " + op2);
            }
        }
        if (op2 == 0) {
            throw new EvaluatingException("Division by zero:" + op1 + " / " + op2);
        }
        return op1 / op2;
    }

    public Integer neg(Integer operand) throws EvaluatingException {
        if (checkOF) {
            if (operand == Integer.MIN_VALUE) {
                throw new EvaluatingException("Overflow: -" + operand);
            }
        }
        return -operand;
    }

    public Integer abs(Integer operand) throws EvaluatingException {
        if (checkOF) {
            if (operand == Integer.MIN_VALUE) {
                throw new EvaluatingException("Overflow: abs" + operand);
            }
        }
        return operand < 0 ? -operand : operand;
    }

    public Integer sqrt(Integer operand) throws EvaluatingException {
        if (operand < 0) {
            throw new EvaluatingException("Negative radical: sqrt" + operand);
        }
        if (operand == 0) {
            return 0;
        }
        int result = 0;
        int copy = operand;
        int oddNum = 1;
        while (copy > 0) {
            result++;
            copy -= oddNum;
            oddNum += 2;
        }
        return result * result == operand ? result : --result;
    }

    public Integer parseNum(String number) {
        return Integer.parseInt(number);
    }
}
