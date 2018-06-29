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

        <%--<c:forEach items="${usuarios}" var="u">--%>
            <%--${u.nome} / ${u.login} / ${u.senha} / <a href="edita-usuario.admin?id=${u.id}">Editar</a> / <a href="remove-usuario.admin?id=${u.id}">Apagar</a><br />--%>
            <%--<ul>--%>
                <%--<c:forEach items="${u.logs}" var="l" >--%>
                    <%--<li>alterou ${l.idObjeto}@${l.classe} em <fmt:formatDate value="${l.dataHora}" pattern="dd/MM/yyyy 'às' HH:mm"/> </li>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</c:forEach>--%>


    <table style="width:100%">
        <tr>
            <th>Nome do Usuario</th>
            <th>Login</th>
            <th>Opcoes</th>
        </tr>

        <c:forEach items="${usuarios}" var="u">
            <tr>
                <td> ${u.nome} </td>
                <td> ${u.login} </td>
                <td><a href="edita-usuario.admin?id=${u.id}">Editar</a></td>
                <td><a href="remove-usuario.admin?id=${u.id}">Apagar</a></td>

                <ul>
                    <c:forEach items="${u.logs}" var="l" >
                        <li>alterou ${l.idObjeto}@${l.classe} em <fmt:formatDate value="${l.dataHora}" pattern="dd/MM/yyyy 'às' HH:mm"/> </li>
                    </c:forEach>
                </ul>


            </tr>
        </c:forEach>

    </table>




    </body>
</html>
