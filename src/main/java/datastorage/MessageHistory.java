package datastorage;

import beans.User;
import beans.UserMessage;
import businesslogic.UserManager;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MessageHistory {
    private static final int MAX_HISTORY_SIZE = 100;
    private static final DateFormat dateFormat = new SimpleDateFormat("[HH:mm:ss] ");
    private static final Logger log = Logger.getLogger(MessageHistory.class);
    private static final MessageHistory instance = new MessageHistory();
    private final LinkedList<UserMessage> history;
    private UserManager userManager = UserManager.getInstance();
    // maybe need to use concurrency collection
    private MessageHistory() {
        history = new LinkedList<UserMessage>();
    }

    public static MessageHistory getInstance() {
        return instance;
    }

    public void addMessage(UserMessage userMessage) {
        synchronized (history) {
            userMessage.setTime(new Date());
            history.addLast(userMessage);
            if (history.size() > MAX_HISTORY_SIZE) {
                history.removeFirst();
            }
        }
    }

    public String extractUserHistory(User user) {
        Date loginTime = userManager.getLoginTime(user);
        if (loginTime == null) {
            return "";
        }
        Iterator<UserMessage> iterator = cloneHistory().iterator();
        StringBuilder stringBuffer = new StringBuilder();

        log.info("\nLogin time:" + loginTime);
        while (iterator.hasNext()) {
            UserMessage userMessage = iterator.next();
            log.info("\nMsg time: " + userMessage.getTime() + "  need to be shown:  " + (loginTime.before(userMessage.getTime())));
            if (loginTime.before(userMessage.getTime())) {
                appendMessage(userMessage, stringBuffer);
                break;
            }
        }
        while (iterator.hasNext()) {
            appendMessage(iterator.next(), stringBuffer);
        }
        return ("<userMessages>" + stringBuffer.toString() + "</userMessages>");
    }

    // remove to business-logic package
    private void appendMessage(UserMessage userMessage, StringBuilder stringBuilder) {
        stringBuilder.append("<userMessage>");
        stringBuilder.append("<time>" + dateFormat.format(userMessage.getTime()) + "</time>");
        stringBuilder.append("<name>" + userMessage.getUser().getName() + "</name>");
        stringBuilder.append("<message>: " + userMessage.getMessage() + "</message>");
        stringBuilder.append("</userMessage>");
    }

    // maybe copyOnWriteArrayList
    private List<UserMessage> cloneHistory() {
        synchronized (history) {
            // maybe need to use concurrency collection
            return new ArrayList<UserMessage>(history);
        }
    }
}
