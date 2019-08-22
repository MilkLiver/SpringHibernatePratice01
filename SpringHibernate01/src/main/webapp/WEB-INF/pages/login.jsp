<%--
  Created by IntelliJ IDEA.
  User: NEIL
  Date: 2019/8/20
  Time: 下午 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" modelAttribute="UserEntity">
    <h2>User Login</h2>
    <table>
        <tr>
            <td>
                <table border="5px">
                    <tr>
                        <td>
                            <span>username:</span>
                        </td>
                        <td>
                            <input type="text" name="username"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span>password:</span>
                        </td>
                        <td>
                            <input type="password" name="password"/>
                        </td>
                    </tr>
                </table>
            </td>
            <td valign="bottom">
                <input type="submit" name="login" value="登入"/>
            </td>
        </tr>
    </table>
</form>
<h3 style="color: red">${warning}</h3>
</body>
</html>
