package com.http.service_database;

import java.sql.ResultSet;

interface Database {
    public int execSQL(String sql, Object[] selectionArg);
    public ResultSet rawQuery(String sql, Object[] selectionArgs);
    public int update(String table, ContentValues values, String whereClause, Object[] whereArgs);
    public int delete(String table, String whereClause, Object[] whereArgs);
    public int insert(String table, ContentValues values);
    public ResultSet execQuery(String table, String[] listCol, String whereClause, Object[] whereArgs);
    public ResultSet execQuery(String table, String[] listCol, String whereClause, Object[] whereArgs, String[] groupByArgs);
    public ResultSet execQuery(String table, String[] listCol, String whereClause, Object[] whereArgs, String[] groupByArgs, String havingClause, Object[] havingArgs);
    public ResultSet execQuery(String table, String[] listCol, String whereClause, Object[] whereArgs, String[] groupByArgs, String havingClause, Object[] havingArgs, String[] orderByArgs, boolean isIncrease);

}
