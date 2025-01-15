
package equilibrium.br.processo.services;
import java.util.List;

import equilibrium.br.processo.dto.DtoProcesso;
import equilibrium.br.processo.entity.ProcessoModel;
import equilibrium.br.processo.util.GerarNumeroProcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import equilibrium.br.processo.exeption.ProcessoNaoEncontradoException;
import equilibrium.br.processo.repository.ProcessoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceProcesso {

	@Autowired
	private ProcessoRepository processoRepository;

	public ProcessoModel salvarProcesso(DtoProcesso dtoProcesso)   {

		ProcessoModel processo = new ProcessoModel();

        processo.setNumeroProcesso(GerarNumeroProcesso.gerarMatriculaProcesso());
		processo.setObjetivo(dtoProcesso.getObjetivo());
		processo.setDataEntrada(dtoProcesso.getDataEntrada());
		processo.setValorRecurso(dtoProcesso.getValorRecurso());
		processo.setTipoProcesso(dtoProcesso.getTipoProcesso());
		return processoRepository.save(processo);

	}
	public ProcessoModel editarProcesso(ProcessoModel processo)   {

		ProcessoModel processoParaAtualizar = new ProcessoModel();
		if(processo.getNumeroProcesso() == null) {
			processo.setNumeroProcesso(GerarNumeroProcesso.gerarMatriculaProcesso());
            processoParaAtualizar.setNumeroProcesso(processo.getNumeroProcesso());
		}
		processoParaAtualizar.setNumeroProcesso(processo.getNumeroProcesso());
		processoParaAtualizar.setDataEntrada(processo.getDataEntrada());
		processoParaAtualizar.setValorRecurso(processo.getValorRecurso());
		processoParaAtualizar.setObjetivo(processo.getObjetivo());
		processoParaAtualizar.setTipoProcesso(processo.getTipoProcesso());
		processoParaAtualizar.setNumeroProcesso(processo.getNumeroProcesso());
		processoParaAtualizar.setId(processo.getId());

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
