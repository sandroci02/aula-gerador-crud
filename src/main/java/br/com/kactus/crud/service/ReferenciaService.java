package br.com.kactus.crud.service;


import br.com.kactus.crud.Exception.NotFoundException;
import br.com.kactus.crud.model.Referencia;
import br.com.kactus.crud.model.representation.ReferenciaRepresentation;
import br.com.kactus.crud.util.Parametro;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReferenciaService {

    ReferenciaRepresentation find(Long id) throws NotFoundException;

    ReferenciaRepresentation save(ReferenciaRepresentation referencia);

    ReferenciaRepresentation update(ReferenciaRepresentation referencia) throws NotFoundException;

    void remove(Long id) throws NotFoundException;

    List<ReferenciaRepresentation> listAll();

    Page<Referencia> list(Parametro entrada);
}