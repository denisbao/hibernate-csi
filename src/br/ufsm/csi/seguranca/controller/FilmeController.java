package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Filme;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FilmeController {


    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("getCadastro-filme.priv")
    public String cadastraFilme(Filme filme, HttpSession session, String token){
        //................................................................................... CROSS-SITE REQUEST FORGERY
        if (token != null){

            String sessionToken = (String) session.getAttribute("token");

            if (token.equals(sessionToken)){

                hibernateDAO.criaObjeto(filme);
                //.................................................................................................. LOG
                Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
                Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());
                try {
                    Date dataHora = new Date();
                    hibernateDAO.criaLog(u, filme.getId(),"cadastro", filme.getClass(),dataHora);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return "forward:getLista-filmes.priv";
            }
        }
       return "../../index";
    }

    @Transactional
    @RequestMapping("updateCadastro-filme.priv")
    public String updateFilme(Filme filme, HttpSession session){
        hibernateDAO.updateObjeto(filme);

        //.......................................................................................................... LOG
        Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
        Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());

        try {
            Date dataHora = new Date();
            hibernateDAO.criaLog(u, filme.getId(),"edicao", filme.getClass(),dataHora);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //..............................................................................................................

        return "forward:getLista-filmes.priv";
    }

    @Transactional
    @RequestMapping("getLista-filmes.priv")
    public String listaFilmes(Model model, String nome, String login){
        Map<String, String> m = new HashMap<>();
        model.addAttribute("filmes", hibernateDAO.listaObjetos(Filme.class, m, null, null, false));
        return "listar-filmes";
    }

    @Transactional
    @RequestMapping("remove-filme.priv")
    public String removeFilme(Long id, HttpSession session){
        Filme f = (Filme) hibernateDAO.carregaObjeto(Filme.class, id);
        if (f.getOpinioes().isEmpty()){

            Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
            Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());

            hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Filme.class, id));

            //...................................................................................................... LOG
            try {
                Date dataHora = new Date();
                hibernateDAO.criaLog(u, f.getId(),"remocao", f.getClass(),dataHora);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //..........................................................................................................
            return "forward:getLista-filmes.priv";
        }
        else{
            return "remover-filme-fail";
        }
    }

    @Transactional
    @RequestMapping("edita-filme.priv")
    public String editaFilme(Model model, Long id){
        model.addAttribute("filme", hibernateDAO.carregaObjeto(Filme.class, id));
        return "editar-filme";
    }

    @Transactional
    @RequestMapping("opinioes-filme.priv")
    public String opinioesFilme(Model model, Long id){
        model.addAttribute("filme", hibernateDAO.carregaObjeto(Filme.class, id));
        return "listar-opinioes-filme";
    }

}