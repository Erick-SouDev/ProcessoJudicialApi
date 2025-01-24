

package equilibrium.br.processo.services;

import java.util.List;

import equilibrium.br.processo.dto.ProcessoDTO;
import equilibrium.br.processo.entity.ProcessoModel;
import equilibrium.br.processo.util.GerarNumeroProcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import equilibrium.br.processo.exeption.ProcessoNaoEncontradoException;
import equilibrium.br.processo.repository.ProcessoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceProcesso {

    @Autowired
    private ProcessoRepository processoRepository;

    public ProcessoModel salvarProcesso(ProcessoDTO dtoProcesso) {

        ProcessoModel processo = new ProcessoModel();

        processo.setNumeroProcesso(GerarNumeroProcesso.gerarMatriculaProcesso());
        processo.setObjetivo(dtoProcesso.getObjetivo());
        processo.setDataEntrada(dtoProcesso.getDataEntrada());
        processo.setValorRecurso(dtoProcesso.getValorRecurso());
        processo.setTipoProcesso(dtoProcesso.getTipoProcesso());
        return processoRepository.save(processo);

    }

    public ProcessoModel editarProcesso(Long id, ProcessoModel processo) throws ProcessoNaoEncontradoException {

        ProcessoModel processoParaAtulizar = null;

        processoParaAtulizar = processoRepository.findById(id).orElseThrow(() -> new ProcessoNaoEncontradoException("Processo Não Foi Encontrado"));

        processoParaAtulizar.setId(id);
        processoParaAtulizar.setNumeroProcesso(processo.getNumeroProcesso());
        processoParaAtulizar.setTipoProcesso(processo.getTipoProcesso());
        processoParaAtulizar.setObjetivo(processo.getObjetivo());
        processoParaAtulizar.setDataEntrada(processo.getDataEntrada());


        return processoRepository.saveAndFlush(processoParaAtulizar);

    }

    public ProcessoModel buscarProcesso(Long idProcesso) throws ProcessoNaoEncontradoException {

        return processoRepository.findById(idProcesso)
                .orElseThrow(() -> new ProcessoNaoEncontradoException("Processo Não foi Encontrado"));
    }

    public void removerProcesso(Long id) {
        processoRepository.deleteById(id);
    }

    public List<ProcessoModel> buscarProcessosPaginados(int pagina, int limit) {
        return processoRepository.findAll(PageRequest.of(pagina, limit)).getContent();
    }

    public Page<ProcessoModel> pesquisarProcessoPorNumero(String numeroProcesso, int page, int size) throws ProcessoNaoEncontradoException {
        Pageable pageable = PageRequest.of(page, size);
        return processoRepository.pesquisarProcesso(numeroProcesso, pageable);

    }



}
