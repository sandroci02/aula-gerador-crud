package br.com.kactus.crud.service;

import br.com.kactus.crud.Exception.NotFoundException;
import br.com.kactus.crud.model.Referencia;
import br.com.kactus.crud.repository.ReferenciaRepository;
import br.com.kactus.crud.util.ParametroData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReferenciaServiceImpl implements ReferenciaService {

    private static final String KEY = "descricao";
    private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar a referência";
    @Autowired
    private ReferenciaRepository repositorio;

    @Override
    public Referencia find(Long id) throws NotFoundException {
        Optional<Referencia> op = repositorio.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        throw new NotFoundException(MSG_ERRO_BUSCA);
    }

    @Override
    public Referencia save(Referencia referencia) {
        return repositorio.save(referencia);
    }

    @Override
    public Referencia update(Referencia referencia) throws NotFoundException {
        Referencia referenciaOld = find(referencia.getId());
        referencia.setVersion(referenciaOld.getVersion());
        return repositorio.save(referencia);
    }

    @Override
    public void remove(Long id) throws NotFoundException {
        repositorio.delete(find(id));
    }

    @Override
    public List<Referencia> listAll() {
        return (List<Referencia>) repositorio.findAll();
    }

    @Override
    public Page<Referencia> list(ParametroData entrada) {
        return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY));
    }
}
