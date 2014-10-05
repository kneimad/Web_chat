package com.mycompany.servlets.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Damien on 05.10.2014.
 */
public interface CommandExecutor {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
