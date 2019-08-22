<%--
  Created by IntelliJ IDEA.
  User: NEIL
  Date: 2019/8/21
  Time: 下午 01:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>上傳檔案</h2>
<form method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><input type="file" name="File" value="選擇檔案" multiple/></td>
            <td>&nbsp;<input type="submit" value="上傳"/></td>
        </tr>
    </table>
</form>
</body>
</html>
