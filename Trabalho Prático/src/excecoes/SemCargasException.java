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
			s = "Você já realizou um saque esse mês, aguarde o proximo mês para realizar novamente!";
		}else {
			s = "Você já realizou uma transferencia esse mês, aguarde o proximo mês para realizar novamente!";
		}
		return s;
	}
	
}
