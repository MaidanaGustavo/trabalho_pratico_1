package Trabalho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import excecoes.EmprestimoJaRealizadoException;
import excecoes.LimitedaContaException;
import excecoes.OperacaoNaoPossivelException;
import excecoes.SaldoInsuficienteException;
import excecoes.SemCargasException;
import excecoes.ValorAcimadoPermitidoException;
import excecoes.ValorNegativoException;

public class Banco {
	private ArrayList<Conta> todasAsContas = new ArrayList<Conta>();
	private ArrayList<Facil> contasFacil = new ArrayList<Facil>();
	private ArrayList<Corrente> contasCorrente = new ArrayList<Corrente>();
	private ArrayList<Poupanca> contasPoupanca = new ArrayList<Poupanca>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Agencia> agencias = new ArrayList<Agencia>();
	private Endereco endereco;
	private Cliente cliente;
	ComparadorA a = new ComparadorA();
	ComparadorB b = new ComparadorB();
 	Scanner tc = new Scanner(System.in);
 	Agencia online  = new Agencia();
	public void movimentacoes() throws SaldoInsuficienteException, OperacaoNaoPossivelException, SemCargasException, ValorNegativoException, LimitedaContaException,ValorAcimadoPermitidoException, EmprestimoJaRealizadoException {
		int n = 0;
		System.out.println(this.instruncoes());
		n = tc.nextInt();
		tc.nextLine();
		do {
			switch(n) {
			case 1:
				for (Conta<?> conta : todasAsContas) {
					conta.viradaDeMes();
				}	
			System.out.println("Mês virado com sucesso!");
				break;
			case 2:
				String nomeAgencia = tc.nextLine(); 
				String numeroAgencia = tc.nextLine();
				String paisAgencia = tc.nextLine();
				String estadoAgencia = tc.nextLine();
				String cidadeAgencia = tc.nextLine();
				String ruaAgencia = tc.nextLine();
				String bairroAgencia = tc.nextLine();
				String cepAgencia = tc.nextLine();
				String numeroenderecoAgencia = tc.nextLine();
				endereco = new Endereco(paisAgencia,estadoAgencia,cidadeAgencia,ruaAgencia,bairroAgencia,cepAgencia,numeroenderecoAgencia);
				agencias.add(new Agencia(numeroAgencia,endereco,nomeAgencia));
				System.out.println("Agencia criada com sucesso!");
				break;
			case 3:
				System.out.println("Digite o tipo de conta");
				tc.nextLine();
				boolean existe = false;
				String tipoDeConta = tc.nextLine() ;
				String tipoDeCliente;
				String cpfCliente = tc.nextLine();
				for (Cliente cliente : clientes) {
					if(cpfCliente.equalsIgnoreCase(cliente.getCpf())) {
						existe = true;
						cliente.setNome(tc.nextLine());
						cliente.getEndereco().setPais(tc.nextLine());
						cliente.getEndereco().setEstado(tc.nextLine());
						cliente.getEndereco().setCidade(tc.nextLine());
						cliente.getEndereco().setRua(tc.nextLine());
						cliente.getEndereco().setBairro(tc.nextLine());
						cliente.getEndereco().setCep(tc.nextLine());
						cliente.getEndereco().setNumero(tc.nextLine());
						cliente.setDataDeNascimento(tc.nextLine());
						 tipoDeCliente = tc.nextLine();
						if(tipoDeCliente.equalsIgnoreCase("TRADICIONAL")) {
							cliente.setPremium(false);
						}else {
							cliente.setPremium(true);
						}
						
					}
				}
				if(existe == false) {
					boolean premium;
					String nome = tc.nextLine();
					String pais = tc.nextLine();
					String estado = tc.nextLine();
					String cidade = tc.nextLine();
					String rua = tc.nextLine();
					String bairro = tc.nextLine();
					String cep = tc.nextLine();
					String numeroendereco = tc.nextLine();
					String datadenascimento = tc.nextLine();
					 tipoDeCliente = tc.nextLine();
					 if(tipoDeCliente.equalsIgnoreCase("TRADICIONAL")) {
							premium = false;
						}else {
							premium  = true;
						}
					 endereco = new Endereco(pais,estado,cidade,rua,bairro,cep,numeroendereco);
					 this.cliente = new Cliente(nome,datadenascimento,cpfCliente,premium,endereco);
					 if(tipoDeConta.equalsIgnoreCase("F")) {
						 double saldoInicial = tc.nextDouble();
						this.todasAsContas.add(new Facil(cliente,online,saldoInicial));
						this.contasFacil.add(new Facil(cliente,online,saldoInicial));
					 }else {
						 String numeroDaAgencia = tc.nextLine();
						 double saldoInicial = tc.nextDouble();
						 for (Agencia agencia : agencias) {
							if(agencia.getNumero().equalsIgnoreCase(numeroDaAgencia)) {
								if(tipoDeConta.equalsIgnoreCase("C")) {
									this.todasAsContas.add((new Corrente(cliente,agencia,saldoInicial)));
									this.contasCorrente.add(new Corrente(cliente,agencia,saldoInicial));
								}else {
									this.todasAsContas.add((new Poupanca(cliente,agencia,saldoInicial)));
									this.contasPoupanca.add(new Poupanca(cliente,agencia,saldoInicial));
								}
							}
						}
					 }
				}
				System.out.println("Conta criada com sucesso!");
				System.out.println(this.todasAsContas);
				break;
			case 4:
				String numerodaAgencia = tc.nextLine();
				String numeroDaConta = tc.nextLine();
				 tc.nextLine();
				
				Conta<?> contasAgencia = this.acharConta(numerodaAgencia,numeroDaConta);		
						
						try {
							
								double valor = tc.nextDouble();
									contasAgencia.saque(valor);		
								}catch(SemCargasException e) {
									System.out.println(e);
								}catch(OperacaoNaoPossivelException e) {
									System.out.println(e);
								}catch(SaldoInsuficienteException e) {
									System.out.println(e);
									try {
										 
										double valor = tc.nextDouble();
										contasAgencia.saque(valor);
									}catch(SaldoInsuficienteException e1) {
										contasAgencia.saque(0);
									}
								}
							
					
				
				
				break;
			case 5:
				String numerodaagencia = tc.nextLine();
				tc.nextLine();
				String numeroDaconta = tc.nextLine();
				 tc.nextLine();
				
				Conta<?> contasagencia = this.acharConta(numerodaagencia,numeroDaconta);							
					try {
									double valor = tc.nextDouble();
									contasagencia.deposito(valor);
								}catch(OperacaoNaoPossivelException e) {
									System.out.println(e);
								}catch(ValorNegativoException e) {
									try {
										double valor = tc.nextDouble();
										contasagencia.deposito(valor);
									}catch(ValorNegativoException e1) {
										contasagencia.deposito(0);
									}
								}
							
						
				
				break;
			case 6:
				 numerodaAgencia = tc.nextLine();
				 numeroDaConta = tc.nextLine();
				 tc.nextLine();
				 contasAgencia = this.acharConta(numerodaAgencia,numeroDaConta);		
				 String numeroAgenciaDestino = tc.nextLine();
				 String numeroContaDestino = tc.nextLine();
				 Conta<?> contaDestino = this.acharConta(numeroAgenciaDestino, numeroContaDestino);
				 try {
					 double valor = tc.nextDouble();
					 contasAgencia.transferencia(contaDestino, valor);
				 }catch(SemCargasException e) {
					 System.out.println(e);
				 }catch(LimitedaContaException e) {
					 System.out.println(e);
					 try {
						 double valor = tc.nextDouble();
						 contasAgencia.transferencia(contaDestino, valor);
					 }catch(LimitedaContaException e1) {
						 System.out.println(e1);
					 }catch(SaldoInsuficienteException e1) {
						 System.out.println(e1);
						 try {
							 double valor = tc.nextDouble();
							 contasAgencia.transferencia(contaDestino, valor);
						 }catch(SaldoInsuficienteException e2) {
							 System.out.println(e2);
							 contasAgencia.transferencia(contaDestino, 0);
						 }
					 }
				 }catch(SaldoInsuficienteException e) {
					 System.out.println(e);
					 try {
						 double valor = tc.nextDouble();
						 contasAgencia.transferencia(contaDestino, valor);
					 }catch(SaldoInsuficienteException e1) {
						 System.out.println(e1);
						 contasAgencia.transferencia(contaDestino, 0);
					 }
				 }catch(ValorNegativoException e) {
					 System.out.println(e);
					 try {
						 double valor = tc.nextDouble();
						 contasAgencia.transferencia(contaDestino, valor);
					 }catch(ValorNegativoException e2) {
						 System.out.println(e);
						 contasAgencia.transferencia(contaDestino, 0);
					 }catch(SaldoInsuficienteException e4){
						 System.out.println(e4);
						 contasAgencia.transferencia(contaDestino, 0);
					 }
					 
				 }
				 break;
			case 7:
				 numerodaAgencia = tc.nextLine();
				 numeroDaConta = tc.nextLine();
				 tc.nextLine();
				 contasAgencia = this.acharConta(numerodaAgencia,numeroDaConta);
				 try {
					 double valor = tc.nextDouble();
					 contasAgencia.emprestimo(valor);
				 }catch(OperacaoNaoPossivelException e) {
					 System.out.println(e);
				 }catch(EmprestimoJaRealizadoException e1) {
					 System.out.println(e1);
				 }catch(ValorAcimadoPermitidoException e) {
					 System.out.println(e);
					 try {
						 double valor = tc.nextDouble();
						 contasAgencia.emprestimo(valor);
					 }catch(ValorAcimadoPermitidoException e1){
						 System.out.println(e1);
					 }
				 }
				break;
			case 8:
				numerodaAgencia = tc.nextLine();
				numeroDaConta = tc.nextLine();
				contasAgencia = this.acharConta(numerodaAgencia,numeroDaConta);
				System.out.println(contasAgencia.extrato());
				break;
			case 9:
				tc.nextLine();
				String tipoDoRelatorio= tc.nextLine();
				switch (tipoDoRelatorio){
				case "A":
					for (Agencia agencia : agencias) {
						Collections.sort(agencia.getContas(),a);
						System.out.println(agencia.getNumero());
						System.out.println(agencia.getContas());
					}
					break;
				case "B":
					for (Agencia agencia : agencias) {
						Collections.sort(agencia.getContas(),b);
						System.out.println(agencia.getNumero());
						System.out.println(agencia.getContas());
					}

					break;
				case "C":
					for (Poupanca poupanca : this.contasPoupanca) {
						System.out.println(poupanca);
					}
					break;
				case "D":
					for (Corrente  corrente : this.contasCorrente) {
						System.out.println(corrente);
					}
					break;
				case "E":
					for (Facil facil : this.contasFacil) {
						System.out.println(facil);
					}
					break;
				case "F":
					for (Cliente cliente : this.clientes) {
						if(cliente.getContas().size()>1) {
							System.out.println(cliente);
						}
					}
					break;
				case "G":
					Collections.sort(this.clientes);
					for (Cliente cliente : this.clientes) {
						System.out.println(cliente);
					}
					break;
					default:
						System.out.println("Escolha um tipo de relatorio existente!");
				}
				
				break;
				default:
					System.out.println(this.instruncoes());
		}
			n = tc.nextInt();
			}while(n!= -1);
	}
	public String instruncoes() {
		return "Bem vindo ao banco! Abaixo há algumas opções de movimetacões:"+"\n"+"Digite 1 para virar o mês."+"\n"
	+"Digite 2 para cadastrar uma agência nova."+"\n"+"Digite 3 para abrir uma nova conta."+"\n"+"Digite 4 para sacar."+"\n"
				+"Digite 5 para realizar um depósito."+"\n"+"Digite 6 para realizar uma transferencia."+
				"\n"+"Digite 7 para solicitar um empréstimo."+"\n"+"Digite 8 para gerar um extrato."+"\n"+"Digite 9 para gera um relatório. \n";
				
	}
	public Conta<?> acharConta(String agencia,String conta){
		for (Agencia agencia1 : agencias) {
			if(agencia1.getNumero().equals(agencia1)) {
				for (Conta<?> contasAgencia : agencia1.getContas()) {
					if(contasAgencia.getCodigo().equalsIgnoreCase(conta)){
						return contasAgencia;
					}
				}
			}
		}
		return null;
	}
	public ArrayList<Conta> getTodasAsContas() {
		return todasAsContas;
	}
	public void setTodasAsContas(ArrayList<Conta> todasAsContas) {
		this.todasAsContas = todasAsContas;
	}
	public ArrayList<Facil> getContasFacil() {
		return contasFacil;
	}
	public void setContasFacil(ArrayList<Facil> contasFacil) {
		this.contasFacil = contasFacil;
	}
	public ArrayList<Corrente> getContasCorrente() {
		return contasCorrente;
	}
	public void setContasCorrente(ArrayList<Corrente> contasCorrente) {
		this.contasCorrente = contasCorrente;
	}
	public ArrayList<Poupanca> getContasPoupanca() {
		return contasPoupanca;
	}
	public void setContasPoupanca(ArrayList<Poupanca> contasPoupanca) {
		this.contasPoupanca = contasPoupanca;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ArrayList<Agencia> getAgencias() {
		return agencias;
	}
	public void setAgencias(ArrayList<Agencia> agencias) {
		this.agencias = agencias;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
