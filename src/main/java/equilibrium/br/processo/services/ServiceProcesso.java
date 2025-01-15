
package equilibrium.br.processo.services;
import java.util.List;

import equilibrium.br.processo.dto.DtoProcesso;
import equilibrium.br.processo.entity.ProcessoModel;
import equilibrium.br.processo.util.GerarNumeroProcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import equilibrium.br.processo.exptions.ProcessoNaoEncontradoException;
import equilibrium.br.processo.repository.ProcessoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceProcesso {

	@Autowired
	private ProcessoRepository processoRepository;

	public ProcessoModel salvarProcesso(DtoProcesso dtoProcesso) throws ProcessoNaoEncontradoException {

		ProcessoModel processo = new ProcessoModel();

        processo.setNumeroProcesso(GerarNumeroProcesso.gerarMatriculaProcesso());
		processo.setObjetivo(dtoProcesso.getObjetivo());
		processo.setDataEntrada(dtoProcesso.getDataEntrada());
		processo.setValorRecurso(dtoProcesso.getValorRecurso());
		processo.setTipoProcesso(dtoProcesso.getTipoProcesso());
		// Salvar no banco de dados
		return processoRepository.save(processo);

	}
	public ProcessoModel editarProcesso(ProcessoModel processo) throws ProcessoNaoEncontradoException {

		ProcessoModel processoParaAtualizar = new ProcessoModel();
		processoParaAtualizar.setDataEntrada(processo.getDataEntrada());
		processoParaAtualizar.setValorRecurso(processo.getValorRecurso());
		processoParaAtualizar.setObjetivo(processo.getObjetivo());
		processoParaAtualizar.setTipoProcesso(processo.getTipoProcesso());
		// atualizar  no banco de dados
		return processoRepository.save(processoParaAtualizar);

	}
	public ProcessoModel buscarProcesso(Long idProcesso) {

		return processoRepository.findById(idProcesso)
				.orElseThrow(() -> new IllegalArgumentException("ProcessoModel    não encontrado!"));
	}

	public void removerProcesso(Long id) {
		processoRepository.deleteById(id);
	}

	public List<ProcessoModel> buscarProcessosPaginados(int pagina, int limit) {
		return processoRepository.findAll(PageRequest.of(pagina, limit)).getContent();
	}

	public ProcessoModel pesquisarProcessoPorNumero(String numeroProcesso) throws ProcessoNaoEncontradoException {
		return processoRepository.pesquisarProcesso(numeroProcesso)
				.orElseThrow(() -> new ProcessoNaoEncontradoException(
						"ProcessoModel não encontrado."));
	}




	public  List<ProcessoModel> carregarProcessosPorTipo(String descricao){
		return  processoRepository.buscarProcessosPorTipoProcesso(descricao);
	}

}
