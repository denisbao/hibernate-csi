<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Minhas Opinioes</title>
</head>
<body>
    <c:import url="menu.jsp"></c:import>

        <c:forEach items="${usuario.opinioes}" var="u">

            <p>Sua opiniao sobre o filme: ${u.filme.nome} - <a href="editar-opiniao.priv?id=${u.id}">Editar</a> - <a href="remove-opiniao.priv?id=${u.id}">Apagar</a> </p>
            <p style="font-size: 16px"><b>${u.comentario}</b></p>
            <hr>

        </c:forEach>


    </body>
</html>