package com.http.dao_impl;

import com.http.config.AppConfig;
import com.http.dao.UserDao;
import com.http.model.MyConnection;
import com.http.model.Role;
import com.http.model.User;
import com.http.payload.LoginForm;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final MyConnection connection = new MyConnection();

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
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_USER, "role", true,
                        AppConfig.TABLE_ROLE, "id", false);

        PreparedStatement preparedStatement = connection.prepare(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public User findById(int id) throws SQLException {
        boolean need_user = true;
        boolean need_role = false;
        User user = null;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_USER, "role", need_user,
                        AppConfig.TABLE_ROLE, "id", need_role)
                + ((need_user || need_role) ? " AND " : " WHERE ") + AppConfig.TABLE_USER + ".id = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.first()) {
            user = getObject(resultSet);
        }

        return user;
    }

    @Override
    public User insert(User user) throws SQLException {
        User new_user = null;
        String sql = "INSERT INTO " + AppConfig.TABLE_USER + " (email, password, name, phone, role, avatar, deleted, job, gender"
                + ", home_town, workplace, birthday, modify_date, create_date, create_by, modify_by)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getPhone());
        preparedStatement.setInt(5, user.getRole().getId());
        preparedStatement.setString(6, user.getAvatar());
        preparedStatement.setBoolean(7, user.getDeleted());
        preparedStatement.setString(8, user.getJob());
        preparedStatement.setString(9, user.getGender());
        preparedStatement.setString(10, user.getHomeTown());
        preparedStatement.setString(11, user.getWorkplace());
        preparedStatement.setDate(12, new Date(user.getBirthday().getTime()));
        preparedStatement.setDate(13, new Date(user.getModifyDate().getTime()));
        preparedStatement.setDate(14, new Date(user.getCreateDate().getTime()));
        preparedStatement.setString(15, user.getCreateBy());
        preparedStatement.setString(16, user.getModifyBy());

        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.first()) {
                new_user = findById((int) resultSet.getLong(1));
            }
        }

        return new_user;
    }

    @Override
    public List<User> search(User user) throws SQLException {
        boolean need_user = true;
        boolean need_role = false;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_USER, "role", need_user,
                        AppConfig.TABLE_ROLE, "id", need_role)
                + ((need_user || need_role) ? " AND " : " WHERE ")
                + " ? IS NULL OR" + AppConfig.TABLE_USER + ".id = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_USER + ".name LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_USER + ".email LIKE ?"
                + " AND " + AppConfig.TABLE_ROLE + ".id = ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_USER + ".phone LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_USER + ".job LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_USER + ".gender LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_USER + ".home_town LIKE ?"
                + " AND ? IS NULL OR " + AppConfig.TABLE_USER + ".workplace LIKE ?"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_USER + ".birthday) >= ?)"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_USER + ".birthday) <= ?) "
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_USER + ".create_date) >= ?)"
                + " AND (? IS NULL OR DATE(" + AppConfig.TABLE_USER + ".create_date) <= ?) ";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, user.getId());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, "%" + user.getName() + "%");
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, "%" + user.getEmail() + "%");
        preparedStatement.setInt(7, AppConfig.roles.get(AppConfig.USER).getId());
        preparedStatement.setString(8, user.getPhone());
        preparedStatement.setString(9, "%" + user.getPhone() + "%");
        preparedStatement.setString(10, user.getJob());
        preparedStatement.setString(11, "%" + user.getJob() + "%");
        preparedStatement.setString(12, user.getGender());
        preparedStatement.setString(13, "%" + user.getGender() + "%");
        preparedStatement.setString(14, user.getHomeTown());
        preparedStatement.setString(15, "%" + user.getHomeTown() + "%");
        preparedStatement.setString(16, user.getWorkplace());
        preparedStatement.setString(17, "%" + user.getWorkplace() + "%");
        preparedStatement.setDate(18, user.getBirthday() == null ? null
                : new Date(user.getBirthday().getTime()));
        preparedStatement.setDate(19, user.getBirthday() == null ? null
                : new Date(user.getBirthday().getTime()));
        preparedStatement.setDate(20, user.getBirthday() == null ? null
                : new Date(user.getBirthday().getTime()));
        preparedStatement.setDate(21, user.getBirthday() == null ? null
                : new Date(user.getBirthday().getTime()));
        preparedStatement.setDate(22, user.getCreateDate() == null ? null
                : new Date(user.getCreateDate().getTime()));
        preparedStatement.setDate(23, user.getCreateDate() == null ? null
                : new Date(user.getCreateDate().getTime()));
        preparedStatement.setDate(24, user.getCreateDate() == null ? null
                : new Date(user.getCreateDate().getTime()));
        preparedStatement.setDate(25, user.getCreateDate() == null ? null
                : new Date(user.getCreateDate().getTime()));

        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public User update(User user) throws SQLException {
        User update_user = null;
        String sql = "UPDATE " + AppConfig.TABLE_USER + " SET email = ?, password = ?, name = ?, phone = ?, avatar = ?" +
                ", job = ?, gender = ?, home_town = ?, workplace = ?, birthday = ?, modify_date = ?" +
                ", modify_by = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getPhone());
        preparedStatement.setString(5, user.getAvatar());
        preparedStatement.setString(6, user.getJob());
        preparedStatement.setString(7, user.getGender());
        preparedStatement.setString(8, user.getHomeTown());
        preparedStatement.setString(9, user.getWorkplace());
        preparedStatement.setDate(10, new Date(user.getBirthday().getTime()));
        preparedStatement.setDate(11, new Date(user.getModifyDate().getTime()));
        preparedStatement.setString(12, user.getModifyBy());
        preparedStatement.setInt(13, user.getId());

        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            update_user = findById(user.getId());
        }

        return update_user;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "UPDATE " + AppConfig.TABLE_USER + " SET deleted = true WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);

        int delete = preparedStatement.executeUpdate();

        return delete >= 0;
    }

    @Override
    public User getUserByEmailAndPassword(LoginForm loginForm) throws SQLException {
        boolean need_user = true;
        boolean need_role = false;
        User user = null;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_USER, "role", need_user,
                        AppConfig.TABLE_ROLE, "id", need_role)
                + ((need_user || need_role) ? " AND " : " WHERE ") + AppConfig.TABLE_USER + ".email = ? AND " +
                AppConfig.TABLE_USER + ".password = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setString(1, loginForm.getEmail());
        preparedStatement.setString(2, loginForm.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.first()) {
            user = getObject(resultSet);
        }

        return user;
    }

    @Override
    public boolean existsByEmailOrPhone(String email, String phone) throws SQLException {
        boolean need_user = true;
        boolean need_role = false;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_USER, "role", need_user,
                        AppConfig.TABLE_ROLE, "id", need_role)
                + ((need_user || need_role) ? " AND " : " WHERE ") + AppConfig.TABLE_USER + ".email = ? AND " +
                AppConfig.TABLE_USER + ".phone = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, phone);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.first();
    }

    @Override
    public List<User> getListByEmailOrPhone(String email, String phone) throws SQLException {
        boolean need_user = true;
        boolean need_role = false;
        String sql = AppConfig
                .createSqlTwoTableSelect(AppConfig.TABLE_USER, "role", need_user,
                        AppConfig.TABLE_ROLE, "id", need_role)
                + ((need_user || need_role) ? " AND " : " WHERE ") + AppConfig.TABLE_USER + ".email = ? AND " +
                AppConfig.TABLE_USER + ".phone = ?";

        PreparedStatement preparedStatement = connection.prepare(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, phone);

        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    @Override
    public List<Role> getRole() throws SQLException {
        String sql = "SELECT * FROM " + AppConfig.TABLE_ROLE;
        PreparedStatement preparedStatement = connection.prepare(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

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
