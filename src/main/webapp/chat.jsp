 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="chatScript.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="content">
            <span class="banner">CHAT</span>
            <div class="text">
                <div class="history">
                    <span>dshgfsdhfgsd<br /></span>
                    <span>dshgfsdhfgsd</span>
                </div>
                <div class="users">
                    <span>vasya<br/></span>
                    <span>petro</span>
                </div>
            </div>
            <input type="text" class="text_field" placeholder="Введіть повідомлення"/>
            <button class="but">
                <span>Відправити</span>
            </button>
        </div>
        <br/>
        <h3>Ви зареєстровані як користувач:</h3>
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
                          <td> <input type="submit" value="Вийти" name="submit" /><br/></td>
                    </tr>
                </tbody>
            </table>
        </form>

            <table border="0">
                 <tbody>
                    <tr>
                          </br>
                          <strong> Users List </strong>
                          </br>
                    </tr>
                    <tr>
                          <td> <textarea id="users" class="text_field" name="users" placeholder="Тут будуть юзери"></textarea> </td>
                    </tr>
                    <tr>
                          </br>
                          <td> <button onclick="getUsers()">REFRESH</button></td>
                    </tr>
                </tbody>
            </table>

    </body>
</html>
