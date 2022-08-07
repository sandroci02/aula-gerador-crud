package br.com.kactus.crud.service;

import br.com.kactus.crud.Exception.NotFoundException;
import br.com.kactus.crud.mapper.ReferenciaMapper;
import br.com.kactus.crud.model.Referencia;
import br.com.kactus.crud.model.representation.ReferenciaRepresentation;
import br.com.kactus.crud.repository.ReferenciaRepository;
import br.com.kactus.crud.util.ParametroData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReferenciaServiceImpl implements ReferenciaService {

    private static final String KEY = "descricao";
    private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar a referência";
    @Autowired
    private ReferenciaRepository repositorio;

    @Override
    public ReferenciaRepresentation find(Long id) throws NotFoundException {
        return ReferenciaMapper.map(getReferencia(id));
    }

    private Referencia getReferencia(Long id) throws NotFoundException {
        return repositorio.findById(id).orElseThrow(() -> new NotFoundException(MSG_ERRO_BUSCA));
    }

    @Override
    public ReferenciaRepresentation save(ReferenciaRepresentation referencia) {
        return ReferenciaMapper.map(repositorio.save(ReferenciaMapper.map(referencia)));
    }

    @Override
    public ReferenciaRepresentation update(ReferenciaRepresentation referenciaRepresentation) throws NotFoundException {
        Referencia referenciaOld = getReferencia(referenciaRepresentation.getId());

        Referencia referencia = ReferenciaMapper.map(referenciaRepresentation);
        referencia.setVersion(referenciaOld.getVersion());

        return ReferenciaMapper.map(repositorio.save(referencia));
    }

    @Override
    public void remove(Long id) throws NotFoundException {
        repositorio.delete(getReferencia(id));
    }

    @Override
    public List<ReferenciaRepresentation> listAll() {
        return ReferenciaMapper.map((List<Referencia>) repositorio.findAll());
    }

    @Override
    public Page<Referencia> list(ParametroData entrada) {
        return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY));
    }
}
