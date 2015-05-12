package org.all.info.dao.jdbc;

import org.all.info.util.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class GeneralDAO {

    public final Logger log = LogManager.getLogger(this.getClass());

    public boolean isExist(String name, String table){
        String sql = "SELECT * FROM " + table + " WHERE name = '" + name + "' LIMIT 1;";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
