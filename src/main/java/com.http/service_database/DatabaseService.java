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

    private synchronized DatabaseService getInstance() {
        if (instance == null) instance = new DatabaseService();
        return instance;
    }

    private PreparedStatement getPreparedStatement(String sql) {
        if (connection == null) return null;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return preparedStatement;
    }

    @Override
    public int execSQL(String sql, String[] selectionArgs) {
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return 0;

        int numExecSuccessful = 0;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, selectionArgs);
            numExecSuccessful = statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return numExecSuccessful;
    }

    @Override
    public ResultSet rawQuery(String sql, String[] selectionArgs) {
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return null;

        ResultSet resultSet = null;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, selectionArgs);
            resultSet = statement.executeQuery();
            //statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet execQuery(String table, String[] listCol, String whereClause, String[] whereArgs) {
        String sql = SQLBuilder.buildSelectSQL(table, listCol, whereClause, whereArgs);

        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return null;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, whereArgs);
            System.out.println(sql);
            resultSet = statement.executeQuery();
            //statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet execQuery(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs) {
        String sql = SQLBuilder.buildSelectSQL(table, listCol, whereClause, whereArgs, groupByArgs);

        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return null;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, whereArgs);
            System.out.println(sql);
            resultSet = statement.executeQuery();
            //statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet execQuery(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs, String havingClause, String[] havingArgs) {
        String sql = SQLBuilder.buildSelectSQL(table, listCol, whereClause, whereArgs, groupByArgs, havingClause, havingArgs);

        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return null;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            ArrayList<String> params = new ArrayList<>();
            if (whereClause != null && whereArgs != null) params.addAll(Arrays.asList(whereArgs));
            if (havingClause != null && havingArgs != null) params.addAll(Arrays.asList(havingArgs));

            SQLBuilder.buildPreparedStatement(statement, sql, params.toArray(new String[0]));

            System.out.println(sql);
            resultSet = statement.executeQuery();
            //statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet execQuery(String table, String[] listCol, String whereClause, String[] whereArgs, String[] groupByArgs, String havingClause, String[] havingArgs, String[] orderByArgs, boolean isIncrease) {
        String sql = SQLBuilder.buildSelectSQL(table, listCol, whereClause, whereArgs, groupByArgs, havingClause, havingArgs, orderByArgs, isIncrease);

        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return null;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            ArrayList<String> params = new ArrayList<>();
            if (whereClause != null && whereArgs != null) params.addAll(Arrays.asList(whereArgs));
            if (havingClause != null && havingArgs != null) params.addAll(Arrays.asList(havingArgs));

            SQLBuilder.buildPreparedStatement(statement, sql, params.toArray(new String[0]));

            System.out.println(sql);
            resultSet = statement.executeQuery();
            //statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }


    @Override
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        String sql = "UPDATE " + table + " SET " +values.toUpdateSQL() + " WHERE " + whereClause;
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return 0;

        int numUpdateSuccessful = 0;
        try {
            ArrayList<String> params = new ArrayList<>(values.getValues());
            if (whereClause != null && whereArgs != null) params.addAll(Arrays.asList(whereArgs));

            SQLBuilder.buildPreparedStatement(statement, sql, params.toArray(new String[0]));

            numUpdateSuccessful =  statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return numUpdateSuccessful;
    }

    @Override
    public int delete(String table, String whereClause, String[] whereArgs) {
        String sql = "DELETE FROM " + table + " WHERE " + whereClause;
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return 0;

        int numDeleteSuccessful = 0;

        try {
            if (whereClause != null) SQLBuilder.buildPreparedStatement(statement, sql, whereArgs);
            numDeleteSuccessful = statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return numDeleteSuccessful;
    }

    @Override
    public int insert(String table, ContentValues values) {
        String sql = "INSERT INTO " + table + "(" + values.toColumnInsertSQL() + ")" + " VALUES " + "(" + values.toValuesInsertSQL() + ")";
        PreparedStatement statement = getPreparedStatement(sql);
        if (statement == null) return 0;
        int numInsertSuccessful = 0;
        try {
            SQLBuilder.buildPreparedStatement(statement, sql, values.getValues().toArray(new String[0]));
            numInsertSuccessful =  statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return numInsertSuccessful;
    }
}
