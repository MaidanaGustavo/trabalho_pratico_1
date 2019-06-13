package excecoes;

public class OperacaoNaoPossivelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2;

	@Override
	public String toString() {
		return "Não é possivel realizar essa operação  nesse tipo de conta.";
	}
	
}
