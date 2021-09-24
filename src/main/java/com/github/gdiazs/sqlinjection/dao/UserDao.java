package com.github.gdiazs.sqlinjection.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;

@Repository
public class UserDao {

    final Logger logger = LoggerFactory.getLogger(UserDao.class);

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean userExists(final String username, final String password){
        String foundUsername = null;
        try (var connection = this.dataSource.getConnection();
             var statement = connection.createStatement()) {

            final var sqlFindByUserNameAndPassword = String.format("SELECT username FROM users WHERE username='%s' and password='%s'", username, password);
            logger.info("sql: {}", sqlFindByUserNameAndPassword);
            final var resultSet = statement.executeQuery(sqlFindByUserNameAndPassword);

            while(resultSet.next()){
                foundUsername = resultSet.getString("username");
            }

        }catch (SQLException sqlException){
            logger.error("Unexpected error executing a query", sqlException );
            return false;
        }
        return foundUsername != null;
    }
}
