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
		String n;
		System.out.println(this.instruncoes());
		n = tc.nextLine();
		do {
			
			try{
				switch(n) {
				case "1":
					for (Conta<?> conta : todasAsContas) {
						conta.viradaDeMes();
					}	
				System.out.println("Mês virado com sucesso!");
					break;
				case "2":
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
				case "3":
					System.out.println("Digite o tipo de conta");
					boolean existe = false;
					String tipoDeConta = tc.nextLine() ;
					String tipoDeCliente;
					System.out.print("Digite o CPF:");
					String cpfCliente = tc.nextLine();
					for (Cliente cliente : clientes) {
						if(cpfCliente.equalsIgnoreCase(cliente.getCpf())) {
							existe = true;
							System.out.println("Atualize seus dados, por favor:");
							System.out.print("Digite seu nome: ");
							cliente.setNome(tc.nextLine());
							System.out.print("Digite seu pais: ");
							cliente.getEndereco().setPais(tc.nextLine());
							System.out.print("Digite seu estado: ");
							cliente.getEndereco().setEstado(tc.nextLine());
							System.out.print("Digite sua cidade: ");
							cliente.getEndereco().setCidade(tc.nextLine());
							System.out.print("Digite sua rua: ");
							cliente.getEndereco().setRua(tc.nextLine());
							System.out.print("Digite seu bairro: ");
							cliente.getEndereco().setBairro(tc.nextLine());
							System.out.print("Digite seu cep:");
							cliente.getEndereco().setCep(tc.nextLine());
							System.out.print("Digite o numero da residencia:");
							cliente.getEndereco().setNumero(tc.nextLine());
							System.out.print("Digite sua data de nascimento(xx/mm/yy): ");
							cliente.setDataDeNascimento(tc.nextLine());
							System.out.println("Digite o tipo de cliente que deseja ser (TRADICIONAL/PREMIUM)" );
							 tipoDeCliente = tc.nextLine();
							if(tipoDeCliente.equalsIgnoreCase("TRADICIONAL")) {
								cliente.setPremium(false);
							}else {
								cliente.setPremium(true);
							}
							this.cliente = cliente;
						}
					}
					if(existe == false) {
						boolean premium;
						System.out.print("Digite seu nome: ");
						String nome = tc.nextLine();
						System.out.print("Digite seu pais: ");
						String pais = tc.nextLine();
						System.out.print("Digite seu estado: ");
						String estado = tc.nextLine();
						System.out.print("Digite sua cidade: ");
						String cidade = tc.nextLine();
						System.out.print("Digite sua rua: ");
						String rua = tc.nextLine();
						System.out.print("Digite seu bairro: ");
						String bairro = tc.nextLine();
						System.out.print("Digite seu cep:");
						String cep = tc.nextLine();
						System.out.print("Digite o numero da residencia:");
						String numeroendereco = tc.nextLine();
						System.out.print("Digite sua data de nascimento(xx/mm/yy): ");
						String datadenascimento = tc.nextLine();
						System.out.println("Digite o tipo de cliente que deseja ser (TRADICIONAL/PREMIUM)" );
						 tipoDeCliente = tc.nextLine();
						 if(tipoDeCliente.equalsIgnoreCase("TRADICIONAL")) {
								premium = false;
							}else {
								premium  = true;
							}
						 endereco = new Endereco(pais,estado,cidade,rua,bairro,cep,numeroendereco);
						 this.cliente = new Cliente(nome,datadenascimento,cpfCliente,premium,endereco);
					}
						 this.clientes.add(cliente);
						 if(tipoDeConta.equalsIgnoreCase("F")) {
							 double saldoInicial = tc.nextDouble();
							 Facil e = new Facil(cliente,online,saldoInicial);
							this.todasAsContas.add(e);
							this.contasFacil.add(e);
							this.online.getContas().add(e);
						 }else {
							 String numeroDaAgencia = tc.nextLine();
							 double saldoInicial = tc.nextDouble();
							 for (Agencia agencia : agencias) {
								if(agencia.getNumero().equalsIgnoreCase(numeroDaAgencia)) {
									if(tipoDeConta.equalsIgnoreCase("C")) {
										Corrente e =  new Corrente(cliente,agencia,saldoInicial);
										this.todasAsContas.add(e);
										this.contasCorrente.add(e);
										agencia.getContas().add(e);
									}else {
										Poupanca e = new Poupanca(cliente,agencia,saldoInicial);
										this.todasAsContas.add(e);
										this.contasPoupanca.add(e);
										agencia.getContas().add(e);
									}
								}
							}
						 }
					System.out.println(this.todasAsContas);
					System.out.println("Conta criada com sucesso!");
					break;
				case "4":
					System.out.print("Digite o numero da agencia: ");
					String numerodaAgencia = tc.nextLine();
					System.out.print("Digite o numero da conta: " );
					String numeroDaConta = tc.nextLine();
					Conta<?> contasAgencia = this.acharConta(numerodaAgencia,numeroDaConta);
					System.out.println(contasAgencia);
							try {
								System.out.print("Digite o valor: ");
									double valor = tc.nextDouble();
										contasAgencia.saque(valor);		
									}catch(SemCargasException e) {
										System.out.println(e);
									}catch(OperacaoNaoPossivelException e) {
										System.out.println(e);
									}catch(SaldoInsuficienteException e) {
										System.out.println(e);
										try {
											System.out.print("Digite o valor: ");
											double valor = tc.nextDouble();
											contasAgencia.saque(valor);
										}catch(SaldoInsuficienteException e1) {
											contasAgencia.saque(0);
										}
									}
							System.out.println("Saque realizado com exito!");
					break;
				case "5":
					System.out.print("Digite o numero da agencia: ");
					String numerodaagencia = tc.nextLine();
					System.out.print("Digite o numero da conta: " );
					String numeroDaconta = tc.nextLine();
					Conta<?> contasagencia = this.acharConta(numerodaagencia,numeroDaconta);							
						try {
										System.out.print("Digite o valor: ");
										double valor = tc.nextDouble();
										contasagencia.deposito(valor);
									}catch(OperacaoNaoPossivelException e) {
										System.out.println(e);
									}catch(ValorNegativoException e) {
										try {
											System.out.print("Digite o valor: ");
											double valor = tc.nextDouble();
											contasagencia.deposito(valor);
										}catch(ValorNegativoException e1) {
											contasagencia.deposito(0);
										}
									}
								
							
						System.out.println("Deposito realizado com exito!");
					break;
				case "6":
					System.out.print("Digite o numero da agencia: ");
					 numerodaAgencia = tc.nextLine();
					 System.out.print("Digite o numero da conta: " );
					 numeroDaConta = tc.nextLine();
					 contasAgencia = this.acharConta(numerodaAgencia,numeroDaConta);		
					 System.out.print("Digite o numero da agencia destino: ");
					 String numeroAgenciaDestino = tc.nextLine();
					 System.out.print("Digite o numero da conta: " );
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
					 System.out.println("Transferencia  realizada com exito!");
					 break;
				case "7":
					System.out.print("Digite o numero da agencia: ");
					 numerodaAgencia = tc.nextLine();
					 System.out.print("Digite o numero da conta: ");
					 numeroDaConta = tc.nextLine();
					 contasAgencia = this.acharConta(numerodaAgencia,numeroDaConta);
					 try {
						 System.out.print("Digite o valor: ");
						 double valor = tc.nextDouble();
						 contasAgencia.emprestimo(valor);
					 }catch(OperacaoNaoPossivelException e) {
						 System.out.println(e);
					 }catch(EmprestimoJaRealizadoException e1) {
						 System.out.println(e1);
					 }catch(ValorAcimadoPermitidoException e) {
						 System.out.println(e);
						 try {
							 System.out.print("Digite o valor: ");
							 double valor = tc.nextDouble();
							 contasAgencia.emprestimo(valor);
						 }catch(ValorAcimadoPermitidoException e1){
							 System.out.println(e1);
						 }
					 }
					 System.out.println("Emprestimo realizado com exito!");
					break;
				case "8":
					System.out.print("Digite o numero da agencia: ");
					numerodaAgencia = tc.nextLine();
					System.out.print("Digite o numero da conta: ");
					numeroDaConta = tc.nextLine();
					contasAgencia = this.acharConta(numerodaAgencia,numeroDaConta);
					System.out.println(contasAgencia.extrato());
					break;
				case "9":
					System.out.print("Digite o tipo de relatório: ");
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
						if(this.online.getContas().size()>1) {
							Collections.sort(this.online.getContas(),b);
							System.out.println(this.online.getNumero());
							System.out.println(this.online.getContas());
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
			}catch(NullPointerException e) {
				System.out.println("Essa conta nao existe!");
			}
			
			
			n = tc.nextLine();
			}while(!n.equalsIgnoreCase("-1"));
	}
	public String instruncoes() {
		return "Bem vindo ao banco! Abaixo há algumas opções de movimetacões:"+"\n"+"Digite 1 para virar o mês."+"\n"
	+"Digite 2 para cadastrar uma agência nova."+"\n"+"Digite 3 para abrir uma nova conta."+"\n"+"Digite 4 para sacar."+"\n"
				+"Digite 5 para realizar um depósito."+"\n"+"Digite 6 para realizar uma transferencia."+
				"\n"+"Digite 7 para solicitar um empréstimo."+"\n"+"Digite 8 para gerar um extrato."+"\n"+"Digite 9 para gera um relatório. \n";
				
	}
	public Conta<?> acharConta(String agencia,String conta){
		for (Agencia agencia1 : agencias) {
			if(agencia1.getNumero().equalsIgnoreCase(agencia)) {
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
