package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Filme;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Opiniao;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Controller
public class OpiniaoController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("setOpiniao.priv")
    public String setOpiniao(Opiniao op, Long filmeId, HttpSession session){

        Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
        Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());
        Filme f = (Filme) hibernateDAO.carregaObjeto(Filme.class, filmeId);

        //.......................................................................................... HIDDEN MANIPULATION
        Long selectedFilmeID = (Long) session.getAttribute("selectedFilmeID");
        if (selectedFilmeID.equals(filmeId)){
            op.setUsuario(u);
            op.setFilme(f);

            hibernateDAO.criaObjeto(op);

            f.getOpinioes().add(op);
            u.getOpinioes().add(op);
            //...................................................................................................... LOG
            try {
                Date dataHora = new Date();
                hibernateDAO.criaLog(u, op.getId(),"cadastro", op.getClass(),dataHora);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //..........................................................................................................

            return "forward:getLista-filmes.priv";
        }
        else{
            //...................................................................................................... LOG
            try {
                Date dataHora = new Date();
                hibernateDAO.criaLog(u, op.getId(),"hiddenManipulation", op.getClass(),dataHora);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //..........................................................................................................
            return "hiddenManipulationErro";
        }
    }

    @Transactional
    @RequestMapping(value = "cadastra-opiniao.priv", method = RequestMethod.GET)
    public String cadastraOpiniao(Model model, Long id, HttpSession session){

        session.setAttribute("selectedFilmeID", id);
        model.addAttribute("filme", hibernateDAO.carregaObjeto(Filme.class, id));
        return "cadastrar-opiniao";
    }

    @Transactional
    @RequestMapping("editar-opiniao.priv")
    public String editarOpiniao(Model model, Long id, HttpSession session){

        model.addAttribute("opiniao", hibernateDAO.carregaObjeto(Opiniao.class, id));
        session.setAttribute("selectedOpID", id);
        return "editar-opiniao";
    }

    @Transactional
    @RequestMapping("update-opiniao.priv")
    public String updateOpiniao(Long opiniaoId, String comentario, HttpSession session){

        Opiniao op = (Opiniao) hibernateDAO.carregaObjeto(Opiniao.class, opiniaoId);
        Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
        Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());

        //.......................................................................................... HIDDEN MANIPULATION
        Long selectedOpID = (Long) session.getAttribute("selectedOpID");
        if (selectedOpID.equals(opiniaoId) && u.getId().equals(op.getUsuario().getId())){

            op.setComentario(comentario);
            hibernateDAO.updateObjeto(op);

            //...................................................................................................... LOG
            try {
                Date dataHora = new Date();
                hibernateDAO.criaLog(u, op.getId(),"edicao", op.getClass(),dataHora);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //..........................................................................................................

        }
        else{
            //...................................................................................................... LOG
            try {
                Date dataHora = new Date();
                hibernateDAO.criaLog(u, op.getId(),"hiddenManipulation", op.getClass(),dataHora);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //..........................................................................................................
            return "hiddenManipulationErro";

        }
        return "forward:lista-usuarios-opiniao.priv";
    }

    @Transactional
    @RequestMapping("remove-opiniao.priv")
    public String removeOpiniao(Long id, HttpSession session){

        Opiniao op = (Opiniao) hibernateDAO.carregaObjeto(Opiniao.class, id);
        Usuario uSession = (Usuario) session.getAttribute("userLoggedIn");
        Usuario u = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, uSession.getId());

        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Opiniao.class, id));

        //.......................................................................................................... LOG
        try {
            Date dataHora = new Date();
            hibernateDAO.criaLog(u, op.getId(),"remocao", op.getClass(),dataHora);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //..............................................................................................................

        return "forward:lista-usuarios-opiniao.priv";
    }

}