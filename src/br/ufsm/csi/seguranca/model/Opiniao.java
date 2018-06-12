package br.ufsm.csi.seguranca.model;

import javax.persistence.*;

@Entity
@Table(name = "OPINIAO")
public class Opiniao {

    private Long id;
    private String comentario;
    private Usuario usuario;
    private Filme filme;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_opiniao")
    @SequenceGenerator(name = "seq_opiniao", sequenceName = "seq_opiniao")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "COMENTARIO", length = 100)
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "ID_FILME")
    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

//    private Usuario teste;
//
//    @ManyToOne(optional = false)
//    public Usuario getTeste() {
//        return teste;
//    }
//
//    public void setTeste(Usuario teste) {
//        this.teste = teste;
//    }
}
