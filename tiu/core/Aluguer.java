package tiu.core;
import tiu.mobilidade.Trotinete;
import java.time.Duration;
import java.time.LocalDateTime;


/**
 * Classe que representa um aluguer no sistema.
 * O aluguer deve ter a data de inicio (LocalDateTime)
 * o utente que fez o aluguer, a trotinete que foi alugada
 * e, caso tenha terminado, deve ter ainda a data de fim,
 * o custo e a distância percorrida
 */
public class Aluguer {
	LocalDateTime inicio;
	LocalDateTime fim;
	Trotinete trotinete;
	Utente utente;
	Duration duracao;
	float custo;
	int distancia;

	public static final float TAXA_DE_DESBLOQUEIO = 0.5f;									//Constante taxa de desbloqueio adicionada a todos os desbloqueios
	public static final float PRECO_POR_MINUTO = 0.15f;										//Constante preço por minuto
	public static final int SEGS_POR_MINUTO = 60;
	
	public Aluguer( Utente utente,Trotinete trotinete) {

		this.inicio = LocalDateTime.now();
		this.fim = null;
		this.trotinete = trotinete;
		this.utente = utente;
		this.distancia = 0;
		this.duracao = null;
		this.custo=TAXA_DE_DESBLOQUEIO+PRECO_POR_MINUTO;
	}

	public Trotinete getTrotinete() {								//Retorna a trotinete do aluguer
		return trotinete;
	}

	public LocalDateTime getInicio() {								//Obtem uma data inicio 
		return inicio;
	}

	public LocalDateTime getFim() {									//Obtem uma data fim 
		return fim;
	}
	/** método responsável por terminar o aluguer
	 */
	public void terminar() {
		this.fim=LocalDateTime.now();								//Guarda a data fim do aluguer
		getDuracao(fim);											//Obtem a duração do aluguer
		setCusto();													//Define o custo final do aluguer
		setDistancia();												//Define a distancia final do aluguer
		utente.terminaAluguer(this);								//Termina o aluguer da classe utente
		trotinete.terminaAluguer();									//Termina o aluguer da classe trotinete
	}

	/** Indica a duração do aluguer. Se o aluguer já
	 * tiver terminado, retorna a duração total do aluguer.
	 * Se o aluguer ainda estiver a decorrer, deve retornar
	 * a duração atual do aluguer
	 * 
	 * @return a duração do aluguer
	 */
	public Duration getDuracao( LocalDateTime toDateTime) {
		duracao = Duration.between(inicio, toDateTime);				//Guarda a duração, entre a data inicio e fim do aluguer
		return duracao;
	}

	/** retorna o custo atual do aluguer.
	 * Se o aluguer esiver terminado este é o custo total,
	 * senão representa o custo até ao momento
	 * @return o custo atual do aluguer
	 */

	public float getCusto() {
		return custo;
	}

	public void setCusto() {
		duracao = this.getDuracao(LocalDateTime.now());				//Obtem a duração atual do aluguer
		float tempo =duracao.toMinutes();							//Transforma a duração em minutos
		custo = TAXA_DE_DESBLOQUEIO + tempo*PRECO_POR_MINUTO + PRECO_POR_MINUTO;	
	}

	/** retorna a distância percorrida durante o aluguer
	 * Se o aluguer ainda estiver a decorrer, retorna a
	 * distância que foi percorrida até ao momento. 
	 * @return a distância percorrida durante o aluguer
	 */
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia() {									//Guarda a distancia do aluguer
		this.distancia = trotinete.getDistanciaAluguer();
	}


}