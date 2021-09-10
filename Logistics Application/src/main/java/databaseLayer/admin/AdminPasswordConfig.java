package databaseLayer.admin;

import databaseLayer.connection.ILogisticsDatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;

public class AdminPasswordConfig implements IAdminPasswordConfig
{
	private String id;
	private int lengthOfPassword;
	private int noOfUpperCase;
	private int noOfLowerCase;
	private int noOfSpecialCharacters;
	private int noOfDigits;
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminPasswordConfig.class);
	private final ILogisticsDatabaseConnection databaseConnection;

	public AdminPasswordConfig(ILogisticsDatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
		performUpdates();
	}

	private void performUpdates() {
		try {
			String str = "SELECT * FROM pwd_config_admin";
			ResultSet rs = databaseConnection.executeQuery(str);

			while (rs.next()) {
				id = rs.getString(1);
				lengthOfPassword = rs.getInt(2);
				noOfUpperCase = rs.getInt(3);
				noOfLowerCase = rs.getInt(4);
				noOfDigits = rs.getInt(6);
				noOfSpecialCharacters = rs.getInt(5);
			}
			rs.close();
			databaseConnection.closeConnection();
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
	}

	public int getLengthOfPassword()
	{
		return lengthOfPassword;
	}
	
	public void setLengthOfPassword(int lengthOfPassword) 
	{
		String str = "UPDATE pwd_config_admin SET length =" +lengthOfPassword+ " WHERE adminID = "  + "'" + id + "'";
		if(lengthOfPassword > 8) {
			this.lengthOfPassword = lengthOfPassword;
			this.databaseConnection.executeUpdate(str);
		}
		this.databaseConnection.closeConnection();
	}
	
	public int getNoOfUpperCase() 
	{
		return noOfUpperCase;
	}
	
	public void setNoOfUpperCase(int noOfUpperCase) 
	{
		String str = "UPDATE pwd_config_admin SET 'uppercase' = " + noOfUpperCase + "WHERE adminID = " + id;
		if(noOfUpperCase > 1)
		{
			this.noOfUpperCase = noOfUpperCase;
			this.databaseConnection.executeUpdate(str);
		}
		this.databaseConnection.closeConnection();

	}
	
	public int getNoOfLowerCase() 
	{
		return noOfLowerCase;
	}
	
	public void setNoOfLowerCase(int noOfLowerCase)
	{
		String str = "UPDATE pwd_config_admin SET 'lowercase' = " + noOfLowerCase + "WHERE adminID = " + id;
		if(noOfLowerCase > 1)
		{
			this.noOfLowerCase = noOfLowerCase;
			this.databaseConnection.executeUpdate(str);
		}
		this.databaseConnection.closeConnection();

	}
	
	public int getNoOfSpecialCharacters()
	{
		return noOfSpecialCharacters;
	}
	
	public void setNoOfSpecialCharacters(int noOfSpecialCharacters) {
		String str = "UPDATE pwd_config_admin SET 'specialcase' = " + noOfSpecialCharacters + "WHERE adminID = " + id;
		if(noOfSpecialCharacters > 1)
		{
			this.noOfSpecialCharacters = noOfSpecialCharacters;
			this.databaseConnection.executeUpdate(str);
		}
		this.databaseConnection.closeConnection();
	}
	
	public int getNoOfDigits() 
	{
		return noOfDigits;
	}
	
	public void setNoOfDigits(int noOfDigits) {
		String str = "UPDATE pwd_config_admin SET 'digit' = " + noOfDigits + "WHERE adminID = " + id;
		if(noOfDigits < 1) {
			this.noOfDigits = noOfDigits;
			this.databaseConnection.executeUpdate(str);
		}
		this.databaseConnection.closeConnection();
	}

}
