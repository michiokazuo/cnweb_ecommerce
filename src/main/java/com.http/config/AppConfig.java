package com.http.config;

public class AppConfig {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL_DATABASE = "jdbc:mysql://localhost:3306/cnweb";
    public static final String USERNAME = "root"; // cần đổi username theo db của idol
    public static final String PASSWORD = "20012000"; // cần đổi password theo db của idol

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
                + (referenceHasDelete1 ? ((rootHasDelete ? "AND " : " ") + referenceTable1 + ".deleted = false") : " ")
                + (referenceHasDelete2 ? ((rootHasDelete || referenceHasDelete1 ? "AND " : " ")
                + referenceTable2 + ".deleted = false") : "")));
    }
}
