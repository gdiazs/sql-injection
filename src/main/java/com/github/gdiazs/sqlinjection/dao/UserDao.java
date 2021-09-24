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
        final var sqlFindByUserNameAndPassword = "SELECT username FROM users WHERE username=? and password=?";

        String foundUsername = null;
        try (var connection = this.dataSource.getConnection();
             var statement = connection.prepareStatement(sqlFindByUserNameAndPassword)) {

            logger.info("sql: {}", sqlFindByUserNameAndPassword);
            statement.setString( 1, username );
            statement.setString( 2, password );

            final var resultSet = statement.executeQuery();

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
