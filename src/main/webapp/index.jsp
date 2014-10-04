<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="font-family: arial;">
        <h1 style="font-size: 20px; font-weight: bold;">Оберіть логін для чату:</h1>
        <form name="hello" action="LoginServlet" method="post">   
            <table border="0">
 
                <tbody>
                    <tr>
                        <td>Логін: </td>
                        <td><input type="text" name="login" value="" size="15" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td> <input type="submit" value="Підтвердити" name="submit"  style="font-weight: bold; color:#ffffff; background-color:#18e351"/><br/></td>
                    </tr>
                </tbody>
            </table>
        </form>  
    </body>
</html>