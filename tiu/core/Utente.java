package tiu.core;

import java.util.ArrayList;

/**
 * Classe que representa um utente do sistema Um utente tem um user name e o seu
 * nome, se tem algum aluguer no momento e uma lista dos alugueres todos que já
 * realizou.
 * 
 */
public class Utente {

	private String userName; // Tem de ser unico no sistema
	private String nome;
	// private boolean aluguer;
	private Aluguer aluguer;
	private ArrayList<Aluguer> alugueres = new ArrayList<Aluguer>();

	public Utente(String userName, String nome) {
		this.userName = userName;
		this.nome = nome;
		this.aluguer = null;
	}


	public String getUserName() { // +-done
		return userName;
	}

	public String getNome() { // +-done
		return nome;
	}

	public void setNome(String nome) { // não sei se necessario
		this.nome = nome;
	}
	// public boolean isAluguer() {
	// return aluguer;
	// }

	public ArrayList<Aluguer> getAlugueres() { // não sei se necessario
		return alugueres;
	}

	public void addAlugueres(Aluguer aluguer) {
		alugueres.add(aluguer);
	}
	

	public Aluguer getAluguer() {
		return aluguer;
	}


	/**
	 * O utente começou um novo aluguer. Só deve aceitar se não tiver nenhum aluguer
	 * atualmente, caso contrário deve ignorar o novo aluguer
	 * 
	 * @param alu o aluguer começado
	 */
	public void comecaAluguer(Aluguer alu) { // +-done
		if (this.aluguer == null)
			this.aluguer = alu;
	}

	/**
	 * Termina o aluguer atual.
	 */
	public void terminaAluguer(Aluguer alu) { // +-done

		if (this.aluguer != null) {
			this.addAlugueres(alu);
			this.aluguer = null;
		}


	}

	/**
	 * indica se está atualmente a alugar alguma trotinete
	 * 
	 * @return true se está a alugar, false caso contrário
	 */
	public boolean estaAlugar() { // +-done
		return this.aluguer != null;
	}

	public void setAluguer(Aluguer aluguer) { // +-done
		this.aluguer = aluguer;
	}

	@Override
	public String toString() { // +-done
		return "Utente:\nuserName = " + userName + "\nnome = " + nome + "\naluguer = " + aluguer + "\nalugueres = "
		+ alugueres;
	}

}