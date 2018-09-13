package Malha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

import dominio.PTP;
import dominio.Trecho;
import dominio.Trem;

public class Malha {

	Graph G;
	
	String[] nomes;  
	int[] capacidades;
	
	public int getCapacidades(int i) {
		return capacidades[i];
	}


	// Para cada estacao de 0 a n-1 temos uma lista de trens nela:
	Vector<ArrayList<Trem>> ocupacaoEstacao;
	
	// Para cada estacao de 0 a n-1 temos uma lista de trens nela:
	static ArrayList<Trecho> ocupacaoTrecho;
		
		
	// Custos dos caminhos minimos e predecessores para todos os pares de vértices:
	int[][] Costs;
	int[][] Pi;
	
	// Numero de vertices e arestas na malha:
	int n;
	int m;
	
	
	public Malha(String grafo, String estacoes) throws IOException {
        ocupacaoTrecho = new ArrayList<Trecho>(m);
		G = GraphLoader.loadFromFile(grafo);
		n = G.getN();
		m = G.getM();

		ShortestPaths sp = new ShortestPaths();
		sp.floyd(G);
		Costs = sp.getCosts();
		Pi = sp.getPi();
		
		// Carrega dados das estacoes:
		Locale.setDefault(Locale.US);
	    Scanner s = new Scanner(new BufferedReader(new FileReader(estacoes)));

	    nomes = new String[n];
	    capacidades = new int[n];

	    int i =	0;
	    while (s.hasNext()) {
	    	i = (int) s.nextInt();
	        nomes[i] = s.next();
	        capacidades[i] = (int) s.nextInt();
	    }
	    s.close();

	    // Cada estacao comeca com uma lista vazia de trens:
	    ocupacaoEstacao = new Vector<ArrayList<Trem>>(n);
	    for (i=0; i<n; i++) {
	    	ocupacaoEstacao.add(i, new ArrayList<Trem>(i));
	    }

	}
	
	
	public void entrar(Trem t, int estacao) {
		ocupacaoEstacao.get(estacao).add(t);
	}
	

	
	public void sair(Trem t, int estacao) {
		
		ArrayList<Trem> L = ocupacaoEstacao.get(estacao);
		
		Iterator<Trem> itr = L.iterator();
		
		while (itr.hasNext()){
		    Trem x = itr.next(); 
		    if (x == t) {
		    	itr.remove();
		    	break;
		    }
		}
	}
	
	public static void novoTrecho( int e1,int e2) {
		Trecho t = new Trecho(e1, e2);
		ocupacaoTrecho.add(t);
	}

    public void checaTrecho( ) {
       
       // para cada trecho:
    	for(int i = 0; i < this.m; ++i) {
            int s1= ocupacaoTrecho.get(i).getTrens().size();
            if(s1==0)
            {
            	int e1= ocupacaoTrecho.get(i).getE1();
            	int e2= ocupacaoTrecho.get(i).getE2();
            	
            	ArrayList<Trem> trensComE1 = numeroTrensMsmSentido(e1,i);
            	ArrayList<Trem> trensComE2 = numeroTrensMsmSentido(e2,i);
            	int espacoE1 = espacoDisponivelEstacao(e1);
            	int espacoE2 = espacoDisponivelEstacao(e2);
            			
            	if ((trensComE1.size()+trensComE2.size()) == (espacoE1+espacoE2) && (trensComE1.size()+trensComE2.size() !=0) )          			
            	{
            	          		
            		
            		// trem 3
            		Trem t3 = trensComE2.get(0);
            		PTP ptp3 = t3.getPTP();
					LocalDateTime hora3 = ptp3.getHorario().plusMinutes((t3.calculoTempoTremAteEstacao(e2, this)));
					//adiciona novo PTP ao trem t1
					int proxima3 = nextStation(e2,t3.getDestino());
					PTP p3 = new PTP( e2, 0, proxima3, hora3);
					t3.setPTP(p3);
					
						
					// trem 4
            		Trem t4 = trensComE2.get(1);
            		PTP ptp4 = t4.getPTP();
					LocalDateTime hora4 = ptp4.getHorario().plusMinutes((t4.calculoTempoTremAteEstacao(e1, this)));
					//adiciona novo PTP ao trem t1
					int proxima4 = nextStation(e1,t4.getDestino());
					PTP p4 = new PTP(e1,0,proxima4,hora4);
					t4.setPTP(p4);
					
				
            		// trem 1
            		Trem t1 = trensComE1.get(0);
            		PTP ptp1 = t1.getPTP();
					LocalDateTime hora1 = hora4.plusMinutes((t1.calculoTempoTremAteEstacao(e2, this)));
					//adiciona novo PTP ao trem t1
					int proxima1 = nextStation(e2,t1.getDestino());

					PTP p1 = new PTP(e2,0,proxima1,hora1);
					//adiciona Novo PTP
					t1.setPTP(p1);
						
					// trem 2
            		Trem t2 = trensComE2.get(1);
            		PTP ptp2 = t2.getPTP();
					LocalDateTime hora2 =ptp2.getHorario().plusMinutes((t1.calculoTempoTremAteEstacao(e1, this)));
					
					//adiciona novo PTP ao trem t1
					int proxima2 = nextStation(e1,t2.getDestino());
					PTP p2 = new PTP(e1,0,proxima2,hora2);
					t1.setPTP(p2); 		
            	}
            }//if(s1==0)
    	}//for
            	       
    }

    //paramentros estacao e indice do trecho
    //retorno quantidade de trens no mesmo sentido
    public ArrayList<Trem> numeroTrensMsmSentido(int e,int t) {
    	ArrayList<Trem> listaTrens= new ArrayList<>();;
    	// Pega as estacoes vizinhas a estacao e1:
    	ArrayList<Integer> neighbors = this.neighborStation(e);
    	for (Integer n : neighbors) {
			// pega trecho de e1 para cada neighbor
    		int indice = indiceTrecho(e, n);
    		//verifica se os trens estao no msm sentido
    		int s2= ocupacaoTrecho.get(indice).getTrens().size();
    		for(int j = 0; j < s2; ++j){
    			Trem t1 = ocupacaoTrecho.get(indice).getTrens().get(j);
    			int proxima = t1.getPTP().getProxima();
    			if (proxima==e) // considerando que tempos são iguais, pq os trens/planejamento esta sendo iniciado e vao se movimentar
        		{   
        			//soma quantidade de trens naquele trecho
    				listaTrens.add(t1);
        		}
    		}
		}
    	return listaTrens;
    	
    }
     
    
    //paramentros estacao e indice do trecho
    //retorno quantidade de trens no mesmo sentido
    public ArrayList<Trem> numeroTrensSentidoOposto(int e) {
    	return this.ocupacaoEstacao.get(e);    	
    }
    
	public int trensTrecho(int e1,int e2) {

		for(int i = 0; i < this.m; ++i) {
			if (((Trecho)this.ocupacaoTrecho.get(i)).getE1() == e1 &&
					((Trecho)this.ocupacaoTrecho.get(i)).getE2() == e2) {
				System.out.println("Trecho possui"+ ocupacaoTrecho.get(i).getTrens().size()+"trens");
				return ocupacaoTrecho.get(i).getTrens().size();
			}
		}
		return 0;
	}
	public void entrarTrecho(Trem t1, int e1,int e2) {

		Trecho t = new Trecho(e1, e2,t1);

		for (int i=0; i<m; i++) {
			if(((ocupacaoTrecho.get(i).getE1()==e1) &&
					(ocupacaoTrecho.get(i).getE2()==e2))||(ocupacaoTrecho.get(i).getE1()==e2) &&
					(ocupacaoTrecho.get(i).getE2()==e1)) {
				System.out.println("Adicionar trem ao trecho");

				//int cont= ocupacaoTrecho.get(i).getTrens().size();
				ocupacaoTrecho.get(i).getTrens().add(t1);
			}
		}
	}

	public void sairTrecho(Trem trem, Trecho t) {
		for(int i = 0; i < this.m; ++i) {
			if (((Trecho)this.ocupacaoTrecho.get(i)).getE1() == t.getE1() &&
					((Trecho)this.ocupacaoTrecho.get(i)).getE2() == t.getE2() &&
					trem.getNome().equals(t.getTrens())) {
				System.out.println("Remover trem do trecho");
				((Trecho)this.ocupacaoTrecho.get(i)).getTrens().remove(i);
			}
		}

	}

    public int indiceTrecho(int e1,int e2) {

        for (int i=0; i<m; i++) {
            if (((ocupacaoTrecho.get(i).getE1()==e1)&&(ocupacaoTrecho.get(i).getE2()==e2)) ||
                    ((ocupacaoTrecho.get(i).getE1()==e2)&&(ocupacaoTrecho.get(i).getE2()==e1))) {
                return i;
            }
        }
        return 0;

    }


    public boolean temCapacidade(int estacao) {
		
		return ocupacaoEstacao.get(estacao).size() < capacidades[estacao];
	}
	
	public int espacoDisponivelEstacao(int estacao) {
		
		return capacidades[estacao]-ocupacaoEstacao.get(estacao).size();
	}
	
	public int distancia(int inicial, int terminal) {
		return Costs[inicial][terminal];
	}

    public int quantidadeNoTrecho(int i) {

        return ocupacaoTrecho.get(i).getTrens().size();
    }
	
	
	public int nextStation(int current, int destination) {
		
		int k = destination;
		int next = destination;

		if (Pi[current][destination] == -1) 
			return -1;

			while (k != current && k != -1) {
				next = k;
				k = Pi[current][k];
			}
			
			return next;
	}
	
	public ArrayList<Integer> neighborStation(int station) {	
			return G.getNeighbors(station);
	}

	public StatusTrens statusDePosicaoEntreDoisTrens(Trem t1, Trem t2) {

		PTP ptp1 = t1.getPTP();
		PTP ptp2 = t2.getPTP();
		//distancia do d do PTP do trem 1 até a proxima estacao
		
		int dEstacao1 = distancia(ptp1.getDistancia(), nextStation(ptp1.getPosicao(), t1.getDestino()));
		//distancia do d do PTP do trem 2 até a proxima estacao
		
		int dEstacao2 = distancia(ptp2.getDistancia(), nextStation(ptp2.getPosicao(), t2.getDestino()));
		
		//nextStation (origem / destino)
		
		// Os dois trens estao fora de uma estacao, d de t1 e d de t2 >0, fase 1 
		if (ptp1.getDistancia() > 0 && ptp2.getDistancia() > 0) {
			// Os dois trens estao no mesmo trecho e tem a mesma posicao.		
			if (ptp1.getPosicao() == ptp2.getPosicao()) {
				if (nextStation(ptp1.getPosicao(), t1.getDestino()) == nextStation(ptp2.getPosicao(), t2.getDestino()))
					// os trens estao no mesmo sentido.  Pode haver colisao traseira. sem considerar horarios e velocidade ainda
					return StatusTrens.ColisaoTraseira;
			}
			else if (nextStation(ptp1.getPosicao(), t1.getDestino()) == ptp2.getPosicao() &&  
						nextStation(ptp2.getPosicao(), t2.getDestino()) == ptp1.getPosicao()){
				
						if ((dEstacao1>dEstacao2)||(dEstacao2>dEstacao1)) {
							// Os trens estao em rota de colisao frontal.
							return StatusTrens.ColisaoFrontal;
						}
						else
						{
							return StatusTrens.Afastamento;
						}
			}	
		}		
		else if (ptp1.getDistancia() == 0 && ptp2.getDistancia() == 0) {
					if (ptp1.getPosicao() == ptp2.getPosicao())
						return StatusTrens.MesmaEstacao;
					else if (nextStation(ptp1.getPosicao(), ptp2.getPosicao()) == ptp2.getPosicao())
						return StatusTrens.EstAdj;
		}		
	
		return StatusTrens.Outros;
	}

	public ArrayList<Trem> getOcupacao(int estacao) {
		if (!ocupacaoEstacao.get(estacao).isEmpty())
			return ocupacaoEstacao.get(estacao);
		else return null;
	}
	
	public ArrayList<Trem> getOcupacaoTrecho(int trecho) {
//		if (!ocupacaoTrecho.get(trecho).isEmpty())
//			return ocupacaoTrecho.get(trecho);
		 return null;
	}

	public String getNome(int i) {
		return nomes[i];
	}


	@Override
	public String toString() {
		return "Malha [nomes=" + Arrays.toString(nomes) + "]";
	}


	public void setNomes(String[] nomes) {
		this.nomes = nomes;
	}


	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}
	
}
