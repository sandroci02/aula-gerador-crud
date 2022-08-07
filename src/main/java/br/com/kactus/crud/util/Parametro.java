package br.com.kactus.crud.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.io.StringReader;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parametro {

	@ApiModelProperty(
			value = "filtros",
			name = "filtros",
			example = "{\"id\":\"1\", \"descricao\":\"descricao\"}")
	private Map<String, String> filtros;
	@ApiModelProperty(
			value = "limite",
			name = "limite",
			dataType = "Integer",
			example = "10")
	private Integer limite= 10;
	@ApiModelProperty(
			value = "pagina",
			name = "pagina",
			dataType = "Integer",
			example = "1")
	private Integer pagina = 1;

	@ApiModelProperty(
			value = "ordenacao",
			name = "ordenacao",
			dataType = "String",
			example = "asc")
	private String sentidoOrdenacao = "asc";
	@ApiModelProperty(
			value = "ordenacaoPor",
			name = "ordenacaoPor",
			dataType = "String",
			example = "id")
	private String ordenacaoPor = "id";



	public Pageable getPageable() {
		Boolean asc =   "ASC".equalsIgnoreCase(this.sentidoOrdenacao);

		Sort by = null;

		if (asc) {
			by = Sort.by(Order.asc(ordenacaoPor));
		} else {
			by = Sort.by(Order.desc(ordenacaoPor));
		}

		return PageRequest.of(pagina - 1, limite, by);
	}

	public String getFiltro(String key) {
		if(!Objects.isNull(filtros)){
			return filtros.get(key);
		}
		return "";
	}
}
