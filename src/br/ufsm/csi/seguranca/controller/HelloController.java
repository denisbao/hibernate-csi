package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.Util.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class HelloController {

    @RequestMapping("hello.html")
    public String hello() {
        return "hello";
    }

    @RequestMapping("cadastrar-filme.priv")
    public String redirectCadastrarFilme(HttpSession session){
        //................................................................................... CROSS-SITE REQUEST FORGERY
        session.setAttribute("token", Token.generateToken());
        return "cadastrar-filme";
    }

    @RequestMapping("listar-filmes.priv")
    public String redirectListarFilme(){
        return "forward:getLista-filmes.html";
    }

    @RequestMapping("cadastrar-usuario.admin")
    public String redirectCadastrarUsuario(){
        return "cadastrar-usuario";
    }

    @RequestMapping("getLogin.html")
    public String redirectLogino(){
        return "/spring-teste";
    }

    @RequestMapping("getAdminErro.priv")
    public String redirectAdminErro() {
        return "adminErro";
    }
}
