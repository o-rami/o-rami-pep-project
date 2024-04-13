package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;
import java.util.Objects;

import Model.Message;
import Service.MessageService;
// import Service.AccountService;


/**
 * @TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    // AccountService accountService;

    public SocialMediaController() {
        this.messageService = new MessageService();
        // this.accountService = new AccountService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserIdHandler);
        // app.post("/login", )
        // app.post("/messages", this.createMessageHandler);
        // app.patch("/messages/{message_id}", this::updateMessageBodyHandler);
        // app.delete("messages/{message_id}", this::deleteMessageByIdHandler);
        

        return app;
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

    private void getMessageHandler(Context ctx) {
        int messageId = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("message_id")));
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            ctx.json(message);
        }
    }
}