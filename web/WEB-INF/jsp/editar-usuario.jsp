<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de usuários</title>
</head>
<body>

<c:import url="menu.jsp"></c:import>

    <h3>Edição de Usuário</h3>

    <form action="updateCadastro-usuario.priv" method="post">

        Nome: <input type="text" name="nome" value="${usuario.nome}"><br><br>
        Login:<input type="text" name= "login" value="${usuario.login}"><br><br>
        Senha:<input type="password" name="senha"><br><br>

        <c:if test="${usuario.admin == true}">
            Administrador: <input type="checkbox" name="admin" checked><br><br>
        </c:if>

        <c:if test="${usuario.admin == false}">
            Administrador: <input type="checkbox" name="admin"><br><br>
        </c:if>


        <input name="id" type="hidden" value="${usuario.id}"  />
        <button type="submit">Cadastrar</button>

    </form>
    </body>
</html>