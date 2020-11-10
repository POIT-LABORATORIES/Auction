<%--
  Created by IntelliJ IDEA.
  User: KIRILL
  Date: 11.11.2020
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import = "java.io.*,java.util.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li>Hello</li>
<%
    PrintWriter writer = response.getWriter();
    File dir = new File("src\\main\\webapp\\HTML");
    if(dir.isDirectory()){
        for(File file : dir.listFiles()){
            writer.println("<li>" + file.getName() + "</li>");
        }
    } else{
        writer.println("<li>Not dir</li>");
    }
    writer.println("<li>Generated</li>");
    writer.close();
%>
</ul>
</body>
</html>
