package com.http.config;

import com.http.dao.UserDao;
import com.http.dao_impl.UserDaoImpl;
import com.http.dto.UserDTO;
import com.http.model.Role;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AppConfig {

    public static UserDTO userInSysTem = null;

    public static final String PATH_SAVE_FILES
            = "D:/Eclipse-workspace/TOMCAT/apache-tomcat-9.0.29/webapps"; // các idol sửa lại link theo máy các idol

    public final static String ROOT_BE = "http://localhost:8080";
    public final static String SAVE_DIRECTORY = "file-upload";

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL_DATABASE = "jdbc:mysql://localhost:3306/cnweb";
    public static final String USERNAME = "root"; // cần đổi username theo db của idol
    public static final String PASSWORD = ""; // cần đổi password theo db của idol

    public static final String TABLE_USER = "user";
    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_BILL = "bill";
    public static final String TABLE_ROLE = "role";
    public static final String TABLE_BILL_HAS_PRODUCT = "bill_has_product";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_COMMENT = "comment";

    /*  rootHasDelete, referenceHasDelete:
        table has column deleted or not
        if yes, content is value column
     */

    public static String createSqlTwoTableSelect(String rootTable, String rootColumn, Boolean rootHasDelete
            , String referenceTable, String referenceColumn, Boolean referenceHasDelete) {
        return "SELECT * FROM " + rootTable + " JOIN " + referenceTable + " ON " + rootTable + "." + rootColumn + " = "
                + referenceTable + "." + referenceColumn
                + ((!rootHasDelete && !referenceHasDelete) ? " "
                : (" WHERE " + (rootHasDelete ? (rootTable + ".deleted = false ") : " ")
                + (referenceHasDelete ? ((rootHasDelete ? "AND " : " ") + referenceTable + ".deleted = false ") : " ")));
    }

    public static String createSqlThreeTableSelect(String rootTable, String rootColumn1, String rootColumn2,
                                                   Boolean rootHasDelete
            , String referenceTable1, String referenceColumn1, Boolean referenceHasDelete1
            , String referenceTable2, String referenceColumn2, Boolean referenceHasDelete2) {
        return "SELECT * FROM " + rootTable + " JOIN " + referenceTable1 + " ON " + rootTable + "." + rootColumn1 + " = "
                + referenceTable1 + "." + referenceColumn1 + " JOIN " + referenceTable2 + " ON " +
                rootTable + "." + rootColumn2 + " = " + referenceTable2 + "." + referenceColumn2
                + ((!rootHasDelete && !referenceHasDelete1 && !referenceHasDelete2) ? " "
                : (" WHERE " + (rootHasDelete ? (rootTable + ".deleted = false ") : " ")
                + (referenceHasDelete1 ? ((rootHasDelete ? " AND " : " ") + referenceTable1 + ".deleted = false") : " ")
                + (referenceHasDelete2 ? ((rootHasDelete || referenceHasDelete1 ? " AND " : " ")
                + referenceTable2 + ".deleted = false") : "")));
    }

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static HashMap<String, Role> roles = new HashMap<>();

    public static void setRole() throws SQLException {
        UserDao userDao = new UserDaoImpl();
        List<Role> roleList = userDao.getRole();
        for (Role role : roleList) {
            if (role.getName().equals(ADMIN))
                roles.put(ADMIN, role);
            else if (role.getName().equals(USER))
                roles.put(USER, role);
        }
    }

    public static boolean checkAdmin(UserDTO userDTO) {
        return userDTO != null && userDTO.getRole().getName().equals(roles.get(ADMIN).getName());
    }

    public static boolean checkUser(UserDTO userDTO) {
        return userDTO != null && userDTO.getRole().getName().equals(roles.get(USER).getName());
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(new Timestamp(new Date().getTime()));
    }
}
