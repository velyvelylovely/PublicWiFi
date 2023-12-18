<%@ page import="dto.BookMarkDTO" %>
<%@ page import="dao.BookMarkDAO" %>
<%@ page import="dao.BookMarkGroupDAO" %>
<%@ page import="dto.BookMarkGroupDTO" %>
<%@ page import="dao.WifiDAO" %>
<%@ page import="dto.WifiDTO" %><%--
  Created by IntelliJ IDEA.
  User: KDMIN
  Date: 2023-10-01
  Time: 오전 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");

    BookMarkDAO bookMarkDAO = new BookMarkDAO();
    int affected = bookMarkDAO.deleteBookMark(Integer.parseInt(id));
%>
</body>
<script>
    <%
        String text = affected > 0 ? "북마크 데이터를 삭제하였습니다." : "북마크 데이터를 삭제하지 못하였습니다.";
    %>
    alert("<%=text%>");
    location.href = "bookmarkList.jsp";
</script>
</html>
