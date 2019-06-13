package Trabalho;

import excecoes.LimitedaContaException;
import excecoes.OperacaoNaoPossivelException;
import excecoes.SaldoInsuficienteException;
import excecoes.SemCargasException;
import excecoes.ValorNegativoException;

public class Facil  extends Conta {
	private int quantidadedeSaques;
	private int quantidadedeTransferencias;
	public Facil(Cliente cliente,  Agencia agencia, double saldo) {
		super(cliente, agencia, saldo);
		this.quantidadedeSaques = 1;
		this.quantidadedeTransferencias =1;
	}
	
	public int getQuantidadedeSaques() {
		return quantidadedeSaques;
	}

	public void setQuantidadedeSaques(int quantidadedeSaques) {
		this.quantidadedeSaques = quantidadedeSaques;
	}

	public int getQuantidadedeTransferencias() {
		return quantidadedeTransferencias;
	}

	public void setQuantidadedeTransferencias(int quantidadedeTransferencias) {
		this.quantidadedeTransferencias = quantidadedeTransferencias;
	}
	@Override
	public boolean saque(double valor) throws SemCargasException, SaldoInsuficienteException  {
		if(this.quantidadedeSaques==0) {
			throw new  SemCargasException(1);
		}else if(valor>this.getSaldo()) {
			throw new SaldoInsuficienteException(this.getSaldo(),valor);
		}else{
			this.setSaldo(this.getSaldo()-valor);
		}
		this.quantidadedeSaques--;	
		this.contadorDeMovimentacoes(valor, "SAQUE");
		return true;
	}
	@Override
	public boolean transferencia(Conta<?> conta, double valor) throws SaldoInsuficienteException,SemCargasException, LimitedaContaException, ValorNegativoException {
		if(valor>this.getSaldo()) {
			throw new SaldoInsuficienteException(this.getSaldo(),valor);
		}else if(this.quantidadedeTransferencias==0) {
			throw new SemCargasException(0);
		}else {
			 if(conta.receber(valor)){
				 this.setSaldo(this.getSaldo()-valor);
				 this.quantidadedeTransferencias--;
				 System.out.println("Sucesso!");
				 this.contadorDeMovimentacoes(valor, "TRANSFERENCIA");
				 return true;
			 }
			
		}
		
		return false;
	}
	@Override
	public void viradaDeMes() {
		if(this.getMesesDeconta()/12>=1) {
			this.anuidadeCliente();
			this.setSaldo(this.getSaldo()-10);
		}
		this.setMesesDeconta(this.getMesesDeconta()+1);
		this.quantidadedeSaques++;
		this.quantidadedeTransferencias++;
	}

	@Override
	public boolean receber(double valor)  throws LimitedaContaException, ValorNegativoException{
		if(this.getSaldo()+valor>5.000) {
			throw new LimitedaContaException(this.getSaldo());
		}else if(valor<0) {
			throw new ValorNegativoException();
		}else {
			this.setSaldo(this.getSaldo()+valor);
			System.out.println("Sucesso!");
			this.contadorDeMovimentacoes(valor, "TRANSFERENCIA");
			return true;
		}		
	}

	@Override
	public boolean deposito(double valor) throws OperacaoNaoPossivelException{
		throw new OperacaoNaoPossivelException();
	}

	@Override
	public boolean emprestimo(double valor)throws OperacaoNaoPossivelException{
		throw new OperacaoNaoPossivelException();
	}

	@Override
	public String extrato() {
		String s = "#EXTRATO \n";
		for (int i = 0; i < this.getMovimentacoes().length; i++) {
			s += this.getMovimentacoes()[i]+"\n";
		}
		s += "SALDO ATUAL "+this.getSaldo()+"\n";
		return s;
	}
	@Override
	public String toString() {
		return  this.getAgencia()+"Conta: "+this.getCodigo()+" "+this.getCliente().getCpf();
	}
}
	

