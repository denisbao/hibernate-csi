<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cadastro de Filmes</title>
    </head>
    <body>

    <c:import url="menu.jsp"></c:import>

        <form action="getCadastro-filme.priv" method="post">

            <label>Título do Filme:</label>
            <input name="nome" type="text" required="required" />
            <br>
            <br>
            <button type="submit">Salvar</button>

        </form>

    </body>
</html>
