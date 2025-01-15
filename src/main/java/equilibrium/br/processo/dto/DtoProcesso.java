package equilibrium.br.processo.dto;

import equilibrium.br.processo.entity.TipoProcessoModel;
import java.math.BigDecimal;
import java.time.LocalDate;
public class DtoProcesso {


    private LocalDate dataEntrada;

    private BigDecimal valorRecurso;

    private String objetivo;

    private TipoProcessoModel tipoProcesso;

    public TipoProcessoModel getTipoProcesso() {
        return tipoProcesso;
    }

    public void setTipoProcesso(TipoProcessoModel tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
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
}
