package Trabalho;

import java.util.ArrayList;

public class Agencia {
	private String numero;
	private ArrayList<Conta> contas = new ArrayList<Conta>();
	private Endereco endereco;
	private String nome;

	
	
	public Agencia(String numero, Endereco endereco, String nome) {
		this.numero = numero;
		this.endereco = endereco;
		this.nome = nome;
	}
	public Agencia() {
		this.numero = "22222";
		this.endereco = null;
		this.nome = "Online"; 
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Conta> getContas() {
		return contas;
	}
	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}
	@Override
	public String toString() {
		return "Agencia: "+this.nome+" "+this.numero;
	}
	
}
