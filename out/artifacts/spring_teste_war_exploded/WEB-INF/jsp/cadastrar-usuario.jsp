<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de usuários</title>
</head>
<body>

<c:import url="menu.jsp"></c:import>

    <h3>Cadastro de Usuário</h3>
    <form action="cria-usuario.admin" method="post">

        Nome: <input type="text" name="nome" ><br><br>
        Login:<input type="text" name="login" ><br><br>
        Senha:<input type="password" name="senha" ><br><br>
        Administrador: <input type="checkbox" name="admin"><br><br>
        <button type="submit">Cadastrar</button>

    </form>
</body>
</html>