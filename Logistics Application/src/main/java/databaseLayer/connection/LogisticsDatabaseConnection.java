package databaseLayer.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LogisticsDatabaseConnection implements ILogisticsDatabaseConnection
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LogisticsDatabaseConnection.class);

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + System.getenv("DB_HOST") + ":" + System.getenv("DB_PORT") + "/" + System.getenv("DB_NAME");
            connection = DriverManager.getConnection(
                    url + "?user=" + System.getenv("DB_USERNAME") + "&password=" + System.getenv("DB_PASSWORD") +
                            "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        } catch (ClassNotFoundException | SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return connection;
    }


    public Statement createStatement() {
        try {
            getConnection();
            statement = this.connection.createStatement();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return statement;
    }

    public ResultSet executeQuery(String query) {
        try {
            resultSet = createStatement().executeQuery(query);
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException.getMessage());
        }
        return resultSet;
    }

    public void closeConnection() {
        try {
            if (null == connection) {
                throw new SQLException("Unable to close a connection!");
            }
            connection.close();
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException.getMessage());
        }
    }

    public PreparedStatement createPreparedStatement(String query) {
        try {
            getConnection();
            preparedStatement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return preparedStatement;
    }

    public int executeUpdate(String query) {
        int executeUpdate = 0;
        try {
            executeUpdate = createStatement().executeUpdate(query);
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException.getMessage());
        }
        return executeUpdate;
    }
}