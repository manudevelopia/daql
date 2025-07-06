package info.developia.lib;

public class Token {
    public final TokenType type;
    public final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String toString() {
        return type + (value != null ? "(" + value + ")" : "");
    }
}
