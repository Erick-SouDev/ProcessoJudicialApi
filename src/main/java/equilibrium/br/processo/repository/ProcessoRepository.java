package equilibrium.br.processo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import equilibrium.br.processo.entity.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

	Page<Processo> findAll(Pageable pageable);

	@Query("SELECT p FROM Processo p WHERE p.numeroProcesso = ?1")
	Optional<Processo> pesquisarProcesso(@Param("numeroProcesso") String numeroProcesso);

	@Query("SELECT p FROM Processo p INNER JOIN p.tipoProcesso t WHERE t.descricao = :descricao")
	List<Processo> buscarProcessosPorTipoProcesso(@Param("descricao") String descricao);

}
