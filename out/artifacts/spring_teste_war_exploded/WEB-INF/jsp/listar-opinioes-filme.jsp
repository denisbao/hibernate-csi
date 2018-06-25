<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Opinioes do Filme</title>
</head>
<body>
    <c:import url="menu.jsp"></c:import>

    <table style="width:100%">
            <tr>
                <c:forEach items="${filme.opinioes}" var="f">
                <td>
                    <p style="font-size: 11px">Opinião dada por: ${f.usuario.nome}</p>
                    ${f.comentario}
                    <hr>
                </td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
