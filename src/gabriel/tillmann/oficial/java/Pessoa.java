package gabriel.tillmann.oficial.java;

import java.io.Serializable;

public class Pessoa implements Serializable{

	private String ID, Nome, CPF, Email, TelFixo, TelCelular;
	private char Sexo;
		
	public Pessoa() {
		super();
		ID = ""; 
	}
	
	public Pessoa(String iD, String nome, String cPF, String email,
			String telFixo, String telCelular, char sexo) {
		super();
		ID = iD;
		Nome = nome;
		CPF = cPF;
		Email = email;
		TelFixo = telFixo;
		TelCelular = telCelular;
		Sexo = sexo;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelFixo() {
		return TelFixo;
	}
	public void setTelFixo(String telFixo) {
		TelFixo = telFixo;
	}
	public String getTelCelular() {
		return TelCelular;
	}
	public void setTelCelular(String telCelular) {
		TelCelular = telCelular;
	}
	public char getSexo() {
		return Sexo;
	}
	public void setSexo(char sexo) {
		Sexo = sexo;
	}
	
}
