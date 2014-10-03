package com.mycompany.datastorage;

import com.mycompany.beans.User;
import com.mycompany.beans.UserMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class MessageHistory {
    private static final int MAX_HISTORY_SIZE = 100;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static MessageHistory instance = new MessageHistory();
    private LinkedList<UserMessage> history;

    public static MessageHistory getInstance()
    {
        return instance;
    }

    private MessageHistory() {
        history = new LinkedList<UserMessage>();
    }

    public void addMessage(UserMessage userMessage)
    {
        synchronized(history)
        {
            userMessage.setTime(new Date());
            history.addLast(userMessage);
            if(history.size()>MAX_HISTORY_SIZE)
            {
                history.removeFirst();
            }
        }
    }
    
    public String extractUserHistory(User user)
    {
        Iterator<UserMessage> iterator = new ArrayList<UserMessage>(history).iterator();
        StringBuffer stringBuffer = new StringBuffer();
        Date loginTime = user.getLoginTime();
        UserMessage userMessage;
        while (iterator.hasNext())
        {
            userMessage = iterator.next();
            if(loginTime.before(userMessage.getTime()))
            {
                appendMessage(userMessage, stringBuffer);
                break;
            }
        }
        while (iterator.hasNext())
        {
            appendMessage(iterator.next(), stringBuffer);
        }        
        return ("<userMessages>" + stringBuffer.toString() + "</userMessages>");
    }
    
    private void appendMessage(UserMessage userMessage, StringBuffer stringBuffer)
    {
        stringBuffer.append("<userMessage>");
        stringBuffer.append("<time>" + dateFormat.format(userMessage.getTime()) + "</time>");
        stringBuffer.append("<name>" + userMessage.getUser().getName() + "</name>");
        stringBuffer.append("<message>" + userMessage.getMessage()+ "</message>");
        stringBuffer.append("</userMessage>");    
    }
}
