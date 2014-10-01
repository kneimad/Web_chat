 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Новий користувач:</h3>
        <% String login=(String)request.getAttribute("login"); 
           //String login=session.getAttribute("login").toString();
           String ipAddress=request.getRemoteAddr();
        %>
        Ваш логін:<%= login %><br/>
        Ваша IP адреса: <%= ipAddress %>
        
        <form name="hello" action="ChatServlet" method="post">   
            <table border="0">
                 <tbody>
                    <tr>
                          <td> <input type="submit" value="Вийти" name="submit" /><br/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
