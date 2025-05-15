package info.developia.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Lexer {
    private final String input;
    private int pos;
    private final int length;

    public Lexer(String input) {
        this.input = input;
        this.length = input.length();
    }

    public List<Token> tokenize() {
        var tokens = new ArrayList<Token>();
        while (pos < length) {
            var current = peek();
            if (Character.isWhitespace(current)) {
                advance();
            } else if (Character.isLetter(current)) {
                var word = readWhile(Character::isLetterOrDigit);
                tokens.add(switch (word.toUpperCase()) {
                    case "SELECT" -> new Token(TokenType.SELECT, word);
                    case "FROM" -> new Token(TokenType.FROM, word);
                    case "WHERE" -> new Token(TokenType.WHERE, word);
                    default -> new Token(TokenType.IDENTIFIER, word);
                });
            } else if (current == '{') {
                tokens.add(new Token(TokenType.BRACKET_OPEN, "{"));
                advance();
            } else if (current == '}') {
                tokens.add(new Token(TokenType.BRACKET_CLOSE, "}"));
                advance();
            } else if (current == '*') {
                tokens.add(new Token(TokenType.STAR, "*"));
                advance();
            } else if (current == ',') {
                tokens.add(new Token(TokenType.COMMA, ","));
                advance();
            } else if (current == ';') {
                tokens.add(new Token(TokenType.SEMICOLON, ";"));
                advance();
            } else if (current == '=') {
                tokens.add(new Token(TokenType.EQUALS, "="));
                advance();
            } else if (current == '\'') {
                advance();
                var sb = new StringBuilder();
                while (peek() != '\'' && pos < length) {
                    sb.append(peek());
                    advance();
                }
                advance();
                tokens.add(new Token(TokenType.VALUE_STRING, sb.toString()));
            } else {
                throw new RuntimeException("Unexpected character: " + current);
            }
        }
        tokens.add(new Token(TokenType.EOF, null));
        return tokens;
    }

    private char peek() {
        return input.charAt(pos);
    }

    private void advance() {
        pos++;
    }

    private String readWhile(Predicate<Character> condition) {
        var sb = new StringBuilder();
        while (pos < length && condition.test(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        return sb.toString();
    }
}