package equilibrium.br.processo.services;
import equilibrium.br.processo.dto.TipoProcessoDTO;
import equilibrium.br.processo.entity.TipoProcessoModel;
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

    public TipoProcessoModel criarTipoProcesso(TipoProcessoDTO dtoTipoProcesso){
        TipoProcessoModel tipoProcesso = new TipoProcessoModel();
        tipoProcesso.setDescricao(dtoTipoProcesso.getDescricao());
        return  tipoProcessoRepository.saveAndFlush(tipoProcesso);
    }

    public List<TipoProcessoModel> listarTiposProcessos(){
        return  tipoProcessoRepository.findAll(Sort.sort(TipoProcessoModel.class).ascending());
    }
}
