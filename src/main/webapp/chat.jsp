 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="chatScript.js"></script>
        <title>JSP Page</title>
    </head>
    <body onload = "getUsers();">
        <div class="content">
            <span></br></span>
            <span class="banner">CHAT</span></br>
            <form name="chat" action="ChatServlet" method="post">
                <textarea id="msgList" class="history" placeholder="Message History" readonly></textarea>
                <textarea id="userList" class="users" placeholder="Users List" readonly></textarea>
            </form>
            <input id="chattext" type="text" class="text_field" placeholder="Введіть повідомлення"/>
            <button id="sendmsg" class="but">
                <span>Відправити</span>
            </button>
        <br/>
        <h3 class="banner">Ви зареєстровані як користувач:</h3>
        <%
           String login=session.getAttribute("login").toString();
           String ipAddress=request.getRemoteAddr();
        %>
        Ваш логін: <input id="login" type="text" value="<%= login %>" readonly/><br/><br/>
        Ваша IP адреса: <input id="ipAddr" type="text" value="<%= ipAddress %>" readonly/><br/><br/>

        <form name="hello" action="LogoutServlet" method="post">
            <table border="0">
                <tbody>
                    <tr>
                        <td> <input id="exit" type="submit" value="Вийти" name="submit" /><br/></td>
                    </tr>
                </tbody>
            </table>
        </form>
        </div>
    </body>
</html>
