
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
  </head>
  <body>
    <h3>Login</h3>
      <form action="login.html" method="post">
        Login:<input type="text" type ="text" name="login" ><br><br>
        Senha:<input type="password" type ="password" name="senha" ><br><br>
        <p>

        <div class="g-recaptcha" data-sitekey="6LdAVmEUAAAAAAwL-AXTQQta-cQTqH-OE0j5ksWg"></div>

        </p>

        <button type="submit">Entrar</button>
      </form>
  </body>
</html>
