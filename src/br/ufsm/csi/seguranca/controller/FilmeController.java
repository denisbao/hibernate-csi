package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Filme;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class FilmeController {


    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("getCadastro-filme.priv")
    public String cadastraFilme(Filme filme){
        hibernateDAO.criaObjeto(filme);
        return "forward:getLista-filmes.priv";
    }

    @Transactional
    @RequestMapping("updateCadastro-filme.priv")
    public String updateFilme(Filme filme){
        hibernateDAO.updateObjeto(filme);
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
    public String removeFilme(Long id){
        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Filme.class, id));
        return "forward:getLista-filmes.priv";
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
