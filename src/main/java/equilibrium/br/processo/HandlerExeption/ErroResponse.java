package equilibrium.br.processo.HandlerExeption;

public class ErroResponse {

	private String codigoErro; // Código do erro (ex: "400", "404")
	private String mensagem; // Mensagem de erro
	private String detalhes; // Detalhes adicionais sobre o erro (opcional)

	// Construtores
	public ErroResponse(String codigoErro, String mensagem, String detalhes) {
		this.codigoErro = codigoErro;
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}

	// Getters e Setters
	public String getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
}
