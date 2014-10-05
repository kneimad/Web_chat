package com.mycompany.servlets.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Damien on 05.10.2014.
 */
public enum ChatAction {
    GetUsers("getUsers", new GetUsers()),
    SendMessage("sendMessage", new SendMessage()),
    GetHistory("getHistory", new GetHistory()),
    Login("login", new Login()),
    Logout("logout", new Logout());
    private static Map<String, ChatAction> actionsByCommand = fillActions();
    private final String clientCommand;
    private final CommandExecutor executor;

    private ChatAction(String clientCommand, CommandExecutor executor) {
        this.clientCommand = clientCommand;
        this.executor = executor;
    }

    private static Map<String, ChatAction> fillActions() {
        Map<String, ChatAction> actions = new HashMap<String, ChatAction>();
        for (ChatAction action : values()) {
            actions.put(action.clientCommand, action);
        }
        return actions;
    }

    public static ChatAction getByCommand(String clientCommand) {
        return actionsByCommand.get(clientCommand);
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        executor.execute(request, response);
    }
}
