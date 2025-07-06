package info.developia.lib

import spock.lang.Specification

class DaqlTest extends Specification {

    def "should create a select"() {
        given:
        String query = """
            select users {
                id
                name
            }"""
        when:
        var sqlSelect = Daql.from(query).asSql()
        then:
        sqlSelect == "SELECT id, name FROM users"
    }
}
