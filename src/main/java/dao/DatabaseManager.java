package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.AccountPojo;


public class DatabaseManager implements AccountDao{
	
	public static DatabaseManager databaseManager = null;
	
	private DatabaseManager() {}
	
	public static DatabaseManager getInstance() {
		if(databaseManager == null) {
			databaseManager = new DatabaseManager();
		}
		return databaseManager;
	}
	
		
	@Override
	public List<AccountPojo> getAllAccount() {
		
		try {
			Connection newConnection = DBUtil.makeConnection();
			Statement statement = newConnection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM account;");
			List<AccountPojo> accountPojos = new ArrayList<AccountPojo>();
			
			while(resultSet.next()) {
				AccountPojo temPojo = new AccountPojo();
				temPojo.setId(resultSet.getInt("id"));
				temPojo.setFirstName(resultSet.getString("first_name"));
				temPojo.setLastName(resultSet.getString("last_name"));
				temPojo.setPassword(resultSet.getString("password"));
				temPojo.setUserName(resultSet.getString("user_name"));
				temPojo.setBalance(resultSet.getFloat("balance"));
				accountPojos.add(temPojo);
			}
			return accountPojos;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public AccountPojo createAccount(AccountPojo accountPojo) {
		
		String sqlString = "INSERT INTO account(first_name,last_name,password,balance,user_name) VALUES(?,?,?,?,?);";
		try{
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareStatement(sqlString,PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, accountPojo.getFirstName());
			preparedStatement.setString(2, accountPojo.getLastName());
			preparedStatement.setString(3, accountPojo.getPassword());
			preparedStatement.setDouble(4, accountPojo.getBalance());
			preparedStatement.setString(5, accountPojo.getUserName());
			
			int rowsUpdated = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while(resultSet.next()) {
				accountPojo.setId(resultSet.getInt("id"));
			}
			if(rowsUpdated > 0) return accountPojo;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public AccountPojo updateAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		
		String sqlString = "UPDATE account SET balance = ? WHERE id = ?";
		try{
			Connection newcConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newcConnection.prepareStatement(sqlString);
			preparedStatement.setDouble(1, accountPojo.getBalance());
			preparedStatement.setInt(2, accountPojo.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected > 0); return accountPojo;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	
	

	@Override
	public void deleteAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public AccountPojo getOneAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		
		String sqlString = "SELECT * FROM account WHERE user_name= ?";
		try{
			Connection newConnection = DBUtil.makeConnection();
			PreparedStatement preparedStatement = newConnection.prepareCall(sqlString);
			preparedStatement.setString(1, accountPojo.getUserName());
			ResultSet resultSet = preparedStatement.executeQuery();
			boolean isnull = true;
			while(resultSet.next()) {
				isnull = false;
				accountPojo.setId(resultSet.getInt("id"));
				accountPojo.setBalance(resultSet.getFloat("balance"));
				accountPojo.setFirstName(resultSet.getString("first_name"));
				accountPojo.setLastName(resultSet.getString("last_name"));
				accountPojo.setUserName(resultSet.getString("user_name"));
				accountPojo.setPassword(resultSet.getString("password"));
			}
			if(isnull) return null;
			return accountPojo;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}