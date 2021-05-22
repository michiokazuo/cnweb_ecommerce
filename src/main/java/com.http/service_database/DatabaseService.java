package com.http.service_database;

import com.http.config.AppConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseService implements Database{
    private static DatabaseService instance = null;
    private Connection connection = null;
    private DatabaseService() {
        try {
            Class.forName(AppConfig.DRIVER);
            connection = DriverManager.getConnection(AppConfig.URL_DATABASE, AppConfig.USERNAME, AppConfig.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseService getInstance() {
        if (instance == null) instance = new DatabaseService();
        return instance;
    }

    private PreparedStatement getPreparedStatement(String sql) {
        if (connection == null) return null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public int execSQL(String sql, Object[] selectionArgs) {
        PreparedStatement statement = getPreparedStatement(sql);
        System.out.println(sql);
        if (statement == null) return 0;

        int numExecSuccessful = 0;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, selectionArgs);
            numExecSuccessful = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return numExecSuccessful;
    }

    @Override
    public ResultSet rawQuery(String sql, Object[] selectionArgs) {
        PreparedStatement statement = getPreparedStatement(sql);
        System.out.println(sql);
        if (statement == null) return null;

        ResultSet resultSet = null;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, selectionArgs);
            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet execQuery(String table, String[] listCol, String whereClause, Object[] whereArgs) {
        String sql = SQLBuilder.buildSelectSQL(table, listCol, whereClause, whereArgs);
        PreparedStatement statement = getPreparedStatement(sql);
        System.out.println(sql);
        if (statement == null) return null;
        ResultSet resultSet = null;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, whereArgs);
            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet execQuery(String table, String[] listCol, String whereClause, Object[] whereArgs, String[] groupByArgs) {
        String sql = SQLBuilder.buildSelectSQL(table, listCol, whereClause, whereArgs, groupByArgs);
        System.out.println(sql);
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return null;
        ResultSet resultSet = null;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, whereArgs);
            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet execQuery(String table, String[] listCol, String whereClause, Object[] whereArgs, String[] groupByArgs, String havingClause, Object[] havingArgs) {
        String sql = SQLBuilder.buildSelectSQL(table, listCol, whereClause, whereArgs, groupByArgs, havingClause, havingArgs);
        System.out.println(sql);
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return null;
        ResultSet resultSet = null;
        try {
            ArrayList<Object> params = new ArrayList<>();
            if (whereClause != null && whereArgs != null) params.addAll(Arrays.asList(whereArgs));
            if (havingClause != null && havingArgs != null) params.addAll(Arrays.asList(havingArgs));

            SQLBuilder.buildPreparedStatement(statement, sql, params.toArray());

            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet execQuery(String table, String[] listCol, String whereClause, Object[] whereArgs, String[] groupByArgs, String havingClause, Object[] havingArgs, String[] orderByArgs, boolean isIncrease) {
        String sql = SQLBuilder.buildSelectSQL(table, listCol, whereClause, whereArgs, groupByArgs, havingClause, havingArgs, orderByArgs, isIncrease);
        System.out.println(sql);
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return null;
        ResultSet resultSet = null;
        try {
            ArrayList<Object> params = new ArrayList<>();
            if (whereClause != null && whereArgs != null) params.addAll(Arrays.asList(whereArgs));
            if (havingClause != null && havingArgs != null) params.addAll(Arrays.asList(havingArgs));

            SQLBuilder.buildPreparedStatement(statement, sql, params.toArray());

            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }


    @Override
    public int update(String table, ContentValues values, String whereClause, Object[] whereArgs) {
        String sql = "UPDATE " + table + " SET " +values.toUpdateSQL() + " WHERE " + whereClause;
        System.out.println(sql);
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return 0;

        int numUpdateSuccessful = 0;
        try {
            ArrayList<Object> params = new ArrayList<>(values.getValues());
            if (whereClause != null && whereArgs != null) params.addAll(Arrays.asList(whereArgs));

            SQLBuilder.buildPreparedStatement(statement, sql, params.toArray());
            numUpdateSuccessful =  statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return numUpdateSuccessful;
    }

    @Override
    public int delete(String table, String whereClause, Object[] whereArgs) {
        String sql = "DELETE FROM " + table + " WHERE " + whereClause;
        System.out.println(sql);
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return 0;

        int numDeleteSuccessful = 0;

        try {
            if (whereClause != null) SQLBuilder.buildPreparedStatement(statement, sql, whereArgs);
            numDeleteSuccessful = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return numDeleteSuccessful;
    }

    @Override
    public int insert(String table, ContentValues values) {
        String sql = "INSERT INTO " + table + "(" + values.toColumnInsertSQL() + ")" + " VALUES " + "(" + values.toValuesInsertSQL() + ")";
        System.out.println(sql);
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return 0;
        int numInsertSuccessful = 0;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, values.getValues().toArray());
            numInsertSuccessful = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return numInsertSuccessful;
    }

    public PreparedStatement getPreparedStatementInsert(String table, ContentValues values) throws SQLException {
        String sql = "INSERT INTO " + table + "(" + values.toColumnInsertSQL() + ")" + " VALUES " + "(" + values.toValuesInsertSQL() + ")";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        if (statement == null) return null;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, values.getValues().toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return statement;
    }
}
