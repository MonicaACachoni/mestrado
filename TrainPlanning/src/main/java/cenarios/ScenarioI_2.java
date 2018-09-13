package cenarios;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Vector;

import Malha.Malha;
import Malha.StatusTrens;
import dominio.PTP;
import dominio.Trem;

public class ScenarioI_2 {


	static BigDecimal k = BigDecimal.ZERO;
	static BigDecimal kMaisUm= BigDecimal.ZERO;
	static Trem tremN1;
	static Trem tremN2;
	static Trem t;
	static int indice1;
	static boolean t1MenorCusto=false;
	static int indice2;
	static BigDecimal positionTremN1= BigDecimal.ZERO;
	static BigDecimal positionTremN2= BigDecimal.ZERO;
	static int indiceDoTremAPlanejar;
	static int i=0;
	static int count =0;
	

		
		// a estacoes sao considerados pontos de parada dos trens , nesse cenario os trens estao entre duas estacoes
		// dada uma lista de trens verifica se tem dois trens que estao partindo do mesmo segmento em sentidos opostos. 
		// checa se o custo de voltar um dos trens pra a posicap k e o custo de voltar o trem 2 para a posicao k+1, executa o que for de menor custo
		public static void  Scenario1_2_(Vector<Trem> trens, Malha malha) {
			
			
			//verifica se tem dois trens no mesmo trecho da malha em sentido contrario (colisao frontal)
			for (int i=0; i < trens.size() ; i++) {
				Trem t1 = trens.get(i);
				
				if (t1.getPTP().getDistancia()>0) {
					for (int j=i+1; j < trens.size() ; j++) {
						Trem t2 = trens.get(j);
						if (malha.statusDePosicaoEntreDoisTrens(t1,t2)==StatusTrens.ColisaoFrontal)
						{   
							System.out.println("Cenario de colisao Frontal\n"); 
							if (t1.getCusto()<t2.getCusto())
							{
								PTP ptp1 = t1.getPTP();
								LocalDateTime hora = ptp1.getHorario().plusMinutes((t1.calculoTempoTremAteEstacao(ptp1.getPosicao(), malha)));
							
								//adiciona novo PTP ao trem t1
								PTP p = new PTP(ptp1.getPosicao(),0,ptp1.getProxima(),hora);
								t1.setPTP(p);
								
							}
							else
							{
								PTP ptp2 = t2.getPTP();
								LocalDateTime hora = ptp2.getHorario().plusMinutes((t2.calculoTempoTremAteEstacao(ptp2.getPosicao(), malha)));
							
								//adiciona novo PTP ao trem t2
								PTP p = new PTP(ptp2.getPosicao(),0,ptp2.getProxima(),hora);
								t2.setPTP(p);
							}
						}
						
					}
				}
			}			
		}		
	}
		
