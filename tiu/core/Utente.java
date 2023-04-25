package tiu.core;

import java.util.ArrayList;

/**
 * Classe que representa um utente do sistema Um utente tem um user name e o seu
 * nome, se tem algum aluguer no momento e uma lista dos alugueres todos que já
 * realizou.
 * 
 */
public class Utente {

	private String userName;
	private String nome;
	private Aluguer aluguer;
	private ArrayList<Aluguer> alugueres = new ArrayList<Aluguer>();

	public Utente(String userName, String nome) {
		this.userName = userName;
		this.nome = nome;
		this.aluguer = null;
	}


	public String getUserName() { 						//Obter username
		return userName;
	}

	public String getNome() { 							//Obter o nome
		return nome;
	}

	public void setNome(String nome) { 					//Alterar
		this.nome = nome;
	}

	public ArrayList<Aluguer> getAlugueres() { 			//não sei se necessario
		return alugueres;
	}

	public void addAlugueres(Aluguer aluguer) {			//Adiciona aluguer á lista de alugueres (histórico)
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
	public void comecaAluguer(Aluguer alu) {
		if (!estaAlugar())
			this.aluguer = alu;
	}

	/**
	 * Termina o aluguer atual.
	 */
	public void terminaAluguer(Aluguer alu) {
		if (estaAlugar()) {
			this.addAlugueres(alu);
			System.out.println("Apagou al utente");
			this.aluguer = null;
		}
	}

	/**
	 * indica se está atualmente a alugar alguma trotinete
	 * 
	 * @return true se está a alugar, false caso contrário
	 */
	public boolean estaAlugar() {
		return this.aluguer != null;
	}

}