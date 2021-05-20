package com.http.service_database;

import java.sql.ResultSet;

interface Database {
    public int execSQL(String sql, String[] selectionArg);
    public ResultSet rawQuery(String sql, String[] selectionArgs);
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs);
    public int delete(String table, String whereClause, String[] whereArgs);
    public int insert(String table, ContentValues values);
    public ResultSet execQuery(String table, String[] listCol, String whereClause, String[] whereArgs);
    public ResultSet execQuery(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs);
    public ResultSet execQuery(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs, String havingClause, String[] havingArgs);
    public ResultSet execQuery(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs, String havingClause, String[] havingArgs, String[] orderByArgs, boolean isIncrease);

}
