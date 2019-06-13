package Trabalho;

public class Endereco {
 private String pais;
 private String estado;
 private String cidade;
 private String rua;
 private String bairro;
 private String cep;
 private String numero;
 
public Endereco(String pais, String estado, String cidade, String rua, String bairro, String cep, String numero) {
	this.pais = pais;
	this.estado = estado;
	this.cidade = cidade;
	this.rua = rua;
	this.bairro = bairro;
	this.cep = cep;
	this.numero = numero;
}
public String getPais() {
	return pais;
}
public void setPais(String pais) {
	this.pais = pais;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public String getCidade() {
	return cidade;
}
public void setCidade(String cidade) {
	this.cidade = cidade;
}
public String getRua() {
	return rua;
}
public void setRua(String rua) {
	this.rua = rua;
}
public String getBairro() {
	return bairro;
}
public void setBairro(String bairro) {
	this.bairro = bairro;
}
public String getCep() {
	return cep;
}
public void setCep(String cep) {
	this.cep = cep;
}
public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}
 
}
