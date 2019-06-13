package Trabalho;
import java.util.Scanner;

import excecoes.EmprestimoJaRealizadoException;
import excecoes.LimitedaContaException;
import excecoes.OperacaoNaoPossivelException;
import excecoes.SaldoInsuficienteException;
import excecoes.SemCargasException;
import excecoes.ValorAcimadoPermitidoException;
import excecoes.ValorNegativoException;

import java.util.ArrayList;
public class Main {
	public static void main(String[] args) throws SaldoInsuficienteException, OperacaoNaoPossivelException, SemCargasException, ValorNegativoException, LimitedaContaException, ValorAcimadoPermitidoException, EmprestimoJaRealizadoException {
		Banco banco = new Banco();
		banco.movimentacoes();
	}
}
