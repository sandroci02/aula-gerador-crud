package br.com.kactus.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
public class Referencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "referencia_id_seq")
    @SequenceGenerator(name = "referencia_id_seq", sequenceName = "referencia_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column
    private String descricao;

    @Version
    private Long version;

    public Referencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


}
