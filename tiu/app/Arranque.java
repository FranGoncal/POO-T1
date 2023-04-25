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
		Set<String> chavesUtentes  = utenteMap.keySet();															//cria um set com as chaves do mapa	
		for (String chave : chavesUtentes ) {																		//Percorrer as chaves do mapa	
			//TODO FEITO cuidado com o null, substituir pelo valor correto
			JanelaUtente ju = new JanelaUtente( jp, central, utenteMap.get(chave) );						//criar a janela utente
			ju.setLocation( 20 + i*(ju.getWidth()+10), 20);													//setar a localizção da janela
			ju.setVisible( true );																			//Janela visivel
			i++;																							//adiciona a posição da janela
		}

		// TODO FEITO criar as janelas das trotinetes (criar uma por cada trotinete)
		i=0;
		HashMap<String, Trotinete> trotineteMap = central.getTrotinetesMap();											//Obter o mapa das trotinetes da central
		Set<String> chavesTrotinetes= trotineteMap.keySet();
		for (String chave : chavesTrotinetes ) {
			// TODO FEITO cuidado com o null, substituir pelo valor correto
			JanelaTrotinete jt = new JanelaTrotinete( jp, trotineteMap.get(chave)) ;
			jt.setLocation( 20 + i*(jt.getWidth()+10), 200);
			jt.setVisible( true );
			i++;
		}

	}

	/** cria a central e usa a configuração de testes
	 * @return a central criada
	 */
	private static CentralTIU setupSistema() {
		CentralTIU central = new CentralTIU(); //trotinetesMap,utentesMap

		// TODO FEITO criar os utentes
		Utente fsergio = new Utente("fsergio@ipcb.pt","Sérgio Barbosa");
		central.addUtenteMap("fsergio@ipcb.pt", fsergio);
		Utente jojo89 = new Utente("jojo89@g.com","João José Silva");
		central.addUtenteMap("jojo89@g.com",jojo89);
		Utente codeguru = new Utente("codeguru@guru.com ","Harry Hacker");
		central.addUtenteMap("codeguru@guru.com ",codeguru);
		Utente Luis = new Utente("luis@ipcbcampus.pt","Luis Santos");
		central.addUtenteMap("luis@ipcbcampus.pt",Luis);
		Utente Francisco = new Utente("francisco@ipcbcampus.pt","Francisco Gomçalves");
		central.addUtenteMap("francisco@ipcbcampus.pt",Francisco);

		// TODO FEITO criar as trotinetes
		Trotinete PooTr1 = new Trotinete("PooTr1",20000, 5);
		central.addTrotinetesMap("PooTr1", PooTr1);
		Trotinete PooTr2 = new Trotinete("PooTr2",20000, 5);
		central.addTrotinetesMap("PooTr2", PooTr2);
		Trotinete PooGO = new Trotinete("PooGO",30000, 6);
		central.addTrotinetesMap("PooGO", PooGO);
		Trotinete Poo20 = new Trotinete("Poo20",30000, 7);
		central.addTrotinetesMap("Poo20", Poo20);

		// TODO FEITO configurar as autonomias restantes para as trotinetes
		PooTr1.setAutonomiaRestante(400);
		PooTr2.setAutonomiaRestante(20000);
		PooGO.setAutonomiaRestante(8000);
		Poo20.setAutonomiaRestante(30000);

		return central;
	}
}