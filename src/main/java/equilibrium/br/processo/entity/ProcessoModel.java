package equilibrium.br.processo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_processo")
public class ProcessoModel implements Serializable {



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
    
    
    @ManyToOne(cascade = CascadeType.REFRESH , optional = false )
    @JoinColumn(name ="tipo_processo_id"  )
	private TipoProcessoModel tipoProcesso;



	
	
	
	


}
