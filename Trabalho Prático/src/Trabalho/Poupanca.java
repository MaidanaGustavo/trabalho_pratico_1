package Trabalho;

import excecoes.LimitedaContaException;
import excecoes.OperacaoNaoPossivelException;
import excecoes.SaldoInsuficienteException;
import excecoes.ValorNegativoException;

public class Poupanca extends Conta  {
	
	public Poupanca(Cliente cliente, Agencia agencia, double saldo) {
		super(cliente, agencia, saldo);
	}

	@Override
	public void viradaDeMes() {
		if(this.getMesesDeconta()/12>=1) {
			this.anuidadeCliente();
			this.setSaldo(this.getSaldo()+(this.getSaldo()/100)*1.4);
		}
		
	}

	@Override
	public boolean receber(double valor)throws ValorNegativoException {
		if(valor<0) {
			throw new  ValorNegativoException();
		}else {
			this.setSaldo(this.getSaldo()+valor);
			System.out.println("Sucesso!");
			this.contadorDeMovimentacoes(valor, "TRANSFERENCIA");
			return true;
		}
	}

	@Override
	public boolean saque(double valor) throws OperacaoNaoPossivelException {
		throw new OperacaoNaoPossivelException();
	}

	@Override
	public boolean deposito(double valor) throws ValorNegativoException {
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
	public boolean emprestimo(double valor) throws OperacaoNaoPossivelException {
		throw new OperacaoNaoPossivelException();
	}

	@Override
	public String extrato() {
		String s = "#EXTRATO \n";
		for (int i = 0; i < this.getMovimentacoes().length; i++) {
			s += this.getMovimentacoes()[i]+"\n";
		}
		s += "SALDO ATUAL: "+this.getSaldo()+"\n";
		return s;
	}
	@Override
	public String toString() {
		return  this.getAgencia()+"Conta: "+this.getCodigo()+" "+this.getCliente().getCpf();
	}

}
