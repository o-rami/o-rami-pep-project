package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.*;



public class AccountDao {

    // User registration
    public Account createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = (int) rs.getLong(1);
                return new Account(
                    generatedId, 
                    account.getUsername(), 
                    account.getPassword()
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}

    

