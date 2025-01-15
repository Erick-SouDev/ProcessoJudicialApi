package equilibrium.br.processo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import equilibrium.br.processo.entity.TipoProcesso;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProcessoRepository   extends JpaRepository<TipoProcesso, Long>{

}
