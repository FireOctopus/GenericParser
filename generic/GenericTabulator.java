package expression.generic;

import expression.exceptions.EvaluatingException;
import expression.modes.*;
import expression.operations.TripleExpression;

public class GenericTabulator implements Tabulator {

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws EvaluatingException {
        return getTable(getMode(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private <T extends Number> Object[][][] getTable(Mode<T> mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws EvaluatingException {
        Parser<T> parser = new ExpressionParser<>(mode);
        TripleExpression<T> parsed = parser.parse(expression);
        Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        table[i - x1][j - y1][k - z1] = parsed.evaluate(
                                mode.parseNumber(String.valueOf(i)),
                                mode.parseNumber(String.valueOf(j)),
                                mode.parseNumber(String.valueOf(k)));
                    } catch (EvaluatingException e) {
                        table[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return table;
    }

    public Mode<? extends Number> getMode(String mode) throws EvaluatingException {
        switch (mode) {
            case "i":
                return new IntegerMode(true);
            case "u":
                return new IntegerMode(false);
            case "d":
                return new DoubleMode();
            case "f":
                return new FloatMode();
            case "bi":
                return new BigIntegerMode();
            case "b":
                return new ByteMode();
            default:
                throw new EvaluatingException("Invalid mode: " + mode);
        }
    }
}
