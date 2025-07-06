package info.developia.lib;

import java.util.List;

public class SelectStatement implements Statement {
    public final String table;
    public final List<String> columns;

    public SelectStatement(List<String> columns, String table) {
        this.columns = columns;
        this.table = table;
    }

    public String asSql() {
        return "SELECT " + String.join(", ", columns) + " FROM " + table;
    }
}