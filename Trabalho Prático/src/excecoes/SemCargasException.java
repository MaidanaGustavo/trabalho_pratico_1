package excecoes;

public class SemCargasException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4;
	private int saqueOutransferencia;
	public SemCargasException(int saqueOutransferencia) {
	
		this.saqueOutransferencia = saqueOutransferencia;
	}
	public int getSaqueOutransferencia() {
		return saqueOutransferencia;
	}
	public void setSaqueOutransferencia(int saqueOutransferencia) {
		this.saqueOutransferencia = saqueOutransferencia;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		String s;
		if(this.saqueOutransferencia==0) {
			s = "Voc� j� realizou um saque esse m�s, aguarde o proximo m�s para realizar novamente!";
		}else {
			s = "Voc� j� realizou uma transferencia esse m�s, aguarde o proximo m�s para realizar novamente!";
		}
		return s;
	}
	
}
