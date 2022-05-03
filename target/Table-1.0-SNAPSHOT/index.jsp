<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Schedule</title>
</head>
<body>
<%--<h1><%= "Hello World!" %>--%>
<%--</h1>--%>
<%--<br/>--%>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<%--<form action="Servlet" method="get" >--%>
<%--    <input type="submit" value="Select file" name="select"/>--%>
<%--</form>--%>
<form method="get" action="Servlet" enctype="multipart/form-data">
    <label for="file">Choose file to upload</label>
    <input type="file" id="file" name="file" multiple />
    <br>
    <input type="submit" value="Submit" name="select"/>
</form>
</body>
</html>