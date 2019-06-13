package excecoes;

public class LimitedaContaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private double saldo;
	
	public LimitedaContaException(double saldo) {
		
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Esse valor irá ultrapassar o limite da sua conta!"+"\n"+"Saldo atual: R$"+this.getSaldo();
	}
	
	
}
