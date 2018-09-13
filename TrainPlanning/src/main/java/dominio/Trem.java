package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Malha.Malha;

public class Trem implements Comparable<Trem> {

	private String nome;
	// initial terminal distance_to_initial
	private LocalDateTime horaInicial;
	int destino; // posicao y sobre a linha
	int velocidade;
	int custo;
	List<PTP> percursoDoTrem = new ArrayList<>();
	List<Meeting> tie = new ArrayList<>();

	// Construtor
	//train: verde 6 7 2349 19 27 2018-06-15T09:44:00
	
	public List<Meeting> getTie() {
		return tie;
	}


	public void setTie(int i,Meeting t) {
		tie.add(i,t);
	}

	public Trem(String nome, int posicao, int proxima, int distancia, int terminal, int velocidade, LocalDateTime saida,int custo) {
		super();
		this.setNome(nome);
		this.setHoraInicial(saida);
		percursoDoTrem.add(new PTP(posicao, distancia, proxima, saida));
		this.destino = terminal;
		this.velocidade = velocidade;
		this.custo=custo;
	}


	public int getDestino() {
		return destino;
	}

	public List<PTP> getPercursoDoTrem() {
		return percursoDoTrem;
	}

	public int getPosicao(Trem t) {
		return percursoDoTrem.get(t.getPercursoDoTrem().size()-1).getPosicao();
	}
	
	public PTP getPTP() {
		return percursoDoTrem.get(percursoDoTrem.size()-1);
	}
	
	
	public void setPTP(PTP p) {
		percursoDoTrem.add(p);

	}
	
	public LocalDateTime getH(Trem t) {
		return percursoDoTrem.get(t.getPercursoDoTrem().size()-1).getHorario();
	}
	
	public void setPercursoDoTrem(int i, PTP percursoDoTrem) {
		this.percursoDoTrem.add(percursoDoTrem);
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	@Override
	public String toString() {

	    for (int i=0; i< getPercursoDoTrem().size();i++) {
	    	
	    	System.out.println("Nome: " + nome + " Percurso= "+ getPercursoDoTrem().get(i).posicao + " distancia= "+ 
	    						getPercursoDoTrem().get(i).distancia + " horario= " + getPercursoDoTrem().get(i).horario+ "\n");
	    }
	    return nome;
		
	    
	}


	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(LocalDateTime horaInicial) {
		this.horaInicial = horaInicial;
	}

	public int calculoTempoTremAteEstacao(int terminal, Malha M) {

		int posicao = percursoDoTrem.get(percursoDoTrem.size()-1).getPosicao();
		int distancia = percursoDoTrem.get(percursoDoTrem.size()-1).getDistancia();
		if (distancia==0)
			return (M.distancia(posicao,terminal)/velocidade);
		else {
			
			if (posicao==terminal)
				return distancia/velocidade;
		
			else
				return ((M.distancia(posicao,terminal)-distancia)/velocidade);				
		}
	}

	@Override
	public int compareTo(Trem that) {
		return this.getHora().compareTo(that.getHora());
	}
	
	public LocalDateTime getHora() {
		return percursoDoTrem.get(percursoDoTrem.size()).getHorario();
	}
	
	public void printTrens()
	{
		
	}


}