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
    <body>
        <div class="content">
            <span class="banner">CHAT</span>
            <div class="text">
                <div class="history">
                    <span>no messages yet<br /></span>
                </div>
                <div class="users">
                    <span>no users yet<br/></span>
                </div>
            </div>
            <input id="chattext" type="text" class="text_field" placeholder="Введіть повідомлення"/>
            <button id="sendmsg" class="but">
                <span>Відправити</span>
            </button>
        <br/>
        <h3 class="banner">Ви зареєстровані як користувач:</h3>
        <% //String login=(String)request.getAttribute("login");
           String login=session.getAttribute("login").toString();
           String ipAddress=request.getRemoteAddr();
        %>
        Ваш логін:<%= login %><br/>
        Ваша IP адреса: <%= ipAddress %>

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
