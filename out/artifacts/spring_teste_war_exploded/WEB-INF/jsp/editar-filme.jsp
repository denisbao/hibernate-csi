<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Filme</title>
</head>
<body>

<c:import url="menu.jsp"></c:import>

<form action="updateCadastro-filme.priv" method="post">

    <label>Título do Filme:</label>
    <input name="nome" type="text" value="${filme.nome}" required="required" />
    <input name="id" type="hidden" value="${filme.id}"  />
    <br>
    <br>
    <button type="submit">Salvar</button>

</form>

</body>
</html>