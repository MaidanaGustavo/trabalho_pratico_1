package excecoes;

public class OperacaoNaoPossivelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2;

	@Override
	public String toString() {
		return "N�o � possivel realizar essa opera��o  nesse tipo de conta.";
	}
	
}
