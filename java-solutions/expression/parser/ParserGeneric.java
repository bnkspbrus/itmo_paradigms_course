package expression.parser;

import expression.TripleExpressionGeneric;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;

public interface ParserGeneric<T> {
    TripleExpressionGeneric<T> parse(String expression) throws ParsingException, OverflowException;
}