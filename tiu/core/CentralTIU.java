package tiu.core;

import java.util.HashMap;

import tiu.mobilidade.Trotinete;

/** Representa a central de controlo do sistema.
 * Deve ter todos os utentes e todas as trotinetes
 * bem como gerir os vários alugueres
 */
public class CentralTIU {

	// definição das costantes para os vários casos de erro

	private HashMap<String, Trotinete> trotinetesMap = new HashMap<String, Trotinete>();	//HashMap que guarda as trotinetes com a respetiva "chave" código que é unico
	private HashMap<String, Utente> utenteMap = new HashMap<String, Utente>();				//HashMap que guarda os utentes com a respetiva "chave" userName que é unico

	public static final int OK = 0;
	public static final int TROTINETE_DESCONHECIDA = 1;
	public static final int TROTINETE_EM_USO = TROTINETE_DESCONHECIDA + 1;
	public static final int TROTINETE_EM_ANDAMENTO = TROTINETE_EM_USO + 1;
	public static final int TROTINETE_EM_CARGA = TROTINETE_EM_ANDAMENTO + 1;
	public static final int TROTINETE_INDISPONIVEL = TROTINETE_EM_CARGA + 1;
	public static final int UTENTE_EM_ALUGUER = TROTINETE_INDISPONIVEL + 1;

	public CentralTIU() {
		this.trotinetesMap = new HashMap<String, Trotinete>();
		this.utenteMap = new HashMap<String, Utente>();
	}

	public void addTrotinetesMap(String codigo, Trotinete t) {								//Adiciona ao HashMap uma trotinete com o respetivo código
		if(codigo.equals(t.getCodigo()))													//Confirma que o codigo chave do HashMap e o da trotinete são iguais
			trotinetesMap.put(codigo, t);															
	}

	public void addUtenteMap(String username, Utente u) {									//Adiciona ao HashMap um utente com o respetivo username
		if(username.equals(u.getUserName()))												//Confirma que o userName chave do HashMap e o do utente são iguais
			utenteMap.put(username, u);
	}

	public Trotinete getTrotinete(String codigo) {											//Obtem a trotinete do mapa cujo codigo seja o do argumento
		return this.trotinetesMap.get(codigo);
	}

	public Utente getUtente(String username) {												//Obtem o utente do mapa cujo userName seja o do argumento
		return this.utenteMap.get(username);
	}

	public HashMap<String, Trotinete> getTrotinetesMap() {									//Obtem o mapa das trotinetes
		return trotinetesMap;
	}

	public HashMap<String, Utente> getUtenteMap() {											//Obtem o mapa dos utentes
		return utenteMap;
	}

	/** Cria um novo aluguer para um cliente
	 * @param utente o utente que pretende o aluguer
	 * @param codigo o código da trotinete que o utente pretende alugar
	 * @return OK, se correu tudo bem
	 * <br>TROTINETE_DESCONHECIDA, se o código não correspode a uma trotinete
	 * <br>TROTINETE_EM_USO, se a trotinete está a ser usada 
	 * <br>TROTINETE_EM_CARGA, se a trotinete está em carga
	 * <br>TROTINETE_INDISPONIVEL, se a trotinete está indisponível
	 * <br>UTENTE_EM_ALUGUER, se o utente já está a alugar outra trotinete
	 */
	public int fazAluguer(Utente utente, String codigo) {
		try {
			Aluguer a1 = new Aluguer(  utente, getTrotinete(codigo));									//cria um aluguer entre o utente e a respectiva trotinete

			if(getTrotinete(codigo).emUso())															//Caso a trotinete esteja em uso
				return TROTINETE_EM_USO;																		
			if(getTrotinete(codigo).emCarga())															//Caso esteja em carga
				return TROTINETE_EM_CARGA;	
			if(getTrotinete(codigo).estaIndisponivel())												//Caso esteja em manutenção
				return TROTINETE_INDISPONIVEL;					
			if(utente.estaAlugar()) 																		//Caso esteja em aluguer
				return UTENTE_EM_ALUGUER;

			utente.comecaAluguer(a1);																		//Caso contrário inicia aluguer
			getTrotinete(codigo).iniciaAluguer(a1);													
			return OK;
		}
		catch(Exception e) {																				//Se der erro ao criar o aluguer é porque o codigo do argumento não esta associado a nenhuma trotinete
			return TROTINETE_DESCONHECIDA;					
		}
	}

	/** Terminar um processo de aluguer
	 * @param aluguer o aluguer a ser terminado
	 * @return OK, se correu tudo bem
	 * <br> TROTINETE_EM_ANDAMENTO se a trotinete ainda se encontrar em andamento
	 */
	public int terminarAluguer(Aluguer aluguer) {															
		if(aluguer.trotinete.emAndamento())																	//Confirma se está em andamento, se tiver não termina o aluguer
			return TROTINETE_EM_ANDAMENTO;
		aluguer.terminar();																					//Se não tiver em andamento termina o aluguer
		return OK;
	}	
}