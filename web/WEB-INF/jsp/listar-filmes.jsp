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
    <title>Filmes</title>
</head>
    <body>

    <c:import url="menu.jsp"></c:import>

        <table style="width:100%">
            <tr>
                <th>Título do Filme</th>
                <th>Opções</th>
            </tr>

            <c:forEach items="${filmes}" var="f">
                <tr>
                    <td> ${f.nome} </td>
                    <td><a href="edita-filme.priv?id=${f.id}">Editar</a></td>
                    <td><a href="remove-filme.priv?id=${f.id}">Apagar</a></td>
                    <td><a href="opinioes-filme.priv?id=${f.id}">Ver Opinioes</a></td>
                    <td><a href="cadastra-opiniao.priv?id=${f.id}">DAR OPINIAO</a></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
