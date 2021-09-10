package databaseLayer.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public interface ILogisticsDatabaseConnection
{
    public Connection getConnection();
    public Statement createStatement();
    public ResultSet executeQuery(String query);
    public void closeConnection();
    public PreparedStatement createPreparedStatement(String query);
    public int executeUpdate(String query);
}
