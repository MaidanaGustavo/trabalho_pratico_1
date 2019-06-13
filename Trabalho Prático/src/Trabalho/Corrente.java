package Trabalho;

import java.util.Scanner;

import excecoes.EmprestimoJaRealizadoException;
import excecoes.LimitedaContaException;
import excecoes.SaldoInsuficienteException;
import excecoes.ValorAcimadoPermitidoException;
import excecoes.ValorNegativoException;

public class Corrente extends Conta {
	private double valorEmprestado;
	private double parcelas;
	private int quantiadeDeParcelas;
	private boolean emprestimo;
	private double debito;
	Scanner tc = new Scanner(System.in);
	
	public Corrente(Cliente cliente , Agencia agencia, double saldo) {
		super(cliente,  agencia, saldo);
		this.emprestimo = false;
		this.parcelas = 0;
		this.quantiadeDeParcelas = 0;
		this.valorEmprestado = 0;
	}

	public double getValorEmprestado() {
		return valorEmprestado;
	}


	public void setValorEmprestado(double valorEmprestado) {
		this.valorEmprestado = valorEmprestado;
	}


	public double getParcelas() {
		return parcelas;
	}


	public void setParcelas(double parcelas) {
		this.parcelas = parcelas;
	}

	public boolean isEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(boolean emprestimo) {
		this.emprestimo = emprestimo;
	}
	
	public int getQuantiadeDeParcelas() {
		return quantiadeDeParcelas;
	}

	public void setQuantiadeDeParcelas(int quantiadeDeParcelas) {
		this.quantiadeDeParcelas = quantiadeDeParcelas;
	}
	
	public double getDebito() {
		return debito;
	}

	public void setDebito(double debito) {
		this.debito = debito;
	}

	@Override
	public void viradaDeMes() {
		if(this.getMesesDeconta()/12>=1) {
			this.anuidadeCliente();
			this.setSaldo(this.getSaldo()-5);
		}
		this.setMesesDeconta(this.getMesesDeconta()+1);
		if(this.quantiadeDeParcelas>0) {
			this.setSaldo(this.getSaldo()-(this.parcelas+(this.valorEmprestado*0.2)));
			this.quantiadeDeParcelas--;
			this.debito = this.debito - this.parcelas;
		}else {
			this.emprestimo = false;
			this.valorEmprestado  = 0 ;
			this.parcelas = 0 ;
			this.quantiadeDeParcelas = 0;
		}
	}

	@Override
	public boolean receber(double valor) throws ValorNegativoException {
		if(valor<0) {
			throw new ValorNegativoException();
		}else {
			this.setSaldo(this.getSaldo()+valor);
			System.out.println("Sucesso!");
			this.contadorDeMovimentacoes(valor, "TRANSFERENCIA");
			return true;
		}
		
	}
	@Override
	public boolean emprestimo(double valor)throws ValorAcimadoPermitidoException, EmprestimoJaRealizadoException {
		if(this.emprestimo==true) {
			throw new EmprestimoJaRealizadoException();
		}else if(this.getCliente().isPremium() && valor>2500 ||this.getCliente().isPremium()==false && valor>200) {
			throw new ValorAcimadoPermitidoException();
		}else {
			System.out.println("A solicitação de emprestimo de:"+valor+" pode ser parcelada em até 12x");
			do {
				System.out.println("Digite a quantidade de parcelas:");
				this.setQuantiadeDeParcelas(tc.nextInt());
			}while(this.quantiadeDeParcelas>12);
			this.valorEmprestado = valor;
			this.parcelas = this.valorEmprestado/this.quantiadeDeParcelas;
			System.out.print("O valor de: R$"+this.valorEmprestado+" ficou parcelado em"+this.quantiadeDeParcelas+" parcelas"
					+ " de R$"+this.parcelas);
		}
		this.emprestimo = true;
		this.debito = this.valorEmprestado;
		this.setSaldo(this.getSaldo()+this.valorEmprestado);
		this.contadorDeMovimentacoes(valor, "EMPRESTIMO");
		return true;
	}

	@Override
	public boolean saque(double valor) throws SaldoInsuficienteException {
		if(valor>this.getSaldo()) {
			throw new SaldoInsuficienteException(this.getSaldo(), valor);
		}else{
			this.setSaldo(this.getSaldo()-valor);
			this.contadorDeMovimentacoes(valor, "SAQUE");
			return true;
		}
	}

	@Override
	public boolean deposito(double valor) throws  ValorNegativoException {
		if(valor<0) {
			throw new ValorNegativoException();
		}else {
			this.setSaldo(this.getSaldo()+valor);
			System.out.println("Sucesso!");
			this.contadorDeMovimentacoes(valor, "DEPOSITO");
			return true;
		}
	}

	@Override
	public boolean transferencia(Conta<?> conta, double valor) throws LimitedaContaException, ValorNegativoException,SaldoInsuficienteException {
		if(valor>this.getSaldo()) {
			throw new SaldoInsuficienteException(this.getSaldo(),valor);
		}else {
			 if(conta.receber(valor)){
				 this.setSaldo(this.getSaldo()-valor);
				 System.out.println("Sucesso!");
				 this.contadorDeMovimentacoes(valor, "TRANSFERENCIA");
				 return true;
			 }
			
		}
		
		return false;
	}

	@Override
	public String extrato() {
		String s = "#EXTRATO \n";
		for (int i = 0; i < this.getMovimentacoes().length; i++) {
			if(this.getMovimentacoes()[i]!= null) {
			s += this.getMovimentacoes()[i]+"\n";
			}
		}
		s += "SALDO ATUAL: "+this.getSaldo()+"\n";
		s += "SALDO DEVEDOR: "+this.getDebito()+"\n";
 		
		return s;
	}
	@Override
	public String toString() {
		return  this.getAgencia()+"Conta: "+this.getCodigo()+" "+this.getCliente().getCpf();
	}
}
