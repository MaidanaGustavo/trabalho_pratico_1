package excecoes;

public class EmprestimoJaRealizadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7;

	@Override
	public String toString() {
		return "Voc� j� realizou um emprestimo!";
	}
	
}
