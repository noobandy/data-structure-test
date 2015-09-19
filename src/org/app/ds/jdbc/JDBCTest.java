/**
 * @filenameName:org.app.ds.jdbc.JDBCTest.java
 * @description:TODO
 * @author anandm
 * @date Aug 12, 2015 1:33:46 PM
 * @version: TODO
 */
package org.app.ds.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @className:org.app.ds.jdbc.JDBCTest.java
 * @description:TODO
 * @author anandm
 * @date Aug 12, 2015 1:33:46 PM
 */
public class JDBCTest {

    public void testStoredProcedure() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila", "root", "joinme260");
        CallableStatement callableStatement = connection
                .prepareCall("call film_in_stock(?,?,?)");

        callableStatement.setInt(1, 1);
        callableStatement.setInt(2, 1);
        callableStatement.registerOutParameter(3, Types.INTEGER);

        ResultSet resultSet = callableStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }

        connection.close();
    }

    public void testFunction() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila", "root", "joinme260");
        CallableStatement callableStatement = connection
                .prepareCall("select inventory_in_stock(?)");

        callableStatement.setInt(1, 1);

        ResultSet resultSet = callableStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getBoolean(1));
        }

        connection.close();
    }

    public void testMetadata() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila", "root", "joinme260");
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(
                connection.getCatalog(), null, null, null);
        /*
         * ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); for
         * (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
         * System.out.print(resultSetMetaData.getColumnLabel(i));
         * System.out.print("\t"); }
         * 
         * System.out.println();
         */
        while (resultSet.next()) {
            System.out.println(resultSet.getString(3));
        }
        connection.close();
    }

    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, SQLException {
        /*
         * Class<?> driverClass = Class.forName("com.mysql.jdbc.Driver"); Driver
         * driver = (Driver) driverClass.newInstance();
         * 
         * DriverManager.registerDriver(driver);
         */

        JDBCTest jdbcTest = new JDBCTest();
        jdbcTest.testStoredProcedure();
        jdbcTest.testFunction();
        jdbcTest.testMetadata();

    }
}
