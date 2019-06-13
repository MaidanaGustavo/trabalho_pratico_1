package excecoes;

public class ValorNegativoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6;

	@Override
	public String toString() {
		return "Não é possivel receber um valor negativo!";
	}

	
}
