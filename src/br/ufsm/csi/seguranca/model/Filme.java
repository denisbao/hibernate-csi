package br.ufsm.csi.seguranca.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "FILME")
public class Filme {

    private Long id;
    private String nome;
    private Collection<Opiniao> opinioes;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_filme")
    @SequenceGenerator(name = "seq_filme", sequenceName = "seq_filme")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME", length = 100)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany(mappedBy = "filme")
    public Collection<Opiniao> getOpinioes() {
        return opinioes;
    }

    public void setOpinioes(Collection<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }
}
