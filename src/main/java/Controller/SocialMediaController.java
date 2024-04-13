package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;
import java.util.Objects;

import Model.Message;
import Model.Account;
import Service.MessageService;
import Service.AccountService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// import Service.AccountService;


/**
 * @TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController() {
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.post("/register", )
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageBodyHandler);
        app.delete("messages/{message_id}", this::deleteMessageByIdHandler);
        

        return app;
    }

    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account accountToLogin = accountService.getAccountByUsername(account.getUsername());
        if (accountToLogin != null)
            if (account.getPassword().equals(accountToLogin.getPassword())) {
                ctx.json(mapper.writeValueAsString(accountToLogin));
            }
        } else {
            ctx.status(401);
        }
    }

    private void createMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message createdMessage = messageService.createMessage(message);
        if (createdMessage != null) {
            ctx.json(mapper.writeValueAsString(createdMessage));
        } else {
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getAllMessagesByUserIdHandler(Context ctx) {
        int accountId = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("account_id")));
        List<Message> messages = messageService.getAllMessagesByUserId(accountId);
        ctx.json(messages);
    }

    private void getMessageByIdHandler(Context ctx) {
        int messageId = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("message_id")));
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            ctx.json(message);
        }
    }

    private void updateMessageBodyHandler(Context ctx) throws JsonMappingException, JsonProcessingException {
        int messageId = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("message_id")));
        Message message = messageService.getMessageById(messageId);
        ObjectMapper mapper = new ObjectMapper();
        Message messageWithText = mapper.readValue(ctx.body(), Message.class);

        if (message != null 
        && messageWithText.getMessage_text().length() <= 255
        && !messageWithText.getMessage_text().isEmpty()) {
            ctx.json(messageService.updateMessage(message.getMessage_id(), 
                new Message(
                    message.getMessage_id(),
                    message.getPosted_by(),
                    messageWithText.getMessage_text(),
                    message.getTime_posted_epoch()
            )));
        } else {
            ctx.status(400);
        }
        
    }


    private void deleteMessageByIdHandler(Context ctx) {
        int messageId = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("message_id")));
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            if (messageService.deleteMessageById(messageId)) {
                ctx.json(message);
            }
        }
    }
}