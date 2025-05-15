package info.developia.lib

import spock.lang.Specification

class LexerTest extends Specification {

    def "should recognize"() {
        given:
        String query = """
            users {
                id
                name = 'John'
            }"""
        Lexer lexer = new Lexer(query)
        when:
        List<Token> tokens = lexer.tokenize()
        then:
        tokens.size() == 8
        tokens[0].type == TokenType.IDENTIFIER
        tokens[0].value == "users"
        tokens[1].type == TokenType.BRACKET_OPEN
        tokens[2].type == TokenType.IDENTIFIER
        tokens[2].value == "id"
        tokens[3].type == TokenType.IDENTIFIER
        tokens[3].value == "name"
        tokens[4].type == TokenType.EQUALS
        tokens[5].type == TokenType.VALUE_STRING
        tokens[5].value == "John"
        tokens[6].type == TokenType.BRACKET_CLOSE
        tokens[7].type == TokenType.EOF
    }
}
