package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists %s()",
                    tableName
            );
            statement.execute(sql);
         }
    }

    public void dropTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Drop table %s;",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception  {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Alter table %s add %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Alter table %s drop column %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Alter table %s rename column %s to %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream is = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(is);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            String table = "test_table";
            tableEditor.createTable(table);
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.dropTable(table);
            tableEditor.createTable(table);
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.addColumn(table, "id", "serial primary key");
            tableEditor.addColumn(table, "name", "varchar(50)");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.dropColumn(table, "name");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.addColumn(table, "name", "varchar(50)");
            tableEditor.renameColumn(table, "name", "Name2");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.dropTable(table);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}