package equilibrium.br.processo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_tipo_processo")
public class TipoProcesso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_processo")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	
	@OneToMany(mappedBy = "tipoProcesso" )
	@JsonIgnore
	List<Processo> processos = new ArrayList<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public List<Processo> getProcessos() {
		return processos;
	}


	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoProcesso other = (TipoProcesso) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "TipoProcesso [id=" + id + ", descricao=" + descricao + ", processos=" + processos + "]";
	}
	
	
	

}
