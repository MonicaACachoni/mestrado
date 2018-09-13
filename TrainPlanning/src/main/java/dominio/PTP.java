package dominio;

import java.time.LocalDateTime;

public class PTP implements Comparable<PTP> {

	int posicao; // estacao referencia de saida do trem
	int distancia;  // distancia que o trem se encontra de posicao
	int proxima; // proximo destino do trem
	LocalDateTime horario;
	
	
	public PTP(int posicao, int distancia, int proxima, LocalDateTime horario) {
		super();
		this.posicao = posicao;
		this.distancia = distancia;
		this.proxima = proxima;
		this.horario = horario;
	}
	
	
	public PTP(int posicao, LocalDateTime horario) {
		super();
		this.posicao = posicao;
		this.horario = horario;
		this.distancia = 0;
		this.proxima = -1;
	}

	public int getDistancia() {
		return distancia;
	}


	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getProxima() {
		return proxima;
	}

	public void setProxima(int proxima) {
		this.proxima = proxima;
	}

	public int getPosicao() {
		return posicao;
	}
	public LocalDateTime getHorario() {
		return horario;
	}
	public void sethorario(LocalDateTime horario) {
		this.horario = horario;
	}
	
	public void setPTP(Trem t,int posicao, int distancia, int proxima, LocalDateTime horario) {
		PTP p = new PTP(posicao,distancia,proxima,horario);
		t.setPTP(p);
	}
	
	@Override
	public String toString() {
		return  posicao + "   " + horario + " ";
	}
	@Override
	public int compareTo(PTP that) {
		return this.getHorario().compareTo(that.getHorario());
	}
	
}
