package br.com.kactus.crud.mapper;

import br.com.kactus.crud.model.Referencia;
import br.com.kactus.crud.model.representation.ReferenciaRepresentation;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ReferenciaMapper {


    public static Referencia map(ReferenciaRepresentation representation) {
        Referencia referencia = new Referencia();
        referencia.setId(representation.getId());
        referencia.setDescricao(representation.getDescricao());
        return referencia;
    }

    public static ReferenciaRepresentation map(Referencia referencia) {
        ReferenciaRepresentation representation = new ReferenciaRepresentation();
        representation.setId(referencia.getId());
        representation.setDescricao(referencia.getDescricao());
        return representation;
    }

    public static List<ReferenciaRepresentation> map(List<Referencia> list) {
        List<ReferenciaRepresentation> retorno =  new ArrayList<>();
        for (Referencia referencia : list) {
            retorno.add(map(referencia));
        }
        return retorno;
    }
}
