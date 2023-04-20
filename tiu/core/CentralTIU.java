package tiu.core;
/** Representa a central de controlo do sistema.
 * Deve ter todos os utentes e todas as trotinetes
 * bem como gerir os vários alugueres
 */
public class CentralTIU {

	// definição das costantes para os vários casos de erro
	public static final int OK = 0;
	public static final int TROTINETE_DESCONHECIDA = 1;
	public static final int TROTINETE_EM_USO = TROTINETE_DESCONHECIDA + 1;
	public static final int TROTINETE_EM_ANDAMENTO = TROTINETE_EM_USO + 1;
	public static final int TROTINETE_EM_CARGA = TROTINETE_EM_ANDAMENTO + 1;
	public static final int TROTINETE_INDISPONIVEL = TROTINETE_EM_CARGA + 1;
	public static final int UTENTE_EM_ALUGUER = TROTINETE_INDISPONIVEL + 1;
	
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
		//Aluguer a1 = new Aluguer( codigo, utente);									//cria um aluguer entre o utente e o codigo da trotinete
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

