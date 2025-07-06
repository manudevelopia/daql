package info.developia.lib;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int position = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public SelectStatement parseSelect() {
        expect(TokenType.SELECT);
        var table = expect(TokenType.IDENTIFIER).value;
        expect(TokenType.BRACKET_OPEN);
        var columns = new ArrayList<String>();
        do {
            columns.add(expect(TokenType.IDENTIFIER).value);
        } while (!match(TokenType.BRACKET_CLOSE));
        expect(TokenType.BRACKET_CLOSE);
        expect(TokenType.EOF);
        return new SelectStatement(columns, table);
    }

    private Token expect(TokenType type) {
        var token = tokens.get(position);
        if (token.type != type) {
            throw new RuntimeException("Expected " + type + " but found " + token.type);
        }
        position++;
        return token;
    }

    private boolean match(TokenType type) {
        return tokens.get(position).type == type;
    }
}