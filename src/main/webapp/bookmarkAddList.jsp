<%@ page import="dao.BookMarkDAO" %>
<%@ page import="dto.BookMarkDTO" %><%--
  Created by IntelliJ IDEA.
  User: KDMIN
  Date: 2023-09-29
  Time: 오전 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>북마크 그룹 추가</h1>
<%
    request.setCharacterEncoding("UTF-8");

    String mgrNo = request.getParameter("mgrNo");
    String gId = request.getParameter("gId");

    // 값이 none일 경우 이전 페이지로 이동
    if (gId.equals("none")) {
        response.sendRedirect(request.getHeader("Referer"));
        return;
    }

    BookMarkDTO bookMarkDTO = new BookMarkDTO();
    bookMarkDTO.setMgrNo(mgrNo);
    bookMarkDTO.setGroupNo(Integer.parseInt(gId));

    BookMarkDAO bookMarkDAO = new BookMarkDAO();
    int affected = bookMarkDAO.insertBookMark(bookMarkDTO);
%>
</body>
<script>
    <%
        String text = affected > 0 ? "북마크 데이터 추가하였습니다." : "북마크 데이터 추가 실패하였습니다.";
    %>
    alert("<%= text %>");
    location.href = "bookmarkList.jsp";
</script>
</html>
