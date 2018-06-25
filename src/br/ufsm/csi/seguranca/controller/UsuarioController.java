package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Filme;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
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
    public String criaUsuario(Usuario usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        usuario.setSenha(md.digest(senha.getBytes("ISO-8859-1")));
        hibernateDAO.criaObjeto(usuario);
        return "usuario";
    }

    @Transactional
    @RequestMapping("login.html")
    public String login(String login, String senha, HttpSession session, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        map.put("login", login);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        map.put("senha", md.digest(senha.getBytes("ISO-8859-1")));
        Collection usuarios = hibernateDAO.listaObjetosEquals(Usuario.class, map);
        if (usuarios == null || usuarios.isEmpty()) {
            return "acesso-negado";
        } else {
            session.setAttribute("userLoggedIn", usuarios.toArray()[0]);
            return "forward:getLista-filmes.priv";
        }

    }

    @Transactional
    @RequestMapping("cria-log.priv")
    public String criaLog(Long idUsuario,
                          Long idObjeto,
                          String classe,
                          @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date dataHora) throws ClassNotFoundException {
        Usuario usuario = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, idUsuario);
        Log log = new Log();
        log.setClasse(Class.forName(classe));
        log.setIdObjeto(idObjeto);
        log.setDataHora(dataHora);
        log.setUsuario(usuario);
        hibernateDAO.criaObjeto(log);
        return "log";
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
    public String removeUsuario(Long id){
        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Usuario.class, id));
        return "forward:lista-usuarios.admin";
    }

    @Transactional
    @RequestMapping("edita-usuario.admin")
    public String editaFilme(Model model, Long id){
        model.addAttribute("usuario", hibernateDAO.carregaObjeto(Usuario.class, id));
        return "editar-usuario";
    }

    @Transactional
    @RequestMapping("updateCadastro-usuario.admin")
    public String updateUsuario(Usuario user){
        hibernateDAO.updateObjeto(user);
        return "forward:lista-usuarios.admin";
    }

    @Transactional
    @RequestMapping("logoff.htlm")
    public String logOff(HttpSession session){
        session.invalidate();
        return "/spring-teste";
    }

    @Transactional
    @RequestMapping("lista-usuarios-opiniao.priv")
    public String listaUsuarioOpiniao(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("userLoggedIn"));
        return "listar-usuario-opinioes";
    }



}
