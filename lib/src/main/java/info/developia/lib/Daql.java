package info.developia.lib;

public class Daql {

    public static Statement from(String query) {
        var lexer = new Lexer(query);
        var parser = new Parser(lexer.tokenize());
        return parser.parseSelect();
    }
}
