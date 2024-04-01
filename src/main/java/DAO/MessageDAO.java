package DAO;

import Model.Message;

import java.util.List;
import java.util.ArrayList;
import Util.ConnectionUtil;


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
     * Get all messages
     * @TODO: retrieve all messages from the Message table
     * @return all messages
     */
    public List<Message> getAllMessages() {
        return null;
    }


    /* 
     * @TODO: Get message by Id
     * @return a message
     */
    public Message getMessage(int messageId) {
        return null;
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
