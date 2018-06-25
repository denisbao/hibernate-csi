<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Filme</title>
</head>
<body>

<c:import url="menu.jsp"></c:import>

<form action="setOpiniao.priv" method="post">

    <label>Título do Filme: ${filme.nome}</label><br><br>
    <textarea name="comentario" cols="200" rows="20"></textarea>" />
    <input name="filmeId" type="hidden" value="${filme.id}"/>
    <br>
    <br>
    <button type="submit">Salvar</button>

</form>

</body>
</html>