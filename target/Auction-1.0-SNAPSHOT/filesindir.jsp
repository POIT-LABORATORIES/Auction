<%--
  Created by IntelliJ IDEA.
  User: KIRILL
  Date: 11.11.2020
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.commons.io.FileUtils" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
<%
    File tempDirectory = new File(FileUtils.getTempDirectoryPath());
    File dir = new File("../webapps/Auction/");
    if(dir.isDirectory()){
        for(File file : tempDirectory.listFiles()){
            out.println("<li>" + file.getName() + "</li>");
        }
    } else{
        out.println("<li>Not dir</li>");
    }
    if (dir.isFile())
        out.println("<li>html file</li>");
    else
        out.println("<li>not file either</li>");
%>
</ul>
</body>
</html>
