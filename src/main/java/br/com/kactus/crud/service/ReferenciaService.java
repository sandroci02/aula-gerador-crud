package br.com.kactus.crud.service;


import br.com.kactus.crud.Exception.NotFoundException;
import br.com.kactus.crud.model.Referencia;
import br.com.kactus.crud.util.ParametroData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReferenciaService {

    Referencia find(Long id) throws NotFoundException;

    Referencia save(Referencia referencia);

    Referencia update(Referencia referencia) throws NotFoundException;

    void remove(Long id) throws NotFoundException;

    List<Referencia> listAll();

    Page<Referencia> list(ParametroData entrada);
}