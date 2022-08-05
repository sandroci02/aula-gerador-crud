package br.com.kactus.crud.util;

import lombok.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
public class PageData {
    private String mensagem;
    private List lista;
    private Paginador paginador;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public Paginador getPaginador() {
        return paginador;
    }

    public void setPaginador(Paginador paginador) {
        this.paginador = paginador;
    }
}
