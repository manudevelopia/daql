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
        List<Token> tokens = new ArrayList<>();
        while (pos < length) {
            char current = peek();
            if (Character.isWhitespace(current)) {
                advance();
            } else if (Character.isLetter(current)) {
                var word = readWhile(Character::isLetterOrDigit);
                switch (word.toUpperCase()) {
                    case "SELECT" -> tokens.add(new Token(TokenType.SELECT, word));
                    case "FROM" -> tokens.add(new Token(TokenType.FROM, word));
                    case "WHERE" -> tokens.add(new Token(TokenType.WHERE, word));
                    default -> tokens.add(new Token(TokenType.IDENTIFIER, word));
                }
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
                StringBuilder sb = new StringBuilder();
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
        StringBuilder sb = new StringBuilder();
        while (pos < length && condition.test(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        return sb.toString();
    }
}