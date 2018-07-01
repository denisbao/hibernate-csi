package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Filme;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Usuario;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by cpol on 29/05/2017.
 */
@Controller
public class UsuarioController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("cria-usuario.admin")
    public String criaUsuario(Usuario usuario, String senha, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        usuario.setSenha(md.digest(senha.getBytes("ISO-8859-1")));
        hibernateDAO.criaObjeto(usuario);

        //.......................................................................................................... LOG
        Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
        Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());

        try {
            Date dataHora = new Date();
            hibernateDAO.criaLog(u, usuario.getId(),"cadastro", usuario.getClass(),dataHora);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //..............................................................................................................
        return "usuario";
    }

    @Transactional
    @RequestMapping("login.html")
    public String login(String login, String senha, HttpSession session, Model model, String jcaptcha, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        map.put("login", login);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        map.put("senha", md.digest(senha.getBytes("ISO-8859-1")));
        Collection usuarios = hibernateDAO.listaObjetosEquals(Usuario.class, map);
        if (usuarios == null || usuarios.isEmpty()) {
            return "../../index";
        } else {
            //.................................................................................. SESSION FIXATION ATTACK
            session.invalidate();
            HttpSession sessionFixAttack = request.getSession();
            sessionFixAttack.setAttribute("userLoggedIn", usuarios.toArray()[0]);
            sessionFixAttack.setAttribute("userLoggedIn", usuarios.toArray()[0]);
            //..........................................................................................................

            //...................................................................................................... LOG
            Usuario uSession = (Usuario) sessionFixAttack.getAttribute("userLoggedIn");
            Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());

            try {
                Date dataHora = new Date();
                hibernateDAO.criaLog(u, u.getId(),"login", u.getClass(),dataHora);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //..........................................................................................................


            return "forward:getLista-filmes.priv";
        }

    }


    @Transactional
    @RequestMapping("lista-usuarios.admin")
    public String listaUsuarios(Model model, String nome, String login) {
        Map<String, String> m = new HashMap<>();
        if (nome != null && !nome.isEmpty()) {
            m.put("nome", nome);
        }
        if (login != null && !login.isEmpty()) {
            m.put("login", login);
        }
        model.addAttribute("usuarios", hibernateDAO.listaObjetos(Usuario.class, m, null, null, false));
        return "lista-usuarios";
    }


    @Transactional
    @RequestMapping("remove-usuario.admin")
    public String removeUsuario(Long id, HttpSession session){
        Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, id);
        if (u.getOpinioes().isEmpty()){

            Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
            Usuario uLogado = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());

            hibernateDAO.removeObjeto(u);

            //...................................................................................................... LOG

            try {
                Date dataHora = new Date();
                hibernateDAO.criaLog(uLogado, u.getId(),"remocao", u.getClass(),dataHora);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //..........................................................................................................
            return "forward:lista-usuarios.admin";
        }
        else{
            return "remover-usuario-fail";
        }
    }

    @Transactional
    @RequestMapping("edita-usuario.admin")
    public String editaUsuario(Model model, Long id){
        model.addAttribute("usuario", hibernateDAO.carregaObjeto(Usuario.class, id));
        return "editar-usuario";
    }

    @Transactional
    @RequestMapping("updateCadastro-usuario.admin")
    public String updateUsuario(Usuario user, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        user.setSenha(md.digest(senha.getBytes("ISO-8859-1")));
        hibernateDAO.updateObjeto(user);

        //.......................................................................................................... LOG
        try {
            Date dataHora = new Date();
            hibernateDAO.criaLog(user, user.getId(),"edicao", user.getClass(),dataHora);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //..............................................................................................................


        return "editar-usuario-ok";
    }

    @Transactional
    @RequestMapping("user-logoff.priv")
    public void logOff(HttpSession session, ServletResponse servletResponse)  throws IOException, ServletException {

        //.......................................................................................................... LOG
        Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
        Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());

        try {
            Date dataHora = new Date();
            hibernateDAO.criaLog(u, u.getId(),"logoff", u.getClass(),dataHora);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //..............................................................................................................

        session.invalidate();
        ((HttpServletResponse) servletResponse).sendRedirect("/spring-teste");
    }

    @Transactional
    @RequestMapping("lista-usuarios-opiniao.priv")
    public String listaUsuarioOpiniao(Model model, HttpSession session){
        Usuario userSessao = (Usuario) session.getAttribute("userLoggedIn");
        Usuario userBanco = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, userSessao.getId());
        model.addAttribute("usuario", userBanco);

        return "listar-usuario-opinioes";
    }






}
