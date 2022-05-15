package expression.generic;

import expression.exceptions.*;
import expression.modes.Mode;
import expression.operations.*;

public class ExpressionParser<T extends Number> implements Parser<T> {
    private int pos;
    private int length;
    private final char[] operators = new char[]{'*', '/', '+', '-'};
    private final int[] prior = new int[]{1, 1, 2, 2};
    private String expression;
    private final Mode<T> mode;

    public ExpressionParser(Mode<T> mode) {
        this.mode = mode;
    }

    public TripleExpression<T> parse(String s) throws ParsingException {
        pos = 0;
        expression = s;
        length = s.length();
        TripleExpression<T> result = parseAddSub();
        if (pos != length) {
            throw new ParsingBracketsException("Expected '('");
        }
        return result;
    }

    private void skipWS() {
        while (pos < length && Character.isWhitespace(expression.charAt(pos))) {
            pos++;
        }
    }

    private TripleExpression<T> parseAddSub() throws ParsingException {
        TripleExpression<T> left = parseMultiDiv();
        TripleExpression<T> right;
        while (pos < length) {
            if (isBinOperatorCorrect(expression.charAt(pos), 2)) {
                break;
            }
            char operator = expression.charAt(pos);
            pos++;
            right = parseMultiDiv();
            if (operator == '+') {
                left = new Add<>(left, right, mode);
            } else {
                left = new Subtract<>(left, right, mode);
            }
        }
        return left;
    }

    private TripleExpression<T> parseMultiDiv() throws ParsingException {
        TripleExpression<T> left = parseOperand();
        while (true) {
            if (pos >= length) {
                return left;
            }
            char operator = expression.charAt(pos);
            if (isBinOperatorCorrect(operator, 1)) {
                return left;
            }
            pos++;
            TripleExpression<T> right = parseOperand();
            if (operator == '*') {
                left = new Multiply<>(left, right, mode);
            } else {
                left = new Divide<>(left, right, mode);
            }
        }
    }

    private TripleExpression<T> parseOperand() throws ParsingException {
        skipWS();
        if (pos == length) {
            throw new ParsingOperandException(whatIsMissing());
        }
        char ch = expression.charAt(pos);
        if (Character.isLetter(ch) || ch == '-') {
            if (ch == 'x' || ch == 'y' || ch == 'z') {
                pos++;
                skipWS();
                return new Variable<>(String.valueOf(ch));
            } else if (ch != 'a' && ch != 's' && ch != '-') {
                throw new ParsingException("Unknown element of expression: " + getWord());
            } else {
                String operator = getUnaryOperator();
                if (ch != '-') {
                    return operator.equals("abs") ? new Abs<>(parseOperand(), mode) : new Sqrt<>(parseOperand(), mode);
                } else {
                    if (Character.isDigit(expression.charAt(pos))) {
                        return parseNumber(true);
                    } else {
                        TripleExpression<T> operand = parseOperand();
                        return new Negate<>(operand, mode);
                    }
                }
            }
        } else if (ch == '(') {
            return parseBracket();
        } else {
            return parseNumber(false);
        }
    }

    private TripleExpression<T> parseBracket() throws ParsingException {
        pos++;
        TripleExpression<T> ExpInBrackets = parseAddSub();
        if (pos == expression.length()) {
            throw new ParsingBracketsException("Expected ')' ");
        }
        if (expression.charAt(pos) == ')') {
            pos++;
            skipWS();
        }
        return ExpInBrackets;
    }

    private TripleExpression<T> parseNumber(boolean isNegated) {
        StringBuilder number = new StringBuilder();
        skipWS();
        if (isNegated) {
            number.append('-');
        }
        while (pos < length && Character.isDigit(expression.charAt(pos))) {
            number.append(expression.charAt(pos++));
        }
        if (number.length() == 0) {
            if (isBinOperatorCorrect(expression.charAt(pos), 0)) {
                throw new ParsingOperandException(whatIsMissing());
            }
        }
        skipWS();
        return new Const<>(mode.parseNumber(number.toString()));
    }

    private boolean isBinOperatorCorrect(char operator, int priority) throws ParsingException {
        for (int i = 0; i < operators.length; i++) {
            if (operator == operators[i]) {
                return priority != prior[i];
            }
        }
        if (Character.isDigit(operator)) {
            throw new ParsingOperandException("Found Whitespace inside of Const");
        } else if (operator != ')') {
            throw new ParsingException("Unknown symbol '" + operator + "'");
        } else {
            return true;
        }
    }

    private boolean isUnOperatorCorrect() {
        if (expression.charAt(pos) == '-') {
            return true;
        }
        String word = getWord();
        if (expression.charAt(pos) == 'a') {
            return word.equals("abs");
        } else {
            return word.equals("sqrt");
        }
    }

    private String whatIsMissing() {
        int back;
        int position = 1;
        String which;
        StringBuilder message = new StringBuilder("Missing ");
        if (pos == length) {
            which = "last ";
            position = pos;
        } else {
            if (pos <= 0) {
                which = "first ";
            } else {
                back = pos - 1;
                while (back > 0 && Character.isWhitespace(expression.charAt(back))) {
                    back--;
                }
                if (expression.charAt(pos) != ')') {
                    if (expression.charAt(back) == '(' || back == 0) {
                        position += pos;
                        which = "first ";
                    } else {
                        position += (pos + back) / 2;
                        which = "middle ";
                    }
                } else if (expression.charAt(pos) == ')' && expression.charAt(back) == '(') {
                    return "Empty brackets -> " + expression;
                } else {
                    position += back + 1;
                    which = "last ";
                }
            }
        }
        message.append(which).append("operand at position: ").append(position).append(" -> ").append(expression);
        return message.toString();
    }

    private String getUnaryOperator() {
        if (isUnOperatorCorrect()) {
            String operator = expression.charAt(pos) == '-' ? "-" : getWord();
            pos += operator.length();
            if (pos != length) {
                skipWS();
                char check = expression.charAt(pos);
                if (Character.isLetter(check) || Character.isDigit(check) || check == '(' || check == '-') {
                    return operator;
                }
            }
            throw new ParsingOperandException("Unary operator '" + operator + "' has no operand -> " + expression);
        } else {
            String expected = expression.charAt(pos) == 'a' ? "abs" : "sqrt";
            throw new ParsingOperatorException("Expected '" + expected + "'; Found '" + getWord() + "';");
        }
    }

    private String getWord() {
        int last = pos;
        while (last < length && Character.isLetter(expression.charAt(last))) {
            last++;
        }
        return expression.substring(pos, last);
    }
}