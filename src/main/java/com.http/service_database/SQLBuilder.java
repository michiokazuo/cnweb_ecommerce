package com.http.service_database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLBuilder {
    public static String buildSelectSQL(String table, String[] listCol, String whereClause, String[] whereArgs) {
        StringBuilder sql;
        if (listCol == null) {
            sql = new StringBuilder("SELECT * FROM " + table);
        } else {
            sql = new StringBuilder("SELECT ");
            for (int i = 0; i < listCol.length - 1; i++) {
                sql.append(listCol[i]).append(",");
            }
            sql.append(listCol[listCol.length - 1]);
            sql.append(" FROM ").append(table);
        }

        if (whereClause != null) {
            sql.append(" WHERE ").append(whereClause);
        }

        return sql.toString();
    }

    public static String buildSelectSQL(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs) {
        StringBuilder sql = new StringBuilder(buildSelectSQL(table, listCol, whereClause, whereArgs));

        if (groupByArgs == null) return sql.toString();

        sql.append(" GROUP BY ");
        for (int i = 0; i < groupByArgs.length - 1; i++) {
            sql.append(groupByArgs[i]).append(",");
        }
        sql.append(groupByArgs[groupByArgs.length - 1]);

        return sql.toString();
    }

    public static String buildSelectSQL(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs, String havingClause, String[] havingArgs) {
        StringBuilder sql = new StringBuilder(buildSelectSQL(table, listCol, whereClause, whereArgs, groupByArgs));

        if (havingClause == null) return sql.toString();

        sql.append(" HAVING ").append(havingClause);

        return sql.toString();
    }

    public static String buildSelectSQL(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs, String havingClause, String[] havingArgs, String[] orderByArgs, boolean isIncrement) {
        StringBuilder sql = new StringBuilder(buildSelectSQL(table, listCol, whereClause, whereArgs, groupByArgs, havingClause, havingArgs));

        if (orderByArgs == null) return sql.toString();

        sql.append(" ORDER BY ");
        for (int i = 0; i < orderByArgs.length - 1; i++) {
            sql.append(orderByArgs[i]).append(",");
        }
        sql.append(orderByArgs[orderByArgs.length - 1]);

        if (isIncrement) {
            sql.append(" ASC ");
        } else {
            sql.append(" DESC ");
        }
        return sql.toString();
    }

    public static void buildPreparedStatement(PreparedStatement statement, String sql, String[] param) throws SQLException {
        if (param == null) return;
        for (int i = 1; i <= param.length; i++) {
            statement.setString(i, param[i - 1]);
        }
    }

    static class BuildTable {
        String sql;
        public BuildTable(String rootTable) {
            this.sql = rootTable;
        }

        public BuildTable addTable(String joinTable, String refJoinTable, String table, String refTable) {
            sql += " JOIN " + joinTable + " ON " + joinTable + "." + refJoinTable + " LIKE " + table + "." + refJoinTable + " ";
            return this;
        }

        public String build() {
            return sql;
        }
    }

}
