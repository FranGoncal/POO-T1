package tiu.mobilidade;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import tiu.core.Aluguer;

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
	private int codigo;
	private boolean emAluguer;
	private boolean indisponivel;										//n sei se necessario
	private boolean emAndamento;
	private boolean carga;												//n sei se necessario
	private ArrayList<Aluguer> alugueres = new ArrayList<Aluguer>();
	private int autonomia;
	private int	autonomiaRestante;
	private int velocidade;
	private int distanciaTotal;
	private int distanciaAluguer;
	private int CONSTANTE = 100;


	public Trotinete(int codigo, int autonomia, int velocidade) {
		this.codigo = codigo;
		this.emAluguer = true;
		this.indisponivel = false;
		this.emAndamento = false;
		this.autonomia = autonomia;
		this.velocidade = velocidade;
		this.distanciaTotal = 0;
		this.distanciaAluguer = 0;
		this.autonomiaRestante = autonomia;

	}

	//	public boolean isEmAluguer() {
	//		return emAluguer;
	//	
	//	}
	public void setEmAluguer(boolean emAluguer) {													//+- done
		this.emAluguer = emAluguer;
	}
	public int getAutonomiaRestante() {																//+- done
		return this.autonomiaRestante;
	}
	public int getDistanciaTotal() {																//+- done
		return distanciaTotal;
	}
	public void setDistanciaTotal(int distanciaTotal) {												//+- done
		this.distanciaTotal += distanciaAluguer;													//Usar apos terminar aluguer
	}
	public int getDistanciaAluguer() {
		return distanciaAluguer;
	}
	//	public void setDistanciaAluguer(int distanciaAluguer) {											// Meio inutil por enquanto
	//		this.distanciaAluguer = distanciaAluguer;
	//	}
	public int getCodigo() {																		//+- done
		return codigo;
	}
	public ArrayList<Aluguer> getAlugueres() {
		return alugueres;
	}
	public int getAutonomia() {																		//+- done
		return autonomia;
	}
	public int getVelocidade() {																	//+- done
		return velocidade;
	}



	/** inicia um aluguer
	 * @param alu o aluguer a começar
	 */
	public void iniciaAluguer(Aluguer alu) {
		setEmAluguer(true);
	}

	/** termina o aluguer atual
	 * 
	 */
	public void terminaAluguer( ) {

		setEmAluguer(false);
	}

	/** Coloca a trotinete em andamento
	 */
	public void mover() {
		this.emAndamento=true;
	}

	/** Pára a trotinete
	 */
	public void parar() {
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
	public boolean emUso() {
		return this.emAluguer;
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
		if(this.emAndamento) {
			this.autonomiaRestante-=this.velocidade;
			this.distanciaAluguer+=this.velocidade;
		}
		if(this.carga) {
			this.autonomiaRestante+=this.CONSTANTE;
			if(this.autonomiaRestante>=this.autonomia) {
				this.autonomiaRestante=this.autonomia;
				setEmCarga(false);
			}
		}
		if(this.autonomiaRestante == autonomia) {
			this.carga = false;
			this.indisponivel = false;
		}
		//		int anterior=0;
		//		int tempoAluguer=0;
		//		do{
		//			LocalDateTime data = LocalDateTime.now();
		//			int segundos = data.getSecond();
		//			if(segundos > anterior || (anterior == 59 && segundos == 0)) {
		//				tempoAluguer+=1;
		//				if(emAndamento)
		//					distanciaAluguer+=velocidade;
		//				anterior=segundos;
		//				System.out.println("DistanciaAndar : "+ distanciaAluguer );
		//				System.out.println("tempoAluguer - "+tempoAluguer+"\n");
		//			}
		//		}
		//		while(emAluguer==true);
	}


	/** coloca/retira a trotinete da manutenção
	 * @param indisponivel true para colocar em manutenção,
	 * false para retirar da manutenção
	 */
	public void setEmManutencao(boolean indisponivel) {
		this.indisponivel = indisponivel;

	}

	/** indica se a trotinete está indisponível.
	 * A trotinete está indisponível se:<br>
	 *   - está em manutenção<br>
	 *   - está a carregar<br>
	 *   - se não está num aluguer e tem pouca autonomia<br>
	 * @return se a trotinete está indisponível
	 */
	public boolean estaIndisponivel() {
		if (this.autonomiaRestante<this.autonomia)
			this.indisponivel=true;
		return this.indisponivel;
	}

	/** Coloca/retira a trotinete em carga.
	 * Se a trotinete está em uso NÃO pode ser colocada em carga
	 * @param carga true para por a carregar e
	 * false para retirar do carregamento
	 */
	public void setEmCarga( boolean carga  ) {
		this.carga = carga;
		this.indisponivel = carga;
		if (carga == true ) {
			this.emAndamento = false;
		}
	}
}