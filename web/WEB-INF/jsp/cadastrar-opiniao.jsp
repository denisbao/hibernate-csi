<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Filme</title>
</head>
<body>

<c:import url="menu.jsp"></c:import>

<form action="setOpiniao.priv" method="post">

    <label>Título do Filme: <c:out value="${filme.nome}"/></label><br><br>
    <%--<textarea name="comentario" cols="200" rows="20"></textarea>--%>
    <textarea name="comentario" cols="200" rows="20" value="${fn:escapeXml(param.comentario)}"></textarea>
    <input name="filmeId" type="hidden" value="${filme.id}"/>
    <br>
    <br>
    <button type="submit">Salvar</button>

</form>

</body>
</html>