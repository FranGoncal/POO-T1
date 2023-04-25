package tiu.mobilidade;

import java.util.ArrayList;
import tiu.core.*;

/**
 * Classe que representa uma trotinete
 * Deve ter o código identificativo, se tem algum aluguer no momento,
 * uma lista de alugueres que já realizou, a autonomia,
 * a autonomia restante, a velocidade de deslocamento,
 * a distância total percorrida,
 * e a distância percorrida no aluguer atual.  
 * 
 */
public class Trotinete {
	private String codigo;
	private Aluguer aluguer;
	private boolean indisponivel;
	private boolean emAndamento;
	private boolean carga;
	public ArrayList<Aluguer> alugueres = new ArrayList<Aluguer>();
	private int autonomia;
	private int	autonomiaRestante;
	private int velocidade;
	private int distanciaTotal;
	private int distanciaAluguer;


	public static final int CARREGADOR = 400;										//Constante de carregamento


	public Trotinete(String codigo ,int autonomia, int velocidade) {
		this.codigo = codigo;									
		this.aluguer = null;											//Trotinete inicia sem aluguer associado
		this.indisponivel = false;
		this.emAndamento = false;
		this.autonomia = autonomia;
		this.velocidade = velocidade;
		this.distanciaTotal = 0;
		this.distanciaAluguer = 0;
		this.autonomiaRestante = autonomia;
	}


	public Aluguer getAluguer() {										//Obter o aluguer atual desta trotinete
		return aluguer;
	}

	public void setAutonomiaRestante(int autonomiaRestante) {			//Define a autonomia restante das trotinetes
		this.autonomiaRestante = autonomiaRestante;
	}

	public int getAutonomiaRestante() {									//Obter a autonomia restante da trotinete
		return this.autonomiaRestante;
	}

	public int getDistanciaTotal() {									//Obter a distancia total percorrida pela trotinete (não usado neste trabalho)
		return distanciaTotal;
	}

	public void setDistanciaTotal(int distanciaTotal) {					//Incrementa o valor da distancia total de um aluguer cada vez que um aluguer acaba
		this.distanciaTotal += distanciaAluguer;
	}

	public int getDistanciaAluguer() {									//Obter a distancia percorrida neste aluguer
		return distanciaAluguer;
	}

	public String getCodigo() {											//Obter o codigo unico da trotinete
		return codigo;
	}

	public int getAutonomia() {											//Obter a autonomia da trotinete
		return autonomia;
	}

	public int getVelocidade() {										//Obter a velocidade da trotinete
		return velocidade;
	}

	/** inicia um aluguer
	 * @param alu o aluguer a começar
	 */
	public void iniciaAluguer(Aluguer alu) {							//Cria um aluguer caso ainda não exista
		if (this.aluguer == null)										//Se o aluguer é null é porque não existe
			this.aluguer = alu;
	}

	/** termina o aluguer atual
	 * 
	 */
	public void terminaAluguer( ) {										//No caso de ter um aluguer, adiciona o aluguer ao histórico, apagar o aluguer e resetar a distancio de aluguer da trotinete
		if (this.aluguer != null) {
			alugueres.add(aluguer);
			this.aluguer = null;
			this.distanciaAluguer=0;
		}
	}

	/** Coloca a trotinete em andamento
	 */
	public void mover() {												//Coloca a trotinete em andamento
		this.emAndamento=true;
	}

	/** Pára a trotinete
	 */
	public void parar() {												//Coloca a trotinete parada
		this.emAndamento=false;
	}

	/** indica se a trotinete está em andamento
	 * @return se a trotinete está em andamento
	 */
	public boolean emAndamento() {
		return emAndamento;
	}

	/** indica se a trotinete está em uso, isto é,
	 * se está a ser alugada
	 * @return se a trotinete está em uso
	 */
	public boolean emUso() {											//No caso de não existir em aluguer, não está em uso, caso contrário, está
		if(this.aluguer==null)
			return false;
		else 
			return true;
	}


	/** indica se a trotinete está em carga
	 * @return se a trotinete está em carga
	 */
	public boolean emCarga() {
		return this.carga;
	}

	/** Método chamado pelo sistema a cada segundo
	 * para que a trotinete atualize o seu estado,
	 * simulando assim o movimento ou carga da mesma
	 */
	public void atualizar() {											
		if(this.emAndamento && emUso() && autonomiaRestante!=0) {			//No caso da trotinete estar a andar, em uso e a autonomia ser diferente de 0
			this.autonomiaRestante-=this.velocidade;							//Retira autonomia á trotinete
			if (autonomiaRestante>=0)											//Caso a autonomia (atualizada) seja maior ou igual a zero
				this.distanciaAluguer+=this.velocidade;								//Adicionamos distancia percorrida ao aluguer
			if (autonomiaRestante<0) {											//Se for uma autonomia negativa
				this.distanciaAluguer+=this.velocidade+autonomiaRestante;			//Somamos á distancia de aluguer a velocidade e a autonomia restante (que é negativa)
				autonomiaRestante=0;												//definimoa a autonomia restante como 0
			}
			this.aluguer.setDistancia();										//Usamos o metodo setDistancia para atualizar a cada segundo a distancia no aluguer
		}
		if(emUso())															//Caso esteja em uso, mas não necessáriamente em andamento					
			this.aluguer.setCusto();											//Usamos o metodo setCusto para atualizar a cada segundo o custo no aluguer	


		if(this.carga) {													//Caso esteja em carga
			this.autonomiaRestante+=CARREGADOR;							//Adicionamos à autonomia restante a constante carregador
			if(this.autonomiaRestante>=this.autonomia) {						//Caso a autonomia exceda a autonomia maxima
				this.autonomiaRestante=this.autonomia;								//Definimos a autonomia restante como a maxima
				setEmCarga(false);													//E tiramos a trotinete do seu estado em carga
			}
		}

	}


	/** coloca/retira a trotinete da manutenção
	 * @param indisponivel true para colocar em manutenção,
	 * false para retirar da manutenção
	 */
	public void setEmManutencao(boolean indisponivel) {
		if(this.aluguer==null) {											//Caso não tenha um aluguer
			this.indisponivel = indisponivel;									//Define a variável indisponivel de acordo com o argumento recebido
		}
	}

	/** indica se a trotinete está indisponível.
	 * A trotinete está indisponível se:<br>
	 *   - está em manutenção<br>
	 *   - está a carregar<br>
	 *   - se não está num aluguer e tem pouca autonomia<br>
	 * @return se a trotinete está indisponível
	 */
	public boolean estaIndisponivel() {
		if (this.autonomiaRestante < 500)									//Confirma se a autonomia restante é insuficiente para um novo aluguer (abaixo de 500)
			this.indisponivel = true;											//Caso seja insuficiente coloca o estado da trotinete como indisponivel
		return this.indisponivel;											//Retorna o estado da trotinete
	}

	/** Coloca/retira a trotinete em carga.
	 * Se a trotinete está em uso NÃO pode ser colocada em carga
	 * @param carga true para por a carregar e
	 * false para retirar do carregamento
	 */
	public void setEmCarga( boolean carga  ) {
		if(this.aluguer==null) {											//Caso a trotinete não esteja a ser alugada
			this.carga = carga;													//Define a variável carga de acordo com o argumento recebido
			this.indisponivel = carga;											//Define a variável indisponivel de acordo com o argumento recebido carga que é o mesmo booleano
		}
	}
}