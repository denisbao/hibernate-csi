<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Minhas Opinioes</title>
</head>
<body>
<c:import url="menu.jsp"></c:import>

<table style="width:100%">
    <tr>
        <c:forEach items="${usuario.opinioes}" var="u">
        <td>
            <p style="font-size: 11px">Sua opiniao sobre o filme: ${u.filme.nome}</p>
                ${u.comentario}
            <hr>
        </td>
    </tr>
    </c:forEach>

</table>
</body>
</html>