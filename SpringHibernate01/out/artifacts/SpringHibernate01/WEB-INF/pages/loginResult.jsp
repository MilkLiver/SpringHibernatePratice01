<%--
  Created by IntelliJ IDEA.
  User: NEIL
  Date: 2019/8/20
  Time: 下午 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h2>User ${username} Login Success!</h2>
<h3>${info}</h3><br/>
<table>
    <tr>
        <td width="200px"><a href="/SpringHibernate01/fileUpload">Upload File</a></td>
        <td width="200px"><a href="/SpringHibernate01/fileDownload">Download File</a></td>
    </tr>
</table>
</body>
</html>
