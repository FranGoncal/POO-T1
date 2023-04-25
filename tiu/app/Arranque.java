package tiu.app;

import java.util.HashMap;
import java.util.Set;

import tiu.core.*;
import tiu.mobilidade.*;

/** Esta classe é responsável por criar os componentes do sistema,
 *  inicializar os dados com a configuração de teste e
 *  apresentar as várias janelas
 */
public class Arranque {

	public static void main(String[] args) {

		// criar a central e fazer o respetivo setup
		CentralTIU central = setupSistema();

		// criar a janela principal
		JanelaPrincipal jp = new JanelaPrincipal( central );
		jp.setLocation( 20, 380 );
		jp.setVisible( true );


		// TODO FEITO criar as janelas dos utentes (criar uma para cada utente)
		int i=0;
		HashMap<String, Utente> utenteMap = central.getUtenteMap();											//Obter o mapa dos utentes da central
		Set<String> chavesUtentes  = utenteMap.keySet();													//Cria um set com as chaves do mapa	
		for (String chave : chavesUtentes ) {																//Percorre as chaves do mapa	
			//TODO FEITO cuidado com o null, substituir pelo valor correto
			JanelaUtente ju = new JanelaUtente( jp, central, utenteMap.get(chave) );						//Criar a janela utente
			ju.setLocation( 20 + i*(ju.getWidth()+10), 20);													//Definir a localizção da janela
			ju.setVisible( true );																			//Janela visivel
			i++;																							//Adiciona a posição da janela
		}

		// TODO FEITO criar as janelas das trotinetes (criar uma por cada trotinete)
		i=0;
		HashMap<String, Trotinete> trotineteMap = central.getTrotinetesMap();								//Obter o mapa das trotinetes da central
		Set<String> chavesTrotinetes= trotineteMap.keySet();												//Cria um set com as chaves do mapa
		for (String chave : chavesTrotinetes ) {															//Percorre as chaves do mapa	
			// TODO FEITO cuidado com o null, substituir pelo valor correto
			JanelaTrotinete jt = new JanelaTrotinete( jp, trotineteMap.get(chave)) ;						//Criar a janela trotinete
			jt.setLocation( 20 + i*(jt.getWidth()+10), 200);												//Definir a localizção da janela
			jt.setVisible( true );																			//Janela visivel
			i++;																							//Adiciona a posição da janela
		}

	}

	/** cria a central e usa a configuração de testes
	 * @return a central criada
	 */
	private static CentralTIU setupSistema() {
		CentralTIU central = new CentralTIU(); //trotinetesMap,utentesMap

		// TODO FEITO criar os utentes
		createUtente("fsergio@ipcb.pt","Sérgio Barbosa", central);
		createUtente("jojo89@g.com","João José Silva", central);
		createUtente("codeguru@guru.com ","Harry Hacker", central);
		createUtente("luis@ipcbcampus.pt","Luis Santos", central);
		createUtente("francisco@ipcbcampus.pt","Francisco Gonçalves", central);
	
		
		// TODO FEITO criar as trotinetes
		Trotinete PooTr1 = createTrotinete("PooTr1",20000, 5, central);
		Trotinete PooTr2 =createTrotinete("PooTr2",20000, 5, central);
		Trotinete PooGO = createTrotinete("PooGO",30000, 6, central);
		Trotinete Poo20 = createTrotinete("Poo20",30000, 7, central);
		

		// TODO FEITO configurar as autonomias restantes para as trotinetes
		PooTr1.setAutonomiaRestante(400);
		PooTr2.setAutonomiaRestante(20000);
		PooGO.setAutonomiaRestante(8000);
		Poo20.setAutonomiaRestante(30000);
		return central;
	}
	
	
	public static void createUtente(String username,String name, CentralTIU central) {
		Utente utente = new Utente(username, name);											//Criar utente
		central.addUtenteMap(username, utente);												//Adicionar ao mapa de utentes usando o username como "chave" unica
	}
	
	public static Trotinete createTrotinete(String codigo ,int autonomia, int velocidade, CentralTIU central) {
		Trotinete trotinete = new Trotinete(codigo, autonomia, velocidade);					//Criar trotinete
		central.addTrotinetesMap(codigo, trotinete);										//Adicionar ao mapa de trotinetes usando o codigo como "chave" unica
		return trotinete;
	}
}