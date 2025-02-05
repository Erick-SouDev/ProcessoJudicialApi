package equilibrium.br.processo.repository;


import java.util.List;
import java.util.Optional;

import equilibrium.br.processo.entity.ProcessoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, Long> {

	Page<ProcessoModel> findAll(Pageable pageable);

	@Query("SELECT p FROM ProcessoModel p WHERE p.numeroProcesso LIKE :numeroProcesso%")
	Page<ProcessoModel> pesquisarProcesso(@Param("numeroProcesso") String numeroProcesso , Pageable pageable);





}
