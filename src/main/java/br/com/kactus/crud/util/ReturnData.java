package br.com.kactus.crud.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReturnData {
    private Object entidade;
    private String mensagem;
}
