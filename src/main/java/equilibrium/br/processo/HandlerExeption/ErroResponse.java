package equilibrium.br.processo.HandlerExeption;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroResponse {

	private int codigoErro; // Código do erro (ex: "400", "404")

	private String descricao; // Mensagem de erro

	private  String menssagem ;



}
