package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Filme;
import br.ufsm.csi.seguranca.model.Opiniao;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class OpiniaoController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("setOpiniao.priv")
    public String setOpiniao(Opiniao op, Long filmeId, HttpSession session){

        Usuario u = (Usuario) session.getAttribute("userLoggedIn");
        Filme f = (Filme) hibernateDAO.carregaObjeto(Filme.class, filmeId);
        hibernateDAO.criaObjeto(op);

        op.setUsuario(u);
        op.setFilme(f);
        Collection opinioesF = f.getOpinioes();
        Collection opinioesU = u.getOpinioes();

        opinioesF.add(op);
        opinioesU.add(op);

        f.setOpinioes(opinioesF);
        u.setOpinioes(opinioesF);

        return "forward:getLista-filmes.priv";

    }

    @Transactional
    @RequestMapping(value = "cadastra-opiniao.priv", method = RequestMethod.GET)
    public String cadastraOpiniao(Model model, Long id){
        model.addAttribute("filme", hibernateDAO.carregaObjeto(Filme.class, id));
        return "cadastrar-opiniao";
    }
}
