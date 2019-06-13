package Trabalho;

public abstract class Conta<T extends Conta<?>> implements Operacoes {
	private Cliente cliente;
	private String codigo;
	private Agencia agencia;
	private double saldo; 
	private int mesesDeconta;
	private static int incremento = 0;
	private String[] movimentacoes = new String[10];
	private int contador;
	public Conta(Cliente cliente,  Agencia agencia,double saldo) {
		this.cliente = cliente;
		this.agencia = agencia;
		this.saldo = saldo;
		this.mesesDeconta = 0;
		this.contadorDeCodigo();
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public int getMesesDeconta() {
		return mesesDeconta;
	}
	public void setMesesDeconta(int mesesDeconta) {
		this.mesesDeconta = mesesDeconta;
	}
	
	public static int getIncremento() {
		return incremento;
	}
	public static void setIncremento(int incremento) {
		Conta.incremento = incremento;
	}
	
	public String[] getMovimentacoes() {
		return movimentacoes;
	}
	public void setMovimentacoes(String[] movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public void anuidadeCliente() {
		if(this.cliente.isPremium()==false) {
			saldo = saldo - 15.00;
		}
	}
	public void contadorDeMovimentacoes(double valor, String operacao) {
		this.movimentacoes[this.getContador()] = ""+operacao+" "+valor;
		if(this.getContador()==9) {
			this.setContador(0);
		}else {
			this.setContador(this.getContador()+1);
		}
	}
	public void contadorDeCodigo() {
		if(Conta.getIncremento()>0 && 9>=Conta.getIncremento() ) {
			this.codigo = String.format("%03d", Conta.getIncremento());
		}else if(Conta.getIncremento()>9 && 99>=Conta.getIncremento()) {
			this.codigo = String.format("%02d", Conta.getIncremento());;
		}else if(Conta.getIncremento()>99 && 999>=Conta.getIncremento()) {
			this.codigo = String.format("%01d", Conta.getIncremento());
		}else {
			this.codigo = ""+Conta.getIncremento();
		}
		Conta.setIncremento(getIncremento()+1);
	}
	@Override
	public String toString() {
		return  this.getAgencia()+"Conta: "+this.codigo+" "+this.getCliente().getCpf();
	}
	
}
