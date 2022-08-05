package br.com.kactus.crud.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametroData {

	private Map<String, Object> filtros;
	private Map<String, Object> parametros;
	private Long id;

	private static final String LIMITE = "limite";
	private static final String PAGINA = "pagina";
	private static final String ASC = "asc";
	private static final String ORDER = "order";

	public String getFiltro(String key) {
		if (filtros != null && filtros.get(key) != null) {
			return String.valueOf(filtros.get(key));
		}
		return "";
	}

	public Long getFiltroLong(String key) {
		if (filtros != null && filtros.get(key) != null && !filtros.get(key).toString().isEmpty()) {
			return Long.valueOf(filtros.get(key).toString());
		}
		return 0L;
	}

	public Long getParametroLong(String key) {
		if (parametros != null && parametros.get(key) != null && !((String) parametros.get(key)).isEmpty()) {
			return Long.valueOf((String) parametros.get(key));
		}
		return 0l;
	}
	
	public Integer getParametroInteger(String key) {
		if (parametros != null && parametros.get(key) != null && !((String) parametros.get(key)).isEmpty()) {
			return Integer.valueOf((String) parametros.get(key));
		}
		return 0;
	}

	public String getParametro(String key) {
		if (parametros != null) {
			return  parametros.get(key) != null ? String.valueOf(parametros.get(key)) : "";
		}
		return "";
	}

	public Map<String, Object> getParametros() {
		if(parametros == null) {
			parametros = new HashMap<>();
		}
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

	public Map<String, Object> getFiltros() {
		if(filtros == null) {
			filtros = new HashMap<>();
		}
		return filtros;
	}

	public void setFiltros(Map<String, Object> filtros) {
		this.filtros = filtros;
	}

	public Pageable getPageable() {
		Boolean asc =    getParametroBoolean(ASC);
		String order =   getParametro(ORDER);
		Integer pagina = getParametroInteger(PAGINA);
		Integer limite = getParametroInteger(LIMITE);

		Sort by = null;

		if (asc) {
			by = Sort.by(Order.asc(order));
		} else {
			by = Sort.by(Order.desc(order));
		}

		return PageRequest.of(pagina - 1, limite, by);
	}



	public <T> T getParametro(String key, Class<T> classe) {
		Object parametro = getParametro(key);
		return transformaObjetoParaClasse(parametro, classe);
	}

	public <T> List<T> getParametroLista(String key, Class<T> classe) {
		Object parametroLista = getParametro(key);
		List<T> listaRetorno = new ArrayList<T>();

		if (parametroLista != null && parametroLista instanceof ArrayList) {
			@SuppressWarnings("unchecked")
			List<Object> lista = (ArrayList<Object>) parametroLista;

			lista.forEach(item -> {
				listaRetorno.add(transformaObjetoParaClasse(item, classe));
			});
		}

		return listaRetorno;
	}

	public <T> T getFiltros(Class<T> classe) {
		return transformaObjetoParaClasse(filtros, classe);
	}

	
	private <T> T transformaObjetoParaClasse(Object objeto, Class<T> classe) {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new StringReader((String)objeto));
		reader.setLenient(true);
		T fromJson = gson.fromJson(reader, classe);
		return fromJson;
	}
	
	public Boolean getParametroBoolean(String string) {
		return getParametro(string, Boolean.class);
	}

	public boolean temParametro(String key) {
		return parametros != null && parametros.get(key) != null;
	}

	public boolean temFiltro(String key) {
		return filtros != null && filtros.get(key) != null;
	}

	public void addFiltro(String chave, String valor) {
		getFiltros().put(chave, valor);
	}

	public void addParametro(String chave, String valor) {
		getParametros().put(chave, valor);
	}

	public String getParametroRemove(String string) {
		if(getParametros().containsKey(string)) {
			return  String.valueOf(getParametros().remove(string));
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
