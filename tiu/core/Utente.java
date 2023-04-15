package tiu.core;

import java.util.ArrayList;

/**
 * Classe que representa um utente do sistema
 * Um utente tem um user name e o seu nome, 
 * se tem algum aluguer no momento e uma lista
 * dos alugueres todos que já realizou. 
 * 
 */
public class Utente {
	
	private String userName;
	private String nome;
	private boolean aluguer;
	private ArrayList<Aluguer> alugueres = new ArrayList<Aluguer>();
	
	public Utente(String userName, String nome, boolean aluguer) {
		this.userName = userName;
		this.nome = nome;
		this.aluguer = aluguer;
	}
	
	
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public boolean isAluguer() {
		return aluguer;
	}



	public void setAluguer(boolean aluguer) {
		this.aluguer = aluguer;
	}



	public ArrayList<Aluguer> getAlugueres() {
		return alugueres;
	}

	/** O utente começou um novo aluguer. Só deve aceitar
	 * se não tiver nenhum aluguer atualmente, caso contrário
	 * deve ignorar o novo aluguer
	 * @param alu o aluguer começado
	 */
	public void comecaAluguer(Aluguer alu) {
	}
	
	/** Termina o aluguer atual.
	 */
	public void terminaAluguer() {
	}
	
	/** indica se está atualmente a alugar alguma trotinete
	 * @return true se está a alugar, false caso contrário
	 */
	public boolean estaAlugar() {
		return false;
	}
}
