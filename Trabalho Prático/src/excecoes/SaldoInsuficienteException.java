package excecoes;

public class SaldoInsuficienteException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3;
	
	private double saldo;
	private double valor;
	public SaldoInsuficienteException(double saldo, double valor) {
		this.saldo = saldo;
		this.valor = valor;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Você não tem saldo suficiente !"+"\n"+"Saldo atual: R$"+this.saldo;
	}
	
	
}
