package info.developia.lib

import spock.lang.Specification

class LexerTest extends Specification {

    def "should recognize"() {
        given:
        String query = """
            select users {
                id
                name = 'John'
            }"""
        Lexer lexer = new Lexer(query)
        when:
        List<Token> tokens = lexer.tokenize()
        then:
        tokens.size() == 9
        tokens[0].type == TokenType.SELECT
        tokens[0].value == "select"
        tokens[1].type == TokenType.IDENTIFIER
        tokens[1].value == "users"
        tokens[2].type == TokenType.BRACKET_OPEN
        tokens[3].type == TokenType.IDENTIFIER
        tokens[3].value == "id"
        tokens[4].type == TokenType.IDENTIFIER
        tokens[4].value == "name"
        tokens[5].type == TokenType.EQUALS
        tokens[6].type == TokenType.VALUE_STRING
        tokens[6].value == "John"
        tokens[7].type == TokenType.BRACKET_CLOSE
        tokens[8].type == TokenType.EOF
    }
}
