package org.all.info.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("src\\main\\resources\\jdbc.properties")));
            String JDBCDriver = (String) properties.get("db.driver");
            String url = (String) properties.get("db.url");
            String user = (String) properties.get("db.user");
            String pass = (String) properties.get("db.passr");
            Class.forName(JDBCDriver);
            connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
