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
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>북마크 삭제</h1>
<%@include file="header.jsp"%>
<h4>북마크를 삭제하시겠습니까?</h4>
<%
    String id = request.getParameter("id");

    //북마크
    BookMarkDAO bookMarkDAO = new BookMarkDAO();
    BookMarkDTO bookMarkDTO = bookMarkDAO.selectBookMarkList(Integer.parseInt(id));

    //북마크 그룹
    BookMarkGroupDAO bookMarkGroupDAO = new BookMarkGroupDAO();
    BookMarkGroupDTO bookMarkGroupDTO = bookMarkGroupDAO.selectBookMarkGroup(bookMarkDTO.getGroupNo());

    //와이파이 정보
    WifiDAO wifiDAO = new WifiDAO();
    WifiDTO wifiDTO = wifiDAO.selectWifi(bookMarkDTO.getMgrNo());
    %>
</body>
<form method="post" action="bookmarkDelete_submit.jsp">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td><%=bookMarkGroupDTO.getName()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=wifiDTO.getXSwifiMainNm()%></td>
        </tr>
        <tr>
            <th>등록일자</th>
            <td><%=bookMarkDTO.getRegister_dttm()%></td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="bookmarkList.jsp">돌아가기</a>
                <input type="submit" name="delete" value="삭제">
                <input type="hidden" name="id" value="<%=bookMarkDTO.getId()%>">
            </td>
        </tr>
    </table>
</form>
</html>
