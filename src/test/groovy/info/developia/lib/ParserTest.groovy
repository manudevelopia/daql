package info.developia.lib

import spock.lang.Specification

class ParserTest extends Specification {

    def "should create a select"() {
        given:
        String query = """
            select users {
                id
                name
            }"""
        Lexer lexer = new Lexer(query)
        when:
        var parser = new Parser(lexer.tokenize())
        var selectStatement = parser.parseSelect()
        then:
        with(selectStatement) {
            table == 'users'
            columns.size() == 2
            columns[0] == 'id'
            columns[1] == 'name'
        }
    }
}
