package equilibrium.br.processo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_processo")
public class Processo implements Serializable {
	
	
	private static final long serialVersionUID = 4622265375274049773L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_processo")
	private Long id;
	@Column(name = "numero_processo" , unique = true)
    private String numeroProcesso;
	
	@Column(name = "data_entrada")
    private LocalDate dataEntrada;
	
	@Column(name = "valor_recurso")
    private BigDecimal valorRecurso;
	
	@Column(name = "objetivo")
    private String objetivo;
    
    
    @ManyToOne(cascade = CascadeType.REFRESH )
    @JoinColumn(name ="tipo_processo_id"  )
	private TipoProcesso tipoProcesso;


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNumeroProcesso() {
		return numeroProcesso;
	}



	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}



	public LocalDate getDataEntrada() {
		return dataEntrada;
	}



	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}



	public BigDecimal getValorRecurso() {
		return valorRecurso;
	}



	public void setValorRecurso(BigDecimal valorRecurso) {
		this.valorRecurso = valorRecurso;
	}



	public String getObjetivo() {
		return objetivo;
	}



	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}



	public TipoProcesso getTipoProcesso() {
		return tipoProcesso;
	}



	public void setTipoProcesso(TipoProcesso tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id, numeroProcesso);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		return Objects.equals(id, other.id) && Objects.equals(numeroProcesso, other.numeroProcesso);
	}
	
	
	
	


}
