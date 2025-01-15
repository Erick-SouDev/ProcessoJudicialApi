package equilibrium.br.processo.services;

import equilibrium.br.processo.dto.DtoTipoProcesso;
import equilibrium.br.processo.entity.TipoProcesso;
import equilibrium.br.processo.repository.TipoProcessoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServiceTipoProcesso {

    @Autowired
    private TipoProcessoRepository tipoProcessoRepository;


    public TipoProcesso criarTipoProcesso(DtoTipoProcesso dtoTipoProcesso){
        TipoProcesso tipoProcesso = new TipoProcesso();
        tipoProcesso.setDescricao(dtoTipoProcesso.getDescricao());
        return  tipoProcessoRepository.saveAndFlush(tipoProcesso);
    }

    public List<TipoProcesso> listarTiposProcessos(){
        return  tipoProcessoRepository.findAll(Sort.sort(TipoProcesso.class).ascending());
    }
}
