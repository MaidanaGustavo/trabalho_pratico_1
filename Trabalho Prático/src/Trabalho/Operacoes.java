package Trabalho;

import excecoes.EmprestimoJaRealizadoException;
import excecoes.LimitedaContaException;
import excecoes.OperacaoNaoPossivelException;
import excecoes.SaldoInsuficienteException;
import excecoes.SemCargasException;
import excecoes.ValorAcimadoPermitidoException;
import excecoes.ValorNegativoException;

public interface Operacoes {
	public abstract void viradaDeMes();
	public abstract boolean receber(double valor)throws LimitedaContaException, ValorNegativoException;
	public abstract boolean saque(double valor) throws SemCargasException, SaldoInsuficienteException,OperacaoNaoPossivelException;
	public abstract boolean deposito(double valor) throws OperacaoNaoPossivelException,ValorNegativoException;
	public abstract boolean transferencia(Conta<?> conta, double valor) throws SaldoInsuficienteException,SemCargasException,LimitedaContaException,ValorNegativoException;
	public abstract boolean emprestimo(double valor)throws OperacaoNaoPossivelException,EmprestimoJaRealizadoException,ValorAcimadoPermitidoException;
	public abstract String extrato();
}
