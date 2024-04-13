package Service;

import DAO.MessageDao;
import Model.Message;
import java.util.List;

public class MessageService {
    MessageDao messageDao;

    public MessageService(){
        messageDao = new MessageDao();
    }

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Message createMessage(Message message) {
        if (message.getMessage_text().isBlank() 
            || message.getMessage_text().isEmpty()
            || message.getMessage_text().length() > 255) {
            return null;
        }
        if (messageDao.getAllMessagesByUserId(message.getPosted_by()).isEmpty()) {
            return null;
        }

        List<Message> messages = messageDao.getAllMessages();
        if (messages.contains(message)) {
            return null;
        }
        return messageDao.createMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    public List<Message> getAllMessagesByUserId(int accountId) {
        return messageDao.getAllMessagesByUserId(accountId);
    }

    public Message getMessageById(int messageId) {
        return messageDao.getMessageById(messageId);
    }

    public Message updateMessage(int messageId, Message message) {
        if (messageDao.getMessageById(messageId) == null) {
            return null;
        }
        message.setMessage_id(messageId);
        messageDao.updateMessage(messageId, message);
        return message;
    }

    public boolean deleteMessageById(int messageId) {
        return messageDao.deleteMessageById(messageId);
    }
}
