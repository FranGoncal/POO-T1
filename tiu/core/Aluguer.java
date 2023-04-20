package tiu.core;
import tiu.mobilidade.Trotinete;
import java.time.Duration;
import java.time.LocalDateTime;
import tiu.core.Utente;


/**
 * Classe que representa um aluguer no sistema.
 * O aluguer deve ter a data de inicio (LocalDateTime)
 * o utente que fez o aluguer, a trotinete que foi alugada
 * e, caso tenha terminado, deve ter ainda a data de fim,
 * o custo e a distância percorrida
 */
public class Aluguer {
	LocalDateTime inicio;
	Trotinete trotinete;
	Utente utente;
	Duration duracao;
	float custo;
	int distancia;

	float PrecoInicial = 0.5f;

	public Aluguer( String codigo, Utente utente) {

		this.inicio =  LocalDateTime.now();
		this.trotinete = trotinete;									//trotinete cujo codigo é codigo
		this.utente = utente;
		this.distancia = 0;
		this.duracao = null;
		this.custo=PrecoInicial;
	}
	public Aluguer( Trotinete trotinete, Utente utente) {			//TEMPORARIO!!!!!!!  usado nos testes
		this.inicio =  LocalDateTime.now();
		this.trotinete = trotinete;									//trotinete cujo codigo é codigo
		this.utente = utente;
		this.distancia = 0;
		this.custo=PrecoInicial;
	}


	/** método responsável por terminar o aluguer
	 */
	public void terminar() {
		utente.terminaAluguer();
		trotinete.terminaAluguer();
		getDuracao(LocalDateTime.now());
	}
	
	
	/** Indica a duração do aluguer. Se o aluguer já
	 * tiver terminado, retorna a duração total do aluguer.
	 * Se o aluguer ainda estiver a decorrer, deve retornar
	 * a duração atual do aluguer
	 * 
	 * @return a duração do aluguer
	 */
	public Duration getDuracao( LocalDateTime toDateTime) {
        duracao = Duration.between(inicio, toDateTime);
		return duracao;
	}

	/** retorna o custo atual do aluguer.
	 * Se o aluguer esiver terminado este é o custo total,
	 * senão representa o custo até ao momento
	 * @return o custo atual do aluguer
	 */
	public float getCusto() {
		//fazer a diferença em segundos das duracoes
		
		return 0;
	}
//	public void setCusto(int tempo) {
//		int precoTempo = tempo;
//		this.custo=PrecoInicial+precoTempo;
//	}
	
	
	
	
	/** retorna a distância percorrida durante o aluguer
	 * Se o aluguer ainda estiver a decorrer, retorna a
	 * distância que foi percorrida até ao momento. 
	 * @return a distância percorrida durante o aluguer
	 */
	public int getDistancia() {
		return 0;
	}
	
//	public Trotinete getTrotinete(String codigo) {
//		for (Trotinete t : Trotinetes)
//		if(trotinete.getCodigo()==codigo)
//			return trotinete;
//	}
}
