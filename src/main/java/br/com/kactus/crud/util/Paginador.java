package br.com.kactus.crud.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class Paginador {

	private Long total;
	private Integer totalPaginas;
	private List<Integer> paginas;
	private Integer elementos;
	private Integer pagina;
	private Integer proximo;
	private Integer anterior;
	private Boolean asc;
	private String order;
	private String resumo;
	private Boolean semRegistro;

	public Paginador(Page<?> lista, ParametroData entrada) {
		this.total = lista.getTotalElements();
		this.totalPaginas = lista.getTotalPages();
		this.pagina = entrada.getParametroInteger("pagina");
		this.anterior = pagina > 1 ? pagina - 1 : pagina;
		this.proximo = pagina < totalPaginas ? pagina + 1 : pagina;
		this.paginas = getPaginasOrganizadas(pagina, totalPaginas);
		this.elementos = lista.getSize();
		this.asc =  entrada.getParametroBoolean("asc");
		this.order = entrada.getParametro("order");

		this.semRegistro = total == 0;
		if (semRegistro) {
			this.resumo = "Não foram encontrados registros para o filtro informado";
		} else {
			int primeiro = lista.getNumber() * lista.getSize() + 1;
			int ultimo = primeiro + lista.getNumberOfElements() - 1;
			this.resumo = String.format("Registros %d-%d de %d, em %d página%s", primeiro, ultimo, total, totalPaginas, totalPaginas == 1 ? "" : "s");
		}
	}

	public List<Integer> getPaginasOrganizadas(int pagina, int total) {
		List<Integer> paginas = new ArrayList<Integer>();
		int count = 0;
		int max = 6;
		int incrementa = 1;

		if (pagina - 3 > 0) {
			paginas.add(pagina - 3);
			count++;
		}
		if (pagina - 2 > 0) {
			paginas.add(pagina - 2);
			count++;
		}
		if (pagina - 1 > 0) {
			paginas.add(pagina - 1);
			count++;
		}

		paginas.add(pagina);

		while (count < max) {
			if (pagina + incrementa > total) {
				break;
			}
			paginas.add(pagina + incrementa);
			count++;
			incrementa++;
		}

		return paginas;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(Integer totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public List<Integer> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Integer> paginas) {
		this.paginas = paginas;
	}

	public Integer getElementos() {
		return elementos;
	}

	public void setElementos(Integer elementos) {
		this.elementos = elementos;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public Boolean getAsc() {
		return asc;
	}

	public void setAsc(Boolean asc) {
		this.asc = asc;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Integer getProximo() {
		return proximo;
	}

	public void setProximo(Integer proximo) {
		this.proximo = proximo;
	}

	public Integer getAnterior() {
		return anterior;
	}

	public void setAnterior(Integer anterior) {
		this.anterior = anterior;
	}

	public Boolean getSemRegistro() {
		return semRegistro;
	}

	public void setSemRegistro(Boolean semRegistro) {
		this.semRegistro = semRegistro;
	}

}
