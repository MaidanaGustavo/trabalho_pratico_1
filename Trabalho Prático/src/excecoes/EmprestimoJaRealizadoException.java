package excecoes;

public class EmprestimoJaRealizadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7;

	@Override
	public String toString() {
		return "Você já realizou um emprestimo!";
	}
	
}
