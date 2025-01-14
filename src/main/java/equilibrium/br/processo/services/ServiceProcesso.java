package equilibrium.br.processo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import equilibrium.br.processo.entity.Processo;
import equilibrium.br.processo.exptions.ProcessoNaoEncontradoException;
import equilibrium.br.processo.repository.ProcessoRepository;
import equilibrium.br.processo.util.GerarNumeroProcesso;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceProcesso {

	@Autowired
	private ProcessoRepository processoRepository;

	public Processo salvarProcesso(Processo processo) throws ProcessoNaoEncontradoException {

		if (processo.getId() == null) {
			// Se o processo é novo, gera um número de processo único
			processo.setNumeroProcesso(GerarNumeroProcesso.gerarMatriculaProcesso());
			return processoRepository.save(processo); // Cria um novo processo
		} else {
			// Se o processo já existe, verifica se ele está presente no banco
			Optional<Processo> processoExistente = processoRepository.findById(processo.getId());

			if (!processoExistente.isPresent()) {
				throw new ProcessoNaoEncontradoException("Processo com ID " + processo.getId() + " não encontrado.");
			}

			Processo processoParaAtualizar = processoExistente.get();
			processoParaAtualizar.setDataEntrada(processo.getDataEntrada());
			processoParaAtualizar.setValorRecurso(processo.getValorRecurso());
			processoParaAtualizar.setObjetivo(processo.getObjetivo());
			processoParaAtualizar.setNumeroProcesso(processo.getNumeroProcesso());
			processoParaAtualizar.setTipoProcesso(processo.getTipoProcesso());

			return processoRepository.save(processoParaAtualizar); // Atualiza o processo
		}
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
        return processoRepository.findByNumeroProcesso(numeroProcesso)
                .orElseThrow(() -> new ProcessoNaoEncontradoException(
                        "Processo não encontrado."));
    }

}
