<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cadastro de Filmes</title>
    </head>
    <body>

    <c:import url="menu.jsp"></c:import>

        <form action="getCadastro-filme.priv" method="post">

            <label>Título do Filme:</label>
            <input name="nome" type="text" required="required" value="${fn:escapeXml(param.nome)}"/>
            <input type="hidden" name="token" value="${token}">
            <br>
            <br>
            <button type="submit">Salvar</button>

        </form>

    </body>
</html>
