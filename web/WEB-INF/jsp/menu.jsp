<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%------------------------------------ MENU --%>
<table style="width:50%">

    <tr>
        <td><a href="cadastrar-filme.priv">Cadastrar Filme</a></td>

        <%--<c:if test="${userLoggedIn.admin == true}">--%>
            <%--<td><a href="cadastrar-usuario.admin">Cadastrar Usuario</a></td>--%>
        <%--</c:if>--%>

        <td><a href="cadastrar-usuario.admin">Cadastrar Usuário</a></td>
        <td><a href="getLista-filmes.priv">Listar Filmes</a></td>
        <td><a href="lista-usuarios.admin">Listar Usuarios</a></td>
        <td><a href="lista-usuarios-opiniao.priv">Minhas Opinioes</a></td>
        <td><a href="logoff.htlm">SAIR</a></td>
    </tr>
</table>
<br><br><br>
<%------------------------------------ END MENU --%>