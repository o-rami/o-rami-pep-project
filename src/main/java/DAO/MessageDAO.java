package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class MessageDAO {

    /*
     * Create new message
     * @TODO:
     * @return 
     */
    public boolean createMessage() {
        return false;
    }


    /* 
     * @TODO: retrieve all messages from the Message table
     * @return all messages
     */
    public List<Message> retrieveAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        
        try {
            String sql = "select * from message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                    );
                messages.add(message);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return messages;
    }


    /* 
     * @TODO: Get message by ID
     * @return a message
     */
    public Message retrieveMessage(int messageId) {
        Connection connection = ConnectionUtil.getConnection();
        Message messageToReturn = new Message();

        try {
            String sql = "select * from message where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, messageId);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                messageToReturn.setMessage_id(rs.getInt("message_id"));
                messageToReturn.setPosted_by(rs.getInt("posted_by"));
                messageToReturn.setMessage_text(rs.getString("message_text"));
                messageToReturn.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return messageToReturn;
    }

    
    /*
     * Update message by id
     * @TODO:
     * @return 
     */
    public Message updateMessage(int messageId) {
        return null;
    }


    /*
     * Delete message by id
     * @TODO:
     * @return 
     */
    public boolean deleteMessage(int messageId) {
        return false;
    }

    /*
     * Get all messages by account id
     * @TODO:
     * @return 
     */
    public List<Message> getAllMessagesByUser(int accountId) {
        return null;
    }
    
}
