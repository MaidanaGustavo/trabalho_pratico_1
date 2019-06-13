package Trabalho;

import java.util.ArrayList;

public class Cliente implements Comparable<Cliente> {
	private String nome;
	private String dataDeNascimento;
	private String cpf;
	private boolean premium;
	private Endereco endereco;
	private ArrayList<Conta> contas = new ArrayList<>();
	
	public Cliente(String nome, String dataDeNascimento, String cpf, boolean premium, Endereco endereco) {
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.premium = premium;
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public boolean isPremium() {
		return premium;
	}
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Conta> getContas() {
		return contas;
	}
	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Cliente) {
			return this.cpf.equalsIgnoreCase(((Cliente) o).getCpf());
		}
		return false;
	    
	}
	@Override
	public int compareTo(Cliente o) {
		// TODO Auto-generated method stub
		return this.nome.compareTo(o.getNome());
	}
	@Override
	public String toString() {
		return ""+this.nome+"\n"+this.cpf+"\n";
	}
	
}
