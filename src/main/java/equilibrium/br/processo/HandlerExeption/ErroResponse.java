package equilibrium.br.processo.HandlerExeption;

import org.springframework.http.HttpStatus;

public class ErroResponse {

	private int codigoErro; // Código do erro (ex: "400", "404")
	private  String detalhe ; // detalhes do erro
	private String mensagem; // Mensagem de erro
	// Construtores
	public ErroResponse(int codigoErro, String mensagem  , String detalhe) {
		this.codigoErro = codigoErro;
		this.mensagem = mensagem;
		this.detalhe = detalhe;
	}

	// Getters e Setters
	public int getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(int codigoErro) {
		this.codigoErro = codigoErro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
}
