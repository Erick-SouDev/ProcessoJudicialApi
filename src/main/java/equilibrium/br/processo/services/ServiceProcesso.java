
package equilibrium.br.processo.services;
import java.util.List;
import java.util.Optional;
import equilibrium.br.processo.dto.DtoProcesso;
import equilibrium.br.processo.entity.TipoProcesso;
import equilibrium.br.processo.util.GerarNumeroProcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import equilibrium.br.processo.entity.Processo;
import equilibrium.br.processo.exptions.ProcessoNaoEncontradoException;
import equilibrium.br.processo.repository.ProcessoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceProcesso {

	@Autowired
	private ProcessoRepository processoRepository;

	public Processo salvarProcesso(DtoProcesso dtoProcesso) throws ProcessoNaoEncontradoException {

		Processo processo = new Processo();

        processo.setNumeroProcesso(GerarNumeroProcesso.gerarMatriculaProcesso());
		processo.setObjetivo(dtoProcesso.getObjetivo());
		processo.setDataEntrada(dtoProcesso.getDataEntrada());
		processo.setValorRecurso(dtoProcesso.getValorRecurso());
		processo.setTipoProcesso(dtoProcesso.getTipoProcesso());
		// Salvar no banco de dados
		return processoRepository.save(processo);

	}
	public Processo editarProcesso(Processo processo) throws ProcessoNaoEncontradoException {

		Processo processoParaAtualizar = new Processo();
		processoParaAtualizar.setDataEntrada(processo.getDataEntrada());
		processoParaAtualizar.setValorRecurso(processo.getValorRecurso());
		processoParaAtualizar.setObjetivo(processo.getObjetivo());
		processoParaAtualizar.setTipoProcesso(processo.getTipoProcesso());
		// atualizar  no banco de dados
		return processoRepository.save(processoParaAtualizar);

	}
	public Processo buscarProcesso(Long idProcesso) {

		return processoRepository.findById(idProcesso)
				.orElseThrow(() -> new IllegalArgumentException("Processo    não encontrado!"));
	}

	public void removerProcesso(Long id) {
		processoRepository.deleteById(id);
	}

	public List<Processo> buscarProcessosPaginados(int pagina, int limit) {
		return processoRepository.findAll(PageRequest.of(pagina, limit)).getContent();
	}

	public Processo pesquisarProcessoPorNumero(String numeroProcesso) throws ProcessoNaoEncontradoException {
		return processoRepository.pesquisarProcesso(numeroProcesso)
				.orElseThrow(() -> new ProcessoNaoEncontradoException(
						"Processo não encontrado."));
	}




	public  List<Processo> carregarProcessosPorTipo(String descricao){
		return  processoRepository.buscarProcessosPorTipoProcesso(descricao);
	}

}
