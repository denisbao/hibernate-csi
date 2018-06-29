<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Filme</title>
</head>
<body>

<c:import url="menu.jsp"></c:import>

<form action="update-opiniao.priv" method="post">

    <label>Título do Filme: ${opiniao.filme.nome}</label><br><br>
    <textarea name="comentario" cols="200" rows="20" placeholder="${opiniao.comentario}"></textarea>" />
    <input name="filmeId" type="hidden" value="${opiniao.filme.id}"/>
    <input name="opiniaoId" type="hidden" value="${opiniao.id}"/>
    <br>
    <br>
    <button type="submit">Salvar</button>

</form>

</body>
</html>