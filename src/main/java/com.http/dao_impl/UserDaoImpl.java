package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.UserDao;
import com.http.model.Role;
import com.http.model.User;
import com.http.payload.LoginForm;
import com.http.service_database.ContentValues;
import com.http.service_database.DatabaseService;
import com.http.service_database.SQLBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User getObject(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(AppConfig.TABLE_USER + ".id"),
                resultSet.getString(AppConfig.TABLE_USER + ".email"),
                resultSet.getString(AppConfig.TABLE_USER + ".password"),
                resultSet.getString(AppConfig.TABLE_USER + ".name"),
                resultSet.getString(AppConfig.TABLE_USER + ".phone"),
                new Role(resultSet.getInt(AppConfig.TABLE_ROLE + ".id"),
                        resultSet.getString(AppConfig.TABLE_ROLE + ".name"),
                        resultSet.getString(AppConfig.TABLE_ROLE + ".content")),
                resultSet.getString(AppConfig.TABLE_USER + ".avatar"),
                resultSet.getBoolean(AppConfig.TABLE_USER + ".deleted"),
                resultSet.getString(AppConfig.TABLE_USER + ".job"),
                resultSet.getString(AppConfig.TABLE_USER + ".gender"),
                resultSet.getString(AppConfig.TABLE_USER + ".home_town"),
                resultSet.getString(AppConfig.TABLE_USER + ".workplace"),
                resultSet.getDate(AppConfig.TABLE_USER + ".birthday"),
                resultSet.getDate(AppConfig.TABLE_USER + ".modify_date"),
                resultSet.getDate(AppConfig.TABLE_USER + ".create_date"),
                resultSet.getString(AppConfig.TABLE_USER + ".create_by"),
                resultSet.getString(AppConfig.TABLE_USER + ".modify_by"));
    }

    @Override
    public List<User> getList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        User user = null;

        if (resultSet.first()) {
            do {
                user = getObject(resultSet);
                if (user != null) {
                    userList.add(user);
                }
            } while (resultSet.next());
        }

        return userList.isEmpty() ? null : userList;
    }

    @Override
    public List<User> findAll() throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_USER)
                        .addTable(AppConfig.TABLE_ROLE, "id", AppConfig.TABLE_USER, "role")
                        .build();
        String whereClause = AppConfig.TABLE_USER + ".deleted = false";
        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, null);

        return getList(resultSet);
    }

    @Override
    public User findById(Integer id) throws SQLException {
        User user = null;
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_USER)
                .addTable(AppConfig.TABLE_ROLE, "id", AppConfig.TABLE_USER, "role")
                .build();

        String whereClause = AppConfig.TABLE_USER + ".deleted = false AND " + AppConfig.TABLE_USER + ".id = ?";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {id});
        if (resultSet.first()) {
            user = getObject(resultSet);
        }

        return user;
    }

    @Override
    public User insert(User user) throws SQLException {
        User new_user = null;

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());
        contentValues.put("name", user.getName());
        contentValues.put("phone", user.getPhone());
        contentValues.put("role", user.getRole().getId());
        contentValues.put("avatar", user.getAvatar());
        contentValues.put("deleted", user.getDeleted());
        contentValues.put("job", user.getJob());
        contentValues.put("gender", user.getGender());
        contentValues.put("home_town", user.getEmail());
        contentValues.put("workplace", user.getWorkplace());
        contentValues.put("birthday", new Date(user.getBirthday().getTime()));
        contentValues.put("modify_date", new Date(user.getModifyDate().getTime()));
        contentValues.put("create_date", new Date(user.getCreateDate().getTime()));
        contentValues.put("create_by", user.getCreateBy());
        contentValues.put("modify_by", user.getModifyBy());

        PreparedStatement statement = DatabaseService.getInstance().getPreparedStatementInsert(AppConfig.TABLE_USER, contentValues);

        int rs = statement.executeUpdate();
        if (rs > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.first()) {
                new_user = findById((int) resultSet.getLong(1));
            }
        }

        return new_user;
    }

    @Override
    public List<User> search(User user) throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_USER)
                .addTable(AppConfig.TABLE_ROLE, "id", AppConfig.TABLE_USER, "role")
                .build();

        String whereClause =  AppConfig.TABLE_USER + ".deleted = false AND ( "
                + " ( ? IS NULL OR " + AppConfig.TABLE_USER + ".id = ? )"
                + " AND ( ? IS NULL OR " + AppConfig.TABLE_USER + ".name LIKE ?) "
                + " AND ( ? IS NULL OR " + AppConfig.TABLE_USER + ".email LIKE ?) "
                + " AND " + AppConfig.TABLE_ROLE + ".id = ?"
                + " AND ( ? IS NULL OR " + AppConfig.TABLE_USER + ".phone LIKE ?) "
                + " AND ( ? IS NULL OR " + AppConfig.TABLE_USER + ".job LIKE ?) "
                + " AND ( ? IS NULL OR " + AppConfig.TABLE_USER + ".gender LIKE ?) "
                + " AND ( ? IS NULL OR " + AppConfig.TABLE_USER + ".home_town LIKE ?) "
                + " AND ( ? IS NULL OR " + AppConfig.TABLE_USER + ".workplace LIKE ?) "
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_USER + ".birthday) >= ?)"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_USER + ".birthday) <= ?) "
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_USER + ".create_date) >= ?)"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_USER + ".create_date) <= ?) ) ";

        Object[] whereArgs = new Object[] {
                user.getId(),
                user.getId(),
                user.getName(),
                "%" + user.getName() + "%",
                user.getEmail(),
                "%" + user.getEmail() + "%",
                AppConfig.roles.get(AppConfig.USER).getId(),
                user.getPhone(),
                "%" + user.getPhone() + "%",
                user.getJob(),
                "%" + user.getJob() + "%",
                user.getGender(),
                "%" + user.getGender() + "%",
                user.getHomeTown(),
                "%" + user.getHomeTown() + "%",
                user.getWorkplace(),
                "%" + user.getWorkplace() + "%",
                user.getBirthday() == null ? null : new Date(user.getBirthday().getTime()),
                user.getBirthday() == null ? null : new Date(user.getBirthday().getTime()),
                user.getBirthday() == null ? null : new Date(user.getBirthday().getTime()),
                user.getBirthday() == null ? null : new Date(user.getBirthday().getTime()),
                user.getCreateDate() == null ? null : new Date(user.getCreateDate().getTime()),
                user.getCreateDate() == null ? null : new Date(user.getCreateDate().getTime()),
                user.getCreateDate() == null ? null : new Date(user.getCreateDate().getTime()),
                user.getCreateDate() == null ? null : new Date(user.getCreateDate().getTime())
        };


        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, whereArgs);

        return getList(resultSet);
    }

    @Override
    public User update(User user) throws SQLException {
        User update_user = null;

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());
        contentValues.put("name", user.getName());
        contentValues.put("phone", user.getPhone());
        contentValues.put("avatar", user.getAvatar());
        contentValues.put("job", user.getJob());
        contentValues.put("gender", user.getGender());
        contentValues.put("home_town", user.getHomeTown());
        contentValues.put("workplace", user.getWorkplace());
        contentValues.put("birthday", new Date(user.getBirthday().getTime()));
        contentValues.put("modify_date", new Date(user.getModifyDate().getTime()));
        contentValues.put("modify_by", user.getModifyBy());

        int rs = DatabaseService.getInstance().update(AppConfig.TABLE_USER, contentValues, "id = ?", new Object[] {user.getId()});
        if (rs > 0) {
            update_user = findById(user.getId());
        }

        return update_user;
    }

    @Override
    public boolean delete(Integer id, String email, java.util.Date modify) throws SQLException {

        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", true);
        contentValues.put("modify_date", new Date(modify.getTime()));
        contentValues.put("modify_by", email);

        int delete = DatabaseService.getInstance().update(AppConfig.TABLE_USER, contentValues, "id = ?", new Object[] {id});

        return delete >= 0;
    }

    @Override
    public void updateCreateAndModifyBy(String oldEmail, String newEmail) throws SQLException {
        updateBy(oldEmail, newEmail, "create_by");
        updateBy(oldEmail, newEmail, "modify_by");
    }

    private void updateBy(String oldEmail, String newEmail, String column) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, newEmail);

        DatabaseService.getInstance().update(AppConfig.TABLE_USER, contentValues, column + " = ?", new Object[] {oldEmail});
    }

    @Override
    public User getUserByEmailAndPassword(LoginForm loginForm) throws SQLException {
        User user = null;
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_USER)
                            .addTable(AppConfig.TABLE_ROLE, "id", AppConfig.TABLE_USER, "role")
                            .build();

        String whereClause = AppConfig.TABLE_USER + ".deleted = false AND " + AppConfig.TABLE_USER + ".email = ? AND " + AppConfig.TABLE_USER + ".password = ?";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {loginForm.getEmail(), loginForm.getPassword()});
        if (resultSet.first()) {
            user = getObject(resultSet);
        }

        return user;
    }

    @Override
    public boolean existsByEmailOrPhone(String email, String phone) throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_USER)
                .addTable(AppConfig.TABLE_ROLE, "id", AppConfig.TABLE_USER, "role")
                .build();
        String whereClause =  AppConfig.TABLE_USER + ".deleted = false AND ( " + AppConfig.TABLE_USER + ".email = ? OR " + AppConfig.TABLE_USER + ".phone = ? )";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {email, phone});

        return resultSet.first();
    }

    @Override
    public List<User> getListByEmailOrPhone(String email, String phone) throws SQLException {
        String table = new SQLBuilder.BuildTable(AppConfig.TABLE_USER)
                .addTable(AppConfig.TABLE_ROLE, "id", AppConfig.TABLE_USER, "role")
                .build();
        String whereClause =  AppConfig.TABLE_USER + ".deleted = false AND ( " + AppConfig.TABLE_USER + ".email = ? OR " + AppConfig.TABLE_USER + ".phone = ? )";

        ResultSet resultSet = DatabaseService.getInstance().execQuery(table, null, whereClause, new Object[] {email, phone});

        return getList(resultSet);
    }

    @Override
    public List<Role> getRole() throws SQLException {

        ResultSet resultSet = DatabaseService.getInstance().execQuery(AppConfig.TABLE_ROLE, null, null, null);

        return getListRole(resultSet);
    }

    List<Role> getListRole(ResultSet resultSet) throws SQLException {
        List<Role> roles = new ArrayList<>();
        Role role = null;

        if (resultSet.first()) {
            do {
                role = new Role(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("content"));
                if (role != null) {
                    roles.add(role);
                }
            } while (resultSet.next());
        }

        return roles.isEmpty() ? null : roles;
    }
}
