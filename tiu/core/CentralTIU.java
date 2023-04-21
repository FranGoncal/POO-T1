package tiu.core;

import java.util.HashMap;

import tiu.mobilidade.Trotinete;

/** Representa a central de controlo do sistema.
 * Deve ter todos os utentes e todas as trotinetes
 * bem como gerir os vários alugueres
 */
public class CentralTIU {

	// definição das costantes para os vários casos de erro
	public static final int OK = 0;
	private HashMap<String, Trotinete> trotinetesMap = new HashMap<String, Trotinete>();
	private HashMap<String, Utente> utenteMap = new HashMap<String, Utente>();

	public static final int TROTINETE_DESCONHECIDA = 1;
	public static final int TROTINETE_EM_USO = TROTINETE_DESCONHECIDA + 1;
	public static final int TROTINETE_EM_ANDAMENTO = TROTINETE_EM_USO + 1;
	public static final int TROTINETE_EM_CARGA = TROTINETE_EM_ANDAMENTO + 1;
	public static final int TROTINETE_INDISPONIVEL = TROTINETE_EM_CARGA + 1;
	public static final int UTENTE_EM_ALUGUER = TROTINETE_INDISPONIVEL + 1;
	
	public CentralTIU() {
		this.trotinetesMap = null;
		this.utenteMap = null;
	}
//	public CentralTIU(HashMap<String, Trotinete> trotinetesMap, HashMap<String, Utente> utenteMap) {
//		this.trotinetesMap = trotinetesMap;
//		this.utenteMap = utenteMap;
//	}
	
	public void addTrotinetesMap(String codigo, Trotinete t) {
		trotinetesMap.put(codigo, t);
	}
	public void addUtenteMap(String username, Utente u) {
		utenteMap.put(username, u);
	}
	
	
	
	public HashMap<String, Trotinete> getTrotinetesMap() {
		return trotinetesMap;
	}

	public void setTrotinetesMap(HashMap<String, Trotinete> trotinetesMap) {
		this.trotinetesMap = trotinetesMap;
	}

	public HashMap<String, Utente> getUtenteMap() {
		return utenteMap;
	}

	public void setUtenteMap(HashMap<String, Utente> utenteMap) {
		this.utenteMap = utenteMap;
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
		
		Aluguer a1 = new Aluguer( codigo, utente);									//cria um aluguer entre o utente e o codigo da trotinete
	//	utente.comecaAluguer(a1);													//inicia aluguer
		
		
		
		
		
		return OK;
	}
	
	/** Terminar um processo de aluguer
	 * @param aluguer o aluguer a ser terminado
	 * @return OK, se correu tudo bem
	 * <br> TROTINETE_EM_ANDAMENTO se a trotinete ainda se encontrar em andamento
	 */
	public int terminarAluguer(Aluguer aluguer) {
		return OK;
	}	
}