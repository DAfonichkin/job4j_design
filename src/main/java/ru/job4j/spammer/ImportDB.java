package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException, IllegalArgumentException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> users.add(convertStringToUser(s)));
        }
        return users;
    }

    private User convertStringToUser(String inputString) {
        String[] stringArray = inputString.split(";");
        if (stringArray.length < 2 || stringArray[0].isBlank() || stringArray[1].isBlank()) {
            throw new IllegalArgumentException();
        }
        return new User(stringArray[0], stringArray[1]);
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            try (Statement statement = cnt.createStatement()) {
                String sql = "create table if not exists users("
                        + "name varchar(250), "
                        + "email varchar(250))";
                statement.execute(sql);
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}