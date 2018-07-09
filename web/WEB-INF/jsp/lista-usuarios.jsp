<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: cpol
  Date: 31/05/2017
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Usuarios</title>
</head>
    <body>

    <c:import url="menu.jsp"></c:import>


    <table style="width:100%">
        <tr>
            <th>Nome do Usuario</th>
            <th>Login</th>
            <th>Opcoes</th>
        </tr>

        <c:forEach items="${usuarios}" var="u">
            <tr>
                <td> <c:out value="${u.nome}"/> </td>
                <td> <c:out value="${u.login}"/> </td>
                <td><a href="edita-usuario.admin?id=${u.id}">Editar</a></td>
                <td><a href="remove-usuario.admin?id=${u.id}">Apagar</a></td>


            </tr>
        </c:forEach>

    </table>




    </body>
</html>
